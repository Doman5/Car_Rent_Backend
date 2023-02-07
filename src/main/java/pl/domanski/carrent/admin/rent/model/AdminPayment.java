package pl.domanski.carrent.admin.rent.model;

import lombok.Getter;
import pl.domanski.carrent.rent.model.PaymentType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "payment")
@Getter
public class AdminPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(value = EnumType.STRING)
    private PaymentType type;
    private boolean defaultPayment;
    private String note;
}

