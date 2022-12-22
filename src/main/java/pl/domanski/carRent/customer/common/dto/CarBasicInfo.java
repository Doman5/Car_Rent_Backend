package pl.domanski.carRent.customer.common.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class CarBasicInfo {
    private String brand;
    private String model;
    private Integer year;
    private String slug;
    private BigDecimal priceMonth;
    private String acceleration;
    private String gearbox;
    private Integer power;
    private String seats;
}
