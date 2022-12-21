package pl.domanski.carRent.customer.car.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.domanski.carRent.customer.car.controller.dto.CarBasicInfo;
import pl.domanski.carRent.customer.car.model.Car;
import pl.domanski.carRent.customer.car.repository.CarRepository;
import pl.domanski.carRent.customer.car.service.mapper.CarMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    public List<CarBasicInfo> getAllCars() {
        return carRepository.findAll().stream()
                .map(CarMapper::mapToCArBasicInfo).toList();
    }

    public Car getCar(String slug) {
        return carRepository.findBySlug(slug).orElseThrow();
    }
}
