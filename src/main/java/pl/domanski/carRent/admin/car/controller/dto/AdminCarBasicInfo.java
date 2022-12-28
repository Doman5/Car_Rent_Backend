package pl.domanski.carRent.admin.car.controller.dto;

import lombok.Builder;
import lombok.Getter;
import pl.domanski.carRent.admin.car.model.AdminBodyType;

@Getter
@Builder
public class AdminCarBasicInfo {
    private Long id;
    private String brand;
    private String model;
    private int year;
    private AdminBodyType type;
}
