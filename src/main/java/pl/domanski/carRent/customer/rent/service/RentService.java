package pl.domanski.carRent.customer.rent.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.domanski.carRent.customer.common.model.Car;
import pl.domanski.carRent.customer.common.repository.CarRepository;
import pl.domanski.carRent.customer.rent.controller.dto.CarRentDto;
import pl.domanski.carRent.customer.rent.controller.dto.RentDateAndPlace;
import pl.domanski.carRent.customer.rent.model.Payment;
import pl.domanski.carRent.customer.rent.model.Rent;
import pl.domanski.carRent.customer.rent.model.dto.RentSummary;
import pl.domanski.carRent.customer.rent.repository.PaymentRepository;
import pl.domanski.carRent.customer.rent.repository.RentRepository;
import pl.domanski.carRent.customer.rent.utils.CheckCarAvailabilityUtils;
import pl.domanski.carRent.webClient.localization.DistanceCalculatorService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;
import static pl.domanski.carRent.customer.rent.mapper.RentCarMapper.createAvailableCarRentDto;
import static pl.domanski.carRent.customer.rent.mapper.RentCarMapper.createUnavailableCarRentDto;
import static pl.domanski.carRent.customer.rent.mapper.RentMapper.createRent;
import static pl.domanski.carRent.customer.rent.mapper.RentMapper.createRentSummary;
import static pl.domanski.carRent.customer.rent.utils.RentPricesCalculator.calculateGrossValue;
import static pl.domanski.carRent.customer.rent.utils.RentPricesCalculator.calculateTransportDistance;
import static pl.domanski.carRent.customer.rent.utils.RentPricesCalculator.calculateTransportPrice;

@Service
@RequiredArgsConstructor
public class RentService {

    private static final double DEFAULT_TAX = 1.23;
    private final DistanceCalculatorService distanceCalculatorService;
    private final CarRepository carRepository;
    private final CheckCarAvailabilityUtils checkCarAvailabilityUtils;
    private final PaymentRepository paymentRepository;
    private final RentRepository rentRepository;


    public RentSummary placeRent(CarRentDto carDto, Long paymentId, Long userId) {
        Car car = carRepository.findById(carDto.getCarId()).orElseThrow();
        Payment payment = paymentRepository.findById(paymentId).orElseThrow();
        BigDecimal grossValue = calculateGrossValueWithTax(carDto, DEFAULT_TAX);
        Rent rent = rentRepository.save(createRent(carDto, userId, car, payment, grossValue));
        //wyslać maila
        //
        //
        return createRentSummary(car, rent);
    }


    private static BigDecimal calculateGrossValueWithTax(CarRentDto carDto, double tax) {
        return carDto.getGrossValue().add(carDto.getRentalPrice()).add(carDto.getReturnPrice()).multiply(BigDecimal.valueOf(tax));
    }

    public List<CarRentDto> showCars(RentDateAndPlace rentDateAndPlace, boolean onlyAvailable) {
        checkCorrectnessDates(rentDateAndPlace);
        double rentalDistance = calculateTransportDistance(rentDateAndPlace.getRentalPlace(), distanceCalculatorService);
        double returnDistance = calculateTransportDistance(rentDateAndPlace.getReturnPlace(), distanceCalculatorService);
        ArrayList<CarRentDto> rentCars = new ArrayList<>();

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
                            return createUnavailableCarRentDto(car);
                        }
                    }).forEach(rentCars::add);
        }
        return rentCars;
    }

    private CarRentDto createAvailableCar(Car car, RentDateAndPlace rentDateAndPlace, double rentalDistance, double returnDistance) {
        long days = DAYS.between(rentDateAndPlace.getRentalDate(), rentDateAndPlace.getReturnDate());
        BigDecimal grossValue = calculateGrossValue(car, days);
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



}
