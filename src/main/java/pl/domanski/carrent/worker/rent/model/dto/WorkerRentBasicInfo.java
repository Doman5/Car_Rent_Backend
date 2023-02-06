package pl.domanski.carRent.worker.rent.model.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class WorkerRentBasicInfo {
    private Long id;
    private String slug;
    private String rentStatus;
    private LocalDateTime rentalDate;
}
