package pl.domanski.carRent.customer.rent.controller.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class RentDto {
    @NotNull
    private Long carId;
    @Min(1)
    private BigDecimal grossValue;
    @Min(0)
    private BigDecimal rentalPrice;
    @Min(0)
    private BigDecimal returnPrice;
    @NotBlank
    private String rentalPlace;
    @NotBlank
    private String returnPlace;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime rentalDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime returnDate;
    @NotNull
    private Long paymentId;
}
