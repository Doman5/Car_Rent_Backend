package pl.domanski.carRent.admin.adminCar.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminCarDescriptionDto {
    @NotBlank
    private String description;
}
