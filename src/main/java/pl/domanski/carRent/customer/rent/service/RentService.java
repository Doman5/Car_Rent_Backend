package pl.domanski.carRent.customer.rent.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.domanski.carRent.customer.common.model.Car;
import pl.domanski.carRent.customer.common.model.SortingType;
import pl.domanski.carRent.customer.common.repository.CarRepository;
import pl.domanski.carRent.customer.rent.model.Payment;
import pl.domanski.carRent.customer.rent.model.Rent;
import pl.domanski.carRent.customer.rent.model.dto.CarToRentDto;
import pl.domanski.carRent.customer.rent.model.dto.RentDateAndPlace;
import pl.domanski.carRent.customer.rent.model.dto.RentDto;
import pl.domanski.carRent.customer.rent.model.dto.RentSummary;
import pl.domanski.carRent.customer.rent.repository.PaymentRepository;
import pl.domanski.carRent.customer.rent.repository.RentRepository;
import pl.domanski.carRent.customer.rent.utils.CheckCarAvailabilityUtils;
import pl.domanski.carRent.webClient.localization.DistanceCalculatorService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.DAYS;
import static pl.domanski.carRent.customer.common.utils.SortingUtils.sortCars;
import static pl.domanski.carRent.customer.rent.mapper.CarToRentMapper.createAvailableCarRentDto;
import static pl.domanski.carRent.customer.rent.mapper.CarToRentMapper.createUnavailableCarRentDto;
import static pl.domanski.carRent.customer.rent.mapper.RentMapper.createRent;
import static pl.domanski.carRent.customer.rent.mapper.RentMapper.createRentSummary;
import static pl.domanski.carRent.customer.rent.utils.RentPricesCalculator.addTaxToFinalPrice;
import static pl.domanski.carRent.customer.rent.utils.RentPricesCalculator.calculateGrossValueByDaysCount;
import static pl.domanski.carRent.customer.rent.utils.RentPricesCalculator.calculateTransportPrice;
import static pl.domanski.carRent.customer.rent.utils.TransportDistanceCalculator.calculateTransportDistance;

@Service
@RequiredArgsConstructor
public class RentService {

    private static final double DEFAULT_TAX_23 = 1.23;
    private final DistanceCalculatorService distanceCalculatorService;
    private final CarRepository carRepository;
    private final CheckCarAvailabilityUtils checkCarAvailabilityUtils;
    private final PaymentRepository paymentRepository;
    private final RentRepository rentRepository;

    @Transactional
    public RentSummary placeRent(RentDto rentDto, Long userId) {
        Car car = carRepository.findById(rentDto.getCarId()).orElseThrow();
        Payment payment = paymentRepository.findById(rentDto.getPaymentId()).orElseThrow();
        BigDecimal grossValue = addTaxToFinalPrice(rentDto, DEFAULT_TAX_23);
        Rent rent = rentRepository.save(createRent(rentDto, userId, car, payment, grossValue));
        //wyslać maila
        //
        //
        return createRentSummary(car, rent);
    }

    public List<CarToRentDto> showCars(RentDateAndPlace rentDateAndPlace, boolean onlyAvailable, String sortedByPrice) {
        checkCorrectnessDates(rentDateAndPlace);
        double rentalDistance = calculateTransportDistance(rentDateAndPlace.getRentalPlace(), distanceCalculatorService);
        double returnDistance = calculateTransportDistance(rentDateAndPlace.getReturnPlace(), distanceCalculatorService);
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
        sortCars(rentCars, sortingType, Comparator.comparing(CarToRentDto::getGrossValue));
        return rentCars;
    }



    private CarToRentDto createUnavailableCar(Car car, RentDateAndPlace rentDateAndPlace) {
        long days = DAYS.between(rentDateAndPlace.getRentalDate(), rentDateAndPlace.getReturnDate());
        BigDecimal grossValue = calculateGrossValueByDaysCount(car, days);
        return createUnavailableCarRentDto(car, grossValue);
    }

    private CarToRentDto createAvailableCar(Car car, RentDateAndPlace rentDateAndPlace, double rentalDistance, double returnDistance) {
        long days = DAYS.between(rentDateAndPlace.getRentalDate(), rentDateAndPlace.getReturnDate());
        BigDecimal grossValue = calculateGrossValueByDaysCount(car, days);
        BigDecimal rentalPrice = calculateTransportPrice(car, rentalDistance);
        BigDecimal returnPrice = calculateTransportPrice(car, returnDistance);
        return createAvailableCarRentDto(car, grossValue, rentalPrice, returnPrice, days, rentDateAndPlace);
    }

    private static void checkCorrectnessDates(RentDateAndPlace rentDateAndPlace) {
        if (rentDateAndPlace.getRentalDate().isAfter(rentDateAndPlace.getReturnDate()))
            throw new RuntimeException("Data oddania musi być później niż data wypożyczenia");
        if (rentDateAndPlace.getRentalDate().isBefore(LocalDateTime.now().plusHours(2)))
            throw new RuntimeException("Data wypożyczenia musi być o 2 godziny później od teraz");
    }


    public List<String> showSortingValues() {
        return Arrays.stream(SortingType.values())
                .map(SortingType::getName)
                .toList();
    }
}
