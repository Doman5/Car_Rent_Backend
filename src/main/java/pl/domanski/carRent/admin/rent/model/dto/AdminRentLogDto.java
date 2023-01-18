package pl.domanski.carRent.admin.rent.model.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class AdminRentLogDto {
    private LocalDateTime created;
    private String note;
}
