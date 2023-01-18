package pl.domanski.carRent.admin.rent.model.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class AdminFullRentInfo {
    private Long id;
    private String car;
    private String paymentType;
    private String username;
    private String rentStatus;
    private LocalDateTime rentalDate;
    private String rentalPlace;
    private LocalDateTime returnDate;
    private String returnPlace;
    private BigDecimal grossValue;
    private List<AdminRentLogDto> rentLogs;
}
