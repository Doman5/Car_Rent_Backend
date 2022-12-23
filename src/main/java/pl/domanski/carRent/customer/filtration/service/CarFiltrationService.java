package pl.domanski.carRent.customer.filtration.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.domanski.carRent.customer.common.repository.CarRepository;
import pl.domanski.carRent.customer.filtration.service.dto.FiltrationDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CarFiltrationService {

    private final CarRepository carRepository;

    public FiltrationDto getCarFilters() {
        Map<String, Long> brands = getBrandsWithCarsCount();
        Map<Integer, Long> years = getYearsWithCarsCount();
        return createFilterDto(brands, years);
    }

    private Map<String, Long> getBrandsWithCarsCount() {
        Map<String, Long> brandAndNumberOfCars = new HashMap<>();
        List<String> brandNames = carRepository.findAllBrandNames();
        for (String brandName : brandNames) {
            Long numberOfCars = carRepository.countByBrand(brandName);
            brandAndNumberOfCars.put(brandName, numberOfCars);
        }
        return brandAndNumberOfCars;
    }

    private Map<Integer, Long> getYearsWithCarsCount() {
        Map<Integer, Long> yearAndNumberOfCars = new HashMap<>();
        List<Integer> carsYears = carRepository.findAllCarsYears();
        for (Integer year : carsYears) {
            Long numberOfCars = carRepository.countByYear(year);
            yearAndNumberOfCars.put(year, numberOfCars);
        }
        return yearAndNumberOfCars;
    }

    private static FiltrationDto createFilterDto(Map<String, Long> brands, Map<Integer, Long> years) {
        return FiltrationDto.builder()
                .brands(brands)
                .years(years)
                .build();
    }
}
