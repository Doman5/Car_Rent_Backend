package pl.domanski.carRent.customer.filter.service.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@Builder
public class FiltersDto {
    List<Map<String,Long>> brands;
    List<Map<String,Long>> years;
}
