package pl.domanski.carRent.customer.rent.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.domanski.carRent.customer.rent.controller.dto.CarRentDto;
import pl.domanski.carRent.customer.rent.controller.dto.RentDateAndPlace;
import pl.domanski.carRent.customer.rent.controller.dto.RentDto;
import pl.domanski.carRent.customer.rent.model.dto.RentSummary;
import pl.domanski.carRent.customer.rent.service.RentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rent")
public class RentController {

    private static final boolean ONLY_AVAILABLE = true;
    private static final boolean ALL_CARS = false;
    private final RentService rentService;

    @GetMapping("/cars")
    public List<CarRentDto> showCarsToRent(RentDateAndPlace rentDateAndPlace) {
        return rentService.showCars(rentDateAndPlace, ALL_CARS);
    }

    @GetMapping("/cars/available")
    public List<CarRentDto> showAvailableCarsToRent(RentDateAndPlace rentDateAndPlace) {
        return rentService.showCars(rentDateAndPlace, ONLY_AVAILABLE);
    }

    @PostMapping
    public RentSummary placeRent(@RequestBody RentDto rentDto, Long userId) {
        return rentService.placeRent(rentDto, userId);
    }
}
