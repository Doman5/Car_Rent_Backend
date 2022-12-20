package pl.domanski.carRent.admin.adminCar.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminCarTechnicalSpecificationDto {
    @Min(1)
    private Integer power;
    @NotBlank
    private String engine;
    @NotBlank
    private String drive;
    @NotBlank
    private String acceleration;
    @NotBlank
    private String gearbox;
    @NotBlank
    private String fuel;
    @NotBlank
    private String seats;
}
