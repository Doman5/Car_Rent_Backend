package pl.domanski.carrent.rent.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.domanski.carrent.common.mail.EmailClientService;
import pl.domanski.carrent.common.model.Car;
import pl.domanski.carrent.common.model.SortingType;
import pl.domanski.carrent.common.repository.CarRepository;
import pl.domanski.carrent.common.utils.SortingUtils;
import pl.domanski.carrent.rent.mapper.PaymentMapper;
import pl.domanski.carrent.common.model.Payment;
import pl.domanski.carrent.rent.model.Rent;
import pl.domanski.carrent.rent.model.dto.CarToRentDto;
import pl.domanski.carrent.rent.model.dto.InitRent;
import pl.domanski.carrent.rent.model.dto.PaymentDto;
import pl.domanski.carrent.rent.model.dto.RentDateAndPlace;
import pl.domanski.carrent.rent.model.dto.RentDto;
import pl.domanski.carrent.rent.model.dto.RentSummary;
import pl.domanski.carrent.rent.repository.PaymentRepository;
import pl.domanski.carrent.common.repository.RentRepository;
import pl.domanski.carrent.rent.utils.CheckCarAvailabilityUtils;
import pl.domanski.carrent.webclient.localization.DistanceCalculatorService;
import pl.domanski.carrent.rent.mapper.CarToRentMapper;
import pl.domanski.carrent.rent.mapper.RentEmailMessageMapper;
import pl.domanski.carrent.rent.utils.DaysCalculateUtils;
import pl.domanski.carrent.rent.utils.RentPricesCalculator;
import pl.domanski.carrent.rent.utils.TransportDistanceCalculator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static pl.domanski.carrent.rent.mapper.RentMapper.createRent;
import static pl.domanski.carrent.rent.mapper.RentMapper.createRentSummary;

@Service
@RequiredArgsConstructor
public class RentService {

    private static final double DEFAULT_TAX_23 = 1.23;
    private final DistanceCalculatorService distanceCalculatorService;
    private final CarRepository carRepository;
    private final CheckCarAvailabilityUtils checkCarAvailabilityUtils;
    private final PaymentRepository paymentRepository;
    private final RentRepository rentRepository;
    private final EmailClientService emailClientService;

    @Transactional
    public RentSummary placeRent(RentDto rentDto, Long userId) {
        Car car = carRepository.findById(rentDto.getCarId()).orElseThrow();
        Payment payment = paymentRepository.findById(rentDto.getPaymentId()).orElseThrow();
        BigDecimal priceWithTax = RentPricesCalculator.addTaxToFinalPrice(rentDto, DEFAULT_TAX_23);
        BigDecimal finalPrice = RentPricesCalculator.addDepositToFinalPrice(priceWithTax, rentDto.getDeposit());
        Rent rent = rentRepository.save(createRent(rentDto, userId, car, payment, finalPrice, priceWithTax, rentDto.getDeposit()));
        sendConfirmEmail(rent);
        return createRentSummary(car, rent);
    }


    private void sendConfirmEmail(Rent rent) {
        //pobrać maila użytkownika z user id
        String userEmail = "dd@dd.pl";
        //
        emailClientService.getInstance().send(userEmail, "Twoje zamówienie zostało przyjęte", RentEmailMessageMapper.createEmailMessage(rent));
    }

    public List<CarToRentDto> showCars(RentDateAndPlace rentDateAndPlace, boolean onlyAvailable, String sortedByPrice) {
        checkCorrectnessDates(rentDateAndPlace);
        double rentalDistance = TransportDistanceCalculator.calculateTransportDistance(rentDateAndPlace.getRentalPlace(), distanceCalculatorService);
        double returnDistance = TransportDistanceCalculator.calculateTransportDistance(rentDateAndPlace.getReturnPlace(), distanceCalculatorService);
        ArrayList<CarToRentDto> rentCars = new ArrayList<>();

        List<Car> cars = carRepository.findAll();
        if (onlyAvailable) {
            cars.stream()
                    .filter(car -> checkCarAvailabilityUtils.checkCarAvailability(car.getId(), rentDateAndPlace))
                    .map(car -> createAvailableCar(car,
                            rentDateAndPlace,
                            rentalDistance,
                            returnDistance))
                    .forEach(rentCars::add);
        } else {
            cars.stream()
                    .map(car -> {
                        if (checkCarAvailabilityUtils.checkCarAvailability(car.getId(), rentDateAndPlace)) {
                            return createAvailableCar(car,
                                    rentDateAndPlace,
                                    rentalDistance,
                                    returnDistance);
                        } else {
                            return createUnavailableCar(car,
                                    rentDateAndPlace);
                        }
                    }).forEach(rentCars::add);
        }
        Optional<SortingType> sortingType = SortingType.get(sortedByPrice);
        return SortingUtils.sortCars(rentCars, sortingType, Comparator.comparing(CarToRentDto::getGrossValue));
    }



    private CarToRentDto createUnavailableCar(Car car, RentDateAndPlace rentDateAndPlace) {
        long days = DaysCalculateUtils.calculateCountOfDays(rentDateAndPlace);
        BigDecimal grossValue = RentPricesCalculator.calculateGrossValueByDaysCount(car, days);
        return CarToRentMapper.createUnavailableCarRentDto(car, grossValue);
    }

    private CarToRentDto createAvailableCar(Car car, RentDateAndPlace rentDateAndPlace, double rentalDistance, double returnDistance) {
        long days = DaysCalculateUtils.calculateCountOfDays(rentDateAndPlace);
        BigDecimal grossValue = RentPricesCalculator.calculateGrossValueByDaysCount(car, days);
        BigDecimal rentalPrice = RentPricesCalculator.calculateTransportPrice(car, rentalDistance);
        BigDecimal returnPrice = RentPricesCalculator.calculateTransportPrice(car, returnDistance);
        BigDecimal finalPrice = grossValue.add(rentalPrice).add(returnPrice);
        return CarToRentMapper.createAvailableCarRentDto(car, grossValue, rentalPrice, returnPrice, days, rentDateAndPlace, finalPrice);
    }

    private static void checkCorrectnessDates(RentDateAndPlace rentDateAndPlace) {
        if (rentDateAndPlace.getRentalDate().isAfter(rentDateAndPlace.getReturnDate()))
            throw new RuntimeException("Data oddania musi być później niż data wypożyczenia");
        if (rentDateAndPlace.getRentalDate().isBefore(LocalDateTime.now().plusHours(2).minusMinutes(1)  ))
            throw new RuntimeException("Data wypożyczenia musi być o 2 godziny później od teraz");
    }


    public InitRent getInitData() {
        List<String> sortingTypes = Arrays.stream(SortingType.values())
                .map(SortingType::getName)
                .toList();
        List<PaymentDto> paymentsTypes = paymentRepository.findAll().stream().map(PaymentMapper::mapToPaymentDto).toList();
        return new InitRent(sortingTypes, paymentsTypes);
    }
}
