package pl.domanski.carRent.admin.rent.model;

import lombok.Getter;
import lombok.Setter;
import pl.domanski.carRent.admin.car.model.AdminCar;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "rent")
@Getter
@Setter
public class AdminRent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private AdminCar car;
    private Long userId;
    @OneToOne
    private AdminPayment payment;
    @Enumerated(EnumType.STRING)
    private AdminRentStatus rentStatus;
    private String rentalPlace;
    private LocalDateTime rentalDate;
    private String returnPlace;
    private LocalDateTime returnDate;
    private BigDecimal grossValue;
}
