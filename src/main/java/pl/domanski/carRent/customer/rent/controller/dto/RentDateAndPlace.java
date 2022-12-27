package pl.domanski.carRent.customer.rent.controller.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Builder
public class RentDateAndPlace {
    private String rentalPlace;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime rentalDate;
    private String returnPlace;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime returnDate;
}
