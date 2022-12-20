package pl.domanski.carRent.admin.common.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
public class AdminCategoryDto {
    @NotBlank
    private String name;
}
