package pl.domanski.carRent.customer.filtration.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.domanski.carRent.customer.car.model.BodyType;
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
        Map<String, Long> bodyType = getBodyTypeCarsCount();
        return createFilterDto(brands, years, bodyType);
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

    private Map<String, Long> getBodyTypeCarsCount() {
        Map<String, Long> bodyTypeAndNumberOfCars = new HashMap<>();
        List<BodyType> carsBodyType = carRepository.findAllCarsBodyTypes();
        for (BodyType type : carsBodyType) {
            Long numberOfCars = carRepository.countByBodyType(type);
            bodyTypeAndNumberOfCars.put(type.getName(), numberOfCars);
        }
        return bodyTypeAndNumberOfCars;
    }

    private static FiltrationDto createFilterDto(Map<String, Long> brands, Map<Integer, Long> years, Map<String, Long> bodyType) {
        return FiltrationDto.builder()
                .brands(brands)
                .years(years)
                .bodyTypes(bodyType)
                .build();
    }
}
