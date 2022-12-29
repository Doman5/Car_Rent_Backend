package pl.domanski.carRent.customer.rent.mapper;

import pl.domanski.carRent.customer.common.model.Car;
import pl.domanski.carRent.customer.rent.model.dto.RentDto;
import pl.domanski.carRent.customer.rent.model.Payment;
import pl.domanski.carRent.customer.rent.model.Rent;
import pl.domanski.carRent.customer.rent.model.RentStatus;
import pl.domanski.carRent.customer.rent.model.dto.RentSummary;

import java.math.BigDecimal;

public class RentMapper {

    public static RentSummary createRentSummary(Car car, Rent rent) {
        return RentSummary.builder()
                .id(rent.getId())
                .carName(car.getSlug())
                .finalPrice(rent.getGrossValue())
                .rentalPlace(rent.getRentalPlace())
                .rentalDate(rent.getRentalDate())
                .returnPlace(rent.getReturnPlace())
                .returnDate(rent.getReturnDate())
                .payment(rent.getPayment())
                .rentStatus(RentStatus.NEW)
                .build();
    }

    public static Rent createRent(RentDto carDto, Long userId, Car car, Payment payment, BigDecimal grossValue) {
        return Rent.builder()
                .car(car)
                .userId(userId)
                .payment(payment)
                .rentalPlace(carDto.getRentalPlace())
                .returnPlace(carDto.getReturnPlace())
                .rentalDate(carDto.getRentalDate())
                .returnDate(carDto.getReturnDate())
                .grossValue(grossValue)
                .rentStatus(RentStatus.NEW)
                .build();
    }
}
