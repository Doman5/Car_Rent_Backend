package pl.domanski.carRent.admin.adminCar.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminCarTechnicalSpecificationDto {
    private Integer power;
    private String engine;
    private String drive;
    private String acceleration;
    private String gearbox;
    private String fuel;
    private String seats;
}
