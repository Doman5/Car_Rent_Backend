package pl.domanski.carRent.admin.adminCar.controller.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;


@Getter
@Builder
public class AdminCarDto {
    private Long id;
    @NotBlank
    private String brand;
    @NotBlank
    private String model;
    @Min(0)
    private int year;
    private AdminCarTechnicalSpecificationDto carTechnicalSpecificationDto;
    private List<AdminCarEquipmentDto> equipments;
    private List<AdminCarDescriptionDto> descriptions;
    private AdminCarPriceDto carPrice;
    private List<AdminCarPhotoDto> photos;
}
