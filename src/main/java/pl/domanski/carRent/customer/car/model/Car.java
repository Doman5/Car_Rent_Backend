package pl.domanski.carRent.customer.car.model;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
@Getter
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    private int year;
    private String slug;
    @OneToOne
    @JoinColumn(name = "car_technical_specification_id")
    private CarTechnicalSpecification carTechnicalSpecification;
    @OneToMany
    @JoinColumn(name = "carId")
    private List<CarEquipment> equipments;
    @OneToMany
    @JoinColumn(name = "carId")
    private List<CarDescription> descriptions;
    @OneToOne
    @JoinColumn(name = "car_price_id")
    private CarPrice carPrice;
    @OneToMany
    @JoinColumn(name = "carId")
    private List<CarPhoto> photos;
    private Long categoryId;
}
