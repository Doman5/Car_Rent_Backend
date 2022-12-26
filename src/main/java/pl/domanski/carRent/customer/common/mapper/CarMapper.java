package pl.domanski.carRent.customer.common.mapper;

import pl.domanski.carRent.customer.common.dto.CarBasicInfo;
import pl.domanski.carRent.customer.common.model.Car;

public class CarMapper {

    public static CarBasicInfo mapToCarBasicInfo(Car car) {
        return CarBasicInfo.builder()
                .brand(car.getBrand())
                .model(car.getModel())
                .year(car.getYear())
                .slug(car.getSlug())
                .priceMonth(car.getCarPrice().getPriceMonth())
                .power(car.getCarTechnicalSpecification().getPower())
                .gearbox(car.getCarTechnicalSpecification().getGearbox())
                .acceleration(car.getCarTechnicalSpecification().getAcceleration())
                .seats(car.getCarTechnicalSpecification().getSeats())
                .build();
    }
}
