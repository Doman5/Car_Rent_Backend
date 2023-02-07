package pl.domanski.carrent.worker.rent.model.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class WorkerRentDto {
    private Long id;
    private Long carId;
    private String slug;
    private Long userId;
    private String rentStatus;
    private String rentalPlace;
    private LocalDateTime rentalDate;
    private String returnPlace;
    private LocalDateTime returnDate;
    private BigDecimal grossValue;
}
