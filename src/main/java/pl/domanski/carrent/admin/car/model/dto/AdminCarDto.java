package pl.domanski.carrent.admin.car.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.domanski.carrent.admin.common.dto.AdminCategoryDto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminCarDto {
    @NotBlank
    private String brand;
    @NotBlank
    private String model;
    @Min(1)
    private int year;
    private String bodyType;
    private AdminCarTechnicalSpecificationDto carTechnicalSpecification;
    private List<AdminCarEquipmentDto> equipments;
    private List<AdminCarDescriptionDto> descriptions;
    private AdminCarPriceDto carPrice;
    private List<AdminCarPhotoDto> photos;
    private AdminCategoryDto category;

    public AdminCarDto(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }
}
