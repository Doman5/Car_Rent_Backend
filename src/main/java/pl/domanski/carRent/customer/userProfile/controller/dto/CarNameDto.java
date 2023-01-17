package pl.domanski.carRent.customer.userProfile.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CarNameDto {
    private String name;
    private String slug;
}
