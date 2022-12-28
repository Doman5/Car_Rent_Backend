package pl.domanski.carRent.customer.rent.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.domanski.carRent.customer.rent.controller.dto.CarRentDto;
import pl.domanski.carRent.customer.rent.controller.dto.RentDateAndPlace;
import pl.domanski.carRent.customer.rent.controller.dto.RentDto;
import pl.domanski.carRent.customer.rent.model.SortingType;
import pl.domanski.carRent.customer.rent.model.dto.RentSummary;
import pl.domanski.carRent.customer.rent.service.RentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rent")
public class RentController {

    private final RentService rentService;

    @GetMapping("/cars")
    public List<CarRentDto> showCarsToRent(RentDateAndPlace rentDateAndPlace,
                                           @RequestParam(required = false) boolean onlyAvailable,
                                           @RequestParam(required = false) SortingType sortedByPrice) {
        return rentService.showCars(rentDateAndPlace, onlyAvailable, sortedByPrice);
    }

    @PostMapping
    public RentSummary placeRent(@RequestBody RentDto rentDto, Long userId) {
        return rentService.placeRent(rentDto, userId);
    }
}
