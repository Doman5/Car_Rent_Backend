package pl.domanski.carrent.admin.rent.model.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class AdminRentDto {
    private Long id;
    private String car;
    private String paymentType;
    private String rentStatus;
    private LocalDateTime rentalDate;
    private LocalDateTime returnDate;
    private BigDecimal finalPrice;
}
