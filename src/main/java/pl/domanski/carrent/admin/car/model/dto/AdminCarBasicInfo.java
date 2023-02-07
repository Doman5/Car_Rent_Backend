package pl.domanski.carrent.admin.car.model.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AdminCarBasicInfo {
    private Long id;
    private String brand;
    private String model;
    private int year;
    private String type;
}
