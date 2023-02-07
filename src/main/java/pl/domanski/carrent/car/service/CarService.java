package pl.domanski.carrent.car.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.domanski.carrent.common.dto.CarBasicInfo;
import pl.domanski.carrent.common.mapper.CarMapper;
import pl.domanski.carrent.common.model.Car;
import pl.domanski.carrent.common.model.SortingType;
import pl.domanski.carrent.common.repository.CarRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static pl.domanski.carrent.common.utils.SortingUtils.sortCars;

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
        return sortCars(cars,
                SortingType.get(sorting),
                Comparator.comparing(CarBasicInfo::getPriceMonth));
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

    public List<CarBasicInfo> getThreeRecommendedCars(String slug) {
        Car originalCar = carRepository.findBySlug(slug).orElseThrow();
        List<Car> carsWithTheSameCategory = carRepository.findAllByCategoryId(originalCar.getCategoryId()).stream()
                .filter(car -> !car.getSlug().equals(slug))
                .collect(Collectors.toList());
        Collections.shuffle(carsWithTheSameCategory);
        return carsWithTheSameCategory.stream()
                .limit(3)
                .map(CarMapper::mapToCarBasicInfo)
                .toList();
    }
}
