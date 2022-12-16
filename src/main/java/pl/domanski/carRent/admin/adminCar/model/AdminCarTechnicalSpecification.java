package pl.domanski.carRent.admin.adminCar.model;


import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "car_technical_specification")
@Getter
public class AdminCarTechnicalSpecification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer power;
    private String engine;
    private String drive;
    private String acceleration;
    private String gearbox;
    private String fuel;
    private String seats;
}
