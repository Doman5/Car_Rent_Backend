package pl.domanski.carRent.customer.car.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.domanski.carRent.customer.common.dto.CarBasicInfo;
import pl.domanski.carRent.customer.common.mapper.CarMapper;
import pl.domanski.carRent.customer.common.model.Car;
import pl.domanski.carRent.customer.common.model.SortingType;
import pl.domanski.carRent.customer.common.repository.CarRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static pl.domanski.carRent.customer.common.utils.SortingUtils.sortCars;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    public Car getCar(String slug) {
        return carRepository.findBySlug(slug).orElseThrow();
    }

    public List<CarBasicInfo> getAllCars(String sorting) {
        List<CarBasicInfo> cars = carRepository.findAll().stream()
                .map(CarMapper::mapToCarBasicInfo)
                .toList();
        Optional<SortingType> sortType = SortingType.get(sorting);
        sortCars(cars, sortType, Comparator.comparing(CarBasicInfo::getPriceMonth));
        return cars;
    }

    public List<CarBasicInfo> getCarsByBrandYearAndType(List<String> brands, List<Integer> years, List<String> types, String sorting) {
        List<Car> allCars = carRepository.findAll();

        List<Car> filteredCars = new ArrayList<>();
        for (Car car : allCars) {
            if (brands != null && brands.contains(car.getBrand())) {
                filteredCars.add(car);
                continue;
            }
            if (years != null && years.contains(car.getYear()))  {
                filteredCars.add(car);
                continue;
            }

            if(types != null && types.contains(car.getBodyType().getName())) {
                filteredCars.add(car);
            }
        }
        return sortCars(filteredCars.stream()
                .map(CarMapper::mapToCarBasicInfo)
                .toList(),
                SortingType.get(sorting),
                Comparator.comparing(CarBasicInfo::getPriceMonth)
                );
    }
}
