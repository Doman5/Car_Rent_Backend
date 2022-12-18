package pl.domanski.carRent.admin.adminCar.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "car_price")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class AdminCarPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal priceDay;
    private BigDecimal priceHalfWeek;
    private BigDecimal priceWeek;
    private BigDecimal priceTwoWeeks;
    private BigDecimal priceMonth;
}
