package pl.domanski.carRent.customer.rent.mapper;

import pl.domanski.carRent.customer.common.model.Car;
import pl.domanski.carRent.customer.rent.model.dto.CarToRentDto;
import pl.domanski.carRent.customer.rent.model.dto.RentDateAndPlace;

import java.math.BigDecimal;

public class CarToRentMapper {

    public static CarToRentDto createAvailableCarRentDto(Car car, BigDecimal grossValue, BigDecimal rentalPrice, BigDecimal returnPrice, long days, RentDateAndPlace rentDateAndPlace) {
        return CarToRentDto.builder()
                .carId(car.getId())
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
                .rentalDate(rentDateAndPlace.getRentalDate())
                .returnDate(rentDateAndPlace.getReturnDate())
                .rentalPlace(rentDateAndPlace.getRentalPlace())
                .returnPlace(rentDateAndPlace.getReturnPlace())
                .isAvailable(true)
                .build();
    }

    public  static CarToRentDto createUnavailableCarRentDto(Car car, BigDecimal grossValue) {
        return CarToRentDto.builder()
                .brand(car.getBrand())
                .model(car.getModel())
                .year(car.getYear())
                .carTechnicalSpecification(car.getCarTechnicalSpecification())
                .grossValue(grossValue)
                .deposit(car.getCarPrice().getDeposit())
                .distanceLimit(car.getCarPrice().getDistanceLimit())
                .isAvailable(false)
                .build();
    }
}