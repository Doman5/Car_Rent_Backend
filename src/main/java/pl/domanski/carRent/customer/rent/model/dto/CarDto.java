package pl.domanski.carRent.customer.rent.model.dto;

import lombok.Getter;
import pl.domanski.carRent.customer.car.model.CarPhoto;
import pl.domanski.carRent.customer.common.model.CarPrice;
import pl.domanski.carRent.customer.common.model.CarTechnicalSpecification;

@Getter
public class CarDto {
    private Long id;
    private String brand;
    private String model;
    private int year;
    private CarTechnicalSpecification carTechnicalSpecification;
    private CarPrice carPrice;
    private CarPhoto photo;
}
