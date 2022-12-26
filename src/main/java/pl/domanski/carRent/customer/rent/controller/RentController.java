package pl.domanski.carRent.customer.rent.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.domanski.carRent.customer.rent.controller.dto.RentDateAndPlace;
import pl.domanski.carRent.customer.rent.controller.dto.CarRentDto;
import pl.domanski.carRent.customer.rent.model.dto.CarDto;
import pl.domanski.carRent.customer.rent.model.dto.RentSummary;
import pl.domanski.carRent.customer.rent.service.RentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rent")
public class RentController {

    private final RentService rentService;

    @GetMapping("/cars")
    public List<CarRentDto> showCarsToRent(RentDateAndPlace rentDateAndPlace) {
        return rentService.showCars(rentDateAndPlace);
    }

    @GetMapping("/cars")
    public List<CarRentDto> showAvailableCarsToRent(RentDateAndPlace rentDateAndPlace) {
        return rentService.showOnlyAvailableCars(rentDateAndPlace);
    }

    @PostMapping("/rent")
    public RentSummary placeRent(CarDto carDto, Long userId, String from, String to) {
        return rentService.placeRent(carDto, userId, from, to);
    }
}
