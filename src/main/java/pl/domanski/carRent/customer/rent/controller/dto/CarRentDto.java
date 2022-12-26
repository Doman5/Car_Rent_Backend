package pl.domanski.carRent.customer.rent.controller.dto;

import lombok.Builder;
import lombok.Getter;
import pl.domanski.carRent.customer.common.model.CarTechnicalSpecification;

import java.math.BigDecimal;

@Getter
@Builder
public class CarRentDto {
    private String brand;
    private String model;
    private Integer year;
    private CarTechnicalSpecification carTechnicalSpecification;
    private Integer deposit;
    private Integer distanceLimit;
    private BigDecimal distanceLimitPenalty;
    private BigDecimal grossValue;
    private BigDecimal rentalPrice;
    private BigDecimal returnPrice;
    private Long days;
    private boolean isAvailable;
}
