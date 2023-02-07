package pl.domanski.carrent.common.mapper;

import pl.domanski.carrent.common.dto.CarBasicInfo;
import pl.domanski.carrent.common.model.Car;

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
                .photo(!car.getPhotos().isEmpty() ? car.getPhotos().get(0).getPhoto() : "")
                .build();
    }
}
