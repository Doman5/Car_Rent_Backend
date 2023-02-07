package pl.domanski.carrent.rent.mapper;

import pl.domanski.carrent.common.model.Car;
import pl.domanski.carrent.rent.model.dto.RentDto;
import pl.domanski.carrent.common.model.Payment;
import pl.domanski.carrent.rent.model.Rent;
import pl.domanski.carrent.common.model.RentStatus;
import pl.domanski.carrent.rent.model.dto.RentSummary;

import java.math.BigDecimal;

public class RentMapper {

    public static RentSummary createRentSummary(Car car, Rent rent) {
        return RentSummary.builder()
                .id(rent.getId())
                .carName(car.getSlug())
                .priceWithoutDeposit(rent.getPriceWithoutDeposit())
                .deposit(rent.getDeposit())
                .finalPrice(rent.getFinalPrice())
                .rentalPlace(rent.getRentalPlace())
                .rentalDate(rent.getRentalDate())
                .returnPlace(rent.getReturnPlace())
                .returnDate(rent.getReturnDate())
                .paymentName(rent.getPayment().getName())
                .build();
    }

    public static Rent createRent(RentDto carDto, Long userId, Car car, Payment payment, BigDecimal finalPrice, BigDecimal priceWithTax, BigDecimal deposit) {
        return Rent.builder()
                .car(car)
                .userId(userId)
                .payment(payment)
                .rentalPlace(carDto.getRentalPlace())
                .returnPlace(carDto.getReturnPlace())
                .rentalDate(carDto.getRentalDate())
                .returnDate(carDto.getReturnDate())
                .priceWithoutDeposit(priceWithTax)
                .deposit(deposit)
                .finalPrice(finalPrice)
                .rentStatus(RentStatus.NEW)
                .build();
    }
}
