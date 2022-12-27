package pl.domanski.carRent.customer.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal priceDay;
    private BigDecimal priceHalfWeek;
    private BigDecimal priceWeek;
    private BigDecimal priceTwoWeeks;
    private BigDecimal priceMonth;
    private Integer deposit;
    private Integer distanceLimit;
    private BigDecimal distanceLimitPenalty;
    private BigDecimal transportPricePerKm;
}
