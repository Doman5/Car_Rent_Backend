package pl.domanski.carRent.customer.filtration.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.domanski.carRent.customer.filtration.service.CarFiltrationService;
import pl.domanski.carRent.customer.filtration.service.dto.FiltrationDto;

@RestController
@RequiredArgsConstructor
public class FilterController {

    private final CarFiltrationService carFilterService;

    @GetMapping("/filters")
    public FiltrationDto getFilterFields() {
        return carFilterService.getCarFilters();
    }
}
