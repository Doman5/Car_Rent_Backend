package pl.domanski.carRent.admin.car.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class AdminCarPhotoDto {
    private Long id;
    private String photo;
}
