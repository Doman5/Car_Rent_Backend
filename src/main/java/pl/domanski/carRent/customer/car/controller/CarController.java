package pl.domanski.carRent.customer.car.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.domanski.carRent.customer.common.dto.CarBasicInfo;
import pl.domanski.carRent.customer.common.model.Car;
import pl.domanski.carRent.customer.car.service.CarService;
import pl.domanski.carRent.customer.car.service.CarFiltrationService;
import pl.domanski.carRent.customer.car.model.dto.FiltrationDto;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    @GetMapping
    public List<CarBasicInfo> getAllCarsByFilter(@RequestParam(required = false) Map<String, String> params) {
        return carService.searchCarsByFilters(params);
    }

    @GetMapping("/{slug}")
    public Car getCarBySlug(@PathVariable String slug) {
        return carService.getCar(slug);
    }

    private final CarFiltrationService carFilterService;

    @GetMapping("/filters")
    public FiltrationDto getFilterFields() {
        return carFilterService.getCarFilters();
    }

    @GetMapping("/sorters")
    public List<String> getSortingValuesList() {
        return carFilterService.getCarSortingValues();
    }
}
