package pl.domanski.carRent.admin.adminCar.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AdminCarTechnicalSpecificationDto {
    private Integer power;
    private String engine;
    private String drive;
    private String acceleration;
    private String gearbox;
    private String fuel;
    private String seats;
}
