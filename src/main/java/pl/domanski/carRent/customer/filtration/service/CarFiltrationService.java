package pl.domanski.carRent.customer.filtration.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.domanski.carRent.customer.common.repository.CarRepository;
import pl.domanski.carRent.customer.filtration.service.dto.FiltrationDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CarFiltrationService {

    private final CarRepository carRepository;

    public FiltrationDto getCarFilters() {
        List<Map<String, Long>> brands = getBrandsWithCarsCount();
        List<Map<String, Long>> years = getYearsWithCarsCount();
        return createFilterDto(brands, years);
    }

    private List<Map<String, Long>> getBrandsWithCarsCount() {
        List<Map<String, Long>> brands = new ArrayList<>();
        List<String> brandNames = carRepository.findAllBrandNames();
        for (String brandName : brandNames) {
            Map<String, Long> brandAndNumberOfCars = new HashMap<>();
            Long numberOfCars = carRepository.countByBrand(brandName);
            brandAndNumberOfCars.put(brandName, numberOfCars);
            brands.add(brandAndNumberOfCars);
        }
        return brands;
    }

    private List<Map<String, Long>> getYearsWithCarsCount() {
        List<Map<String, Long>> years = new ArrayList<>();
        List<Integer> carsYears = carRepository.findAllCarsYears();
        for (Integer year : carsYears) {
            Map<String, Long> yearAndNumberOfCars = new HashMap<>();
            Long numberOfCars = carRepository.countByYear(year);
            yearAndNumberOfCars.put(String.valueOf(year), numberOfCars);
            years.add(yearAndNumberOfCars);
        }
        return years;
    }

    private static FiltrationDto createFilterDto(List<Map<String, Long>> brands, List<Map<String, Long>> years) {
        return FiltrationDto.builder()
                .brands(brands)
                .years(years)
                .build();
    }
}
