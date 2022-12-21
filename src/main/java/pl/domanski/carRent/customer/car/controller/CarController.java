package pl.domanski.carRent.customer.car.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.domanski.carRent.customer.car.controller.dto.CarBasicInfo;
import pl.domanski.carRent.customer.car.model.Car;
import pl.domanski.carRent.customer.car.service.CarService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    @GetMapping
    public List<CarBasicInfo> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping("/{slug}")
    public Car getCarBySlug(@PathVariable String slug) {
        return carService.getCar(slug);
    }
}
