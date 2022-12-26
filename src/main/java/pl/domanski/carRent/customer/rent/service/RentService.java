package pl.domanski.carRent.customer.rent.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.domanski.carRent.customer.common.model.Car;
import pl.domanski.carRent.customer.common.repository.CarRepository;
import pl.domanski.carRent.customer.rent.controller.dto.CarRentDto;
import pl.domanski.carRent.customer.rent.controller.dto.RentDateAndPlace;
import pl.domanski.carRent.customer.rent.model.dto.CarDto;
import pl.domanski.carRent.customer.rent.model.dto.RentSummary;
import pl.domanski.carRent.customer.rent.utils.CheckCarAvailabilityUtils;
import pl.domanski.carRent.webClient.localization.DistanceCalculatorService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;
import static pl.domanski.carRent.customer.rent.utils.RentPricesCalculator.calculateGrossValue;
import static pl.domanski.carRent.customer.rent.utils.RentPricesCalculator.calculateTransportPrice;

@Service
@RequiredArgsConstructor
public class RentService {

    private final DistanceCalculatorService distanceCalculatorService;
    private final CarRepository carRepository;
    private final CheckCarAvailabilityUtils checkCarAvailabilityUtils;


    public RentSummary placeRent(CarDto carDto, Long userId, String from, String to) {
        return null;
    }

    public List<CarRentDto> showOnlyAvailableCars(RentDateAndPlace rentDateAndPlace) {
        LocalDateTime rentalDate = rentDateAndPlace.getRentalDate();
        LocalDateTime returnDate = rentDateAndPlace.getReturnDate();
        checkCorrectnessDates(rentalDate, returnDate);

        ArrayList<CarRentDto> carRentDtos = new ArrayList<>();
        List<Car> cars = carRepository.findAll();
        for (Car car : cars) {
            if (checkCarAvailabilityUtils.checkCarAvailability(car.getId(), rentDateAndPlace)) {
                carRentDtos.add(createAvailableCar(rentDateAndPlace, rentalDate, returnDate, car));
            }
        }
        return carRentDtos;
    }

    public List<CarRentDto> showCars(RentDateAndPlace rentDateAndPlace) {
        LocalDateTime rentalDate = rentDateAndPlace.getRentalDate();
        LocalDateTime returnDate = rentDateAndPlace.getReturnDate();
        checkCorrectnessDates(rentalDate, returnDate);

        ArrayList<CarRentDto> carRentDtos = new ArrayList<>();
        List<Car> cars = carRepository.findAll();
        for (Car car : cars) {
            if (checkCarAvailabilityUtils.checkCarAvailability(car.getId(), rentDateAndPlace)) {
                carRentDtos.add(createAvailableCar(rentDateAndPlace, rentalDate, returnDate, car));
            } else {
                carRentDtos.add(buildUnavailableCarRentDto(car));
            }
        }
        return carRentDtos;
    }

    private CarRentDto createAvailableCar(RentDateAndPlace rentDateAndPlace, LocalDateTime rentalDate, LocalDateTime returnDate, Car car) {
        long days = DAYS.between(rentalDate, returnDate);
        BigDecimal grossValue = calculateGrossValue(car, days);
        BigDecimal rentalPrice = calculateTransportPrice(rentDateAndPlace.getRentalPlace(), car, distanceCalculatorService);
        BigDecimal returnPrice = calculateTransportPrice(rentDateAndPlace.getReturnPlace(), car, distanceCalculatorService);
        return buildAvailableCarRentDto(car, grossValue, rentalPrice, returnPrice, days);
    }


    private static void checkCorrectnessDates(LocalDateTime rentalDate, LocalDateTime returnDate) {
        if (rentalDate.isAfter(returnDate))
            throw new RuntimeException("Data oddania musi być później niż data wypożyczenia");
        if (rentalDate.isBefore(LocalDateTime.now()))
            throw new RuntimeException("Data wypożyczenia musi być później niż obecna data i godzina");
    }

    private CarRentDto buildAvailableCarRentDto(Car car, BigDecimal grossValue, BigDecimal rentalPrice, BigDecimal returnPrice, long days) {
        return CarRentDto.builder()
                .brand(car.getBrand())
                .model(car.getModel())
                .year(car.getYear())
                .carTechnicalSpecification(car.getCarTechnicalSpecification())
                .deposit(car.getCarPrice().getDeposit())
                .distanceLimit(car.getCarPrice().getDistanceLimit())
                .days(days)
                .grossValue(grossValue)
                .rentalPrice(rentalPrice)
                .returnPrice(returnPrice)
                .isAvailable(true)
                .build();
    }

    private static CarRentDto buildUnavailableCarRentDto(Car car) {
        return CarRentDto.builder()
                .brand(car.getBrand())
                .model(car.getModel())
                .year(car.getYear())
                .carTechnicalSpecification(car.getCarTechnicalSpecification())
                .deposit(car.getCarPrice().getDeposit())
                .distanceLimit(car.getCarPrice().getDistanceLimit())
                .isAvailable(false)
                .build();
    }

}
