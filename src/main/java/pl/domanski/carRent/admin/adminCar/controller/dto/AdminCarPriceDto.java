package pl.domanski.carRent.admin.adminCar.controller.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class AdminCarPriceDto {
    private BigDecimal priceDay;
    private BigDecimal priceHalfWeek;
    private BigDecimal priceWeek;
    private BigDecimal priceTwoWeeks;
    private BigDecimal priceMonth;
}
