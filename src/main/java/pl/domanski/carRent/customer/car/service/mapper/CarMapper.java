package pl.domanski.carRent.customer.car.service.mapper;

import pl.domanski.carRent.customer.car.controller.dto.CarBasicInfo;
import pl.domanski.carRent.customer.car.model.Car;

public class CarMapper {

    public static CarBasicInfo mapToCArBasicInfo(Car car) {
        return CarBasicInfo.builder()
                .brand(car.getBrand())
                .model(car.getModel())
                .year(car.getYear())
                .priceMonth(car.getCarPrice().getPriceMonth())
                .power(car.getCarTechnicalSpecification().getPower())
                .gearbox(car.getCarTechnicalSpecification().getGearbox())
                .acceleration(car.getCarTechnicalSpecification().getAcceleration())
                .seats(car.getCarTechnicalSpecification().getSeats())
                .build();
    }
}
