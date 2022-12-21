package pl.domanski.carRent.customer.car.model;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Getter
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
}
