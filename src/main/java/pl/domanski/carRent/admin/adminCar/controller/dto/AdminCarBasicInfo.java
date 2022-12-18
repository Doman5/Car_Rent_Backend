package pl.domanski.carRent.admin.adminCar.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AdminCarBasicInfo {
    private Long id;
    private String brand;
    private String model;
    private int year;
}
