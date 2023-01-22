package pl.domanski.carRent.admin.car.model.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Min;
import java.math.BigDecimal;

@Getter
@Builder
public class AdminCarPriceDto {
    @Min(1)
    private BigDecimal priceDay;
    @Min(1)
    private BigDecimal priceHalfWeek;
    @Min(1)
    private BigDecimal priceWeek;
    @Min(1)
    private BigDecimal priceTwoWeeks;
    @Min(1)
    private BigDecimal priceMonth;
    @Min(1)
    private Integer deposit;
    @Min(1)
    private Integer distanceLimit;
    @Min(1)
    private BigDecimal distanceLimitPenalty;
    @Min(1)
    private BigDecimal transportPricePerKm;
}
