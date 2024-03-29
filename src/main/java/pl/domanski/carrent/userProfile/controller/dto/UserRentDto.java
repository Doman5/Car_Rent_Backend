package pl.domanski.carrent.userProfile.controller.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class UserRentDto {
    private Long id;
    private CarNameDto car;
    private String paymentType;
    private String rentStatus;
    private String rentalPlace;
    private LocalDateTime rentalDate;
    private String returnPlace;
    private LocalDateTime returnDate;
    private BigDecimal priceWithoutDeposit;
    private BigDecimal deposit;
    private BigDecimal finalPrice;
}
