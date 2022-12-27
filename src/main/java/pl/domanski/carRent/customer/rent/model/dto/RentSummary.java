package pl.domanski.carRent.customer.rent.model.dto;

import lombok.Builder;
import lombok.Getter;
import pl.domanski.carRent.customer.rent.model.Payment;
import pl.domanski.carRent.customer.rent.model.RentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class RentSummary {
    private Long id;
    private String carName;
    private BigDecimal finalPrice;
    private String rentalPlace;
    private LocalDateTime rentalDate;
    private String returnPlace;
    private LocalDateTime returnDate;
    private Payment payment;
    private RentStatus rentStatus;
}
