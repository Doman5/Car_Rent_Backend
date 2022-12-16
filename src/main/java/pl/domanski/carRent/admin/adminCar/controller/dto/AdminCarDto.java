package pl.domanski.carRent.admin.adminCar.controller.dto;

import lombok.Getter;
import pl.domanski.carRent.admin.adminCar.model.AdminCarTechnicalSpecification;

@Getter
public class AdminCarDto {
    private Long id;
    private String brand;
    private String model;
    private int year;
    private AdminCarTechnicalSpecification adminCarTechnicalSpecification;
}
