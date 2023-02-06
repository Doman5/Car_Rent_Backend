package pl.domanski.carRent.worker.rent.model;

import lombok.Getter;
import lombok.Setter;
import pl.domanski.carRent.worker.common.model.WorkerCar;

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
public class WorkerRent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private WorkerCar car;
    private Long userId;
    @OneToOne
    private WorkerPayment payment;
    @Enumerated(EnumType.STRING)
    private WorkerRentStatus rentStatus;
    private String rentalPlace;
    private LocalDateTime rentalDate;
    private String returnPlace;
    private LocalDateTime returnDate;
    private BigDecimal grossValue;
}
