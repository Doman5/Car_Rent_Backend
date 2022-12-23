package pl.domanski.carRent.customer.filtration.service.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public class FiltrationDto {
    Map<String,Long> brands;
    Map<Integer,Long> years;
}
