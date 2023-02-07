package pl.domanski.carrent.worker.common.model;

import lombok.Getter;
import pl.domanski.carrent.car.model.BodyType;
import pl.domanski.carrent.common.model.CarPrice;
import pl.domanski.carrent.common.model.CarTechnicalSpecification;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "car")
@Getter
public class WorkerCar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    private int year;
    @Enumerated(EnumType.STRING)
    //create worker body type in worker.car
    private BodyType bodyType;
    private String slug;
    @OneToOne
    // create worker technical spec
    private CarTechnicalSpecification carTechnicalSpecification;
    @OneToOne
    //create worker car price
    private CarPrice carPrice;
    private Long categoryId;
}
