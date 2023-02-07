package pl.domanski.carrent.rent.model.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class RentSummary {
    private Long id;
    private String carName;
    private BigDecimal priceWithoutDeposit;
    private BigDecimal deposit;
    private BigDecimal finalPrice;
    private String rentalPlace;
    private LocalDateTime rentalDate;
    private String returnPlace;
    private LocalDateTime returnDate;
    private String paymentName;
}
