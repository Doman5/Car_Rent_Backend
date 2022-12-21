package pl.domanski.carRent.customer.car.controller.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class CarBasicInfo {
    private String brand;
    private String model;
    private Integer year;
    private BigDecimal priceMonth;
    private String acceleration;
    private String gearbox;
    private Integer power;
    private String seats;
}
