package pl.domanski.carrent.userProfile.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CarNameDto {
    private String name;
    private String slug;
}
