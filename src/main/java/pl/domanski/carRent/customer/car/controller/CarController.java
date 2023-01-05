package pl.domanski.carRent.customer.car.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.domanski.carRent.customer.car.model.dto.FiltrationDto;
import pl.domanski.carRent.customer.car.service.CarFiltrationService;
import pl.domanski.carRent.customer.car.service.CarService;
import pl.domanski.carRent.customer.common.dto.CarBasicInfo;
import pl.domanski.carRent.customer.common.model.Car;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;
    private final CarFiltrationService carFilterService;

    @GetMapping
    public List<CarBasicInfo> getAllCarsByFilter(@RequestParam(value = "brand", required = false) List<String> brands,
                                                 @RequestParam(value = "year", required = false) List<Integer> year,
                                                 @RequestParam(value = "type", required = false) List<String> type,
                                                 @RequestParam(value = "sort", defaultValue = "Malejaco") String sorting) {
        if (brands == null && year == null && type == null) {
            return carService.getAllCars(sorting);
        } else {
            return carService.getCarsByBrandYearAndType(brands, year, type, sorting);
        }
    }

    @GetMapping("/{slug}")
    public Car getCarBySlug(@PathVariable String slug) {
        return carService.getCar(slug);
    }

    @GetMapping("/filters")
    public FiltrationDto getFilterFields() {
        return carFilterService.getCarFilters();
    }

    @GetMapping("/sort")
    public List<String> getSortingValuesList() {
        return carFilterService.getCarSortingValues();
    }
}
