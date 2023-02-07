package pl.domanski.carrent.rent.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.domanski.carrent.rent.model.dto.CarToRentDto;
import pl.domanski.carrent.rent.model.dto.InitRent;
import pl.domanski.carrent.rent.model.dto.RentDateAndPlace;
import pl.domanski.carrent.rent.model.dto.RentDto;
import pl.domanski.carrent.rent.model.dto.RentSummary;
import pl.domanski.carrent.rent.service.RentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rent")
public class RentController {

    private final RentService rentService;

    @GetMapping("/cars")
    public List<CarToRentDto> showCarsToRent(RentDateAndPlace rentDateAndPlace,
                                             @RequestParam(required = false) boolean onlyAvailable,
                                             @RequestParam(defaultValue = "Malejaco") String sortedByPrice) {
        return rentService.showCars(rentDateAndPlace, onlyAvailable, sortedByPrice);

    }

    @PostMapping
    public RentSummary placeRent(@RequestBody RentDto rentDto,
                                 @AuthenticationPrincipal Long userId) {
        return rentService.placeRent(rentDto, userId);
    }

    @GetMapping("/initData")
    public InitRent showSortingValues() {
        return rentService.getInitData();
    }

}
