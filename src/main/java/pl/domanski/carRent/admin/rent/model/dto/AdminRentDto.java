package pl.domanski.carRent.admin.rent.model.dto;

import lombok.Builder;
import lombok.Getter;
import pl.domanski.carRent.admin.rent.model.AdminRentStatus;

import java.math.BigDecimal;

@Getter
@Builder
public class AdminRentDto {
    private Long id;
    private AdminRentStatus rentStatus;
    private BigDecimal grossValue;

}
