package pl.domanski.carRent.customer.filter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.domanski.carRent.customer.filter.service.CarFilterService;
import pl.domanski.carRent.customer.filter.service.dto.FiltersDto;

@RestController
@RequiredArgsConstructor
public class FilterController {

    private final CarFilterService carFilterService;

    @GetMapping("/filters")
    public FiltersDto getFilterFields() {
        return carFilterService.getCarFilters();
    }
}
