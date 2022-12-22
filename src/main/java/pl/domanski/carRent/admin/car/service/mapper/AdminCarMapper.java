package pl.domanski.carRent.admin.car.service.mapper;

import pl.domanski.carRent.admin.car.controller.dto.AdminCarDescriptionDto;
import pl.domanski.carRent.admin.car.controller.dto.AdminCarDto;
import pl.domanski.carRent.admin.car.controller.dto.AdminCarEquipmentDto;
import pl.domanski.carRent.admin.car.controller.dto.AdminCarBasicInfo;
import pl.domanski.carRent.admin.car.controller.dto.AdminCarPhotoDto;
import pl.domanski.carRent.admin.car.controller.dto.AdminCarPriceDto;
import pl.domanski.carRent.admin.car.controller.dto.AdminCarTechnicalSpecificationDto;
import pl.domanski.carRent.admin.car.model.AdminCar;
import pl.domanski.carRent.admin.car.model.AdminCarDescription;
import pl.domanski.carRent.admin.car.model.AdminCarEquipment;
import pl.domanski.carRent.admin.car.model.AdminCarPhoto;
import pl.domanski.carRent.admin.car.model.AdminCarPrice;
import pl.domanski.carRent.admin.car.model.AdminCarTechnicalSpecification;

public class AdminCarMapper {

    public static AdminCarBasicInfo mapToAdminCarBasicInfo(AdminCar adminCar) {
        return AdminCarBasicInfo.builder()
                .id(adminCar.getId())
                .brand(adminCar.getBrand())
                .model(adminCar.getModel())
                .year(adminCar.getYear())
                .build();
    }

    public static AdminCarDescription mapToAdminCarDescription(AdminCarDescriptionDto descriptionDto, Long carId) {
        return AdminCarDescription.builder()
                .description(descriptionDto.getDescription())
                .carId(carId)
                .build();
    }

    public static AdminCar mapToAdminCar(AdminCarDto adminCarDto, String slug, Long id, long categoryId) {
        return AdminCar.builder()
                .id(id)
                .brand(adminCarDto.getBrand())
                .model(adminCarDto.getModel())
                .year(adminCarDto.getYear())
                .slug(slug)
                .categoryId(categoryId)
                .build();
    }

    public static AdminCarEquipment mapToAdminCarEquipment(AdminCarEquipmentDto adminCarEquipmentDto, Long carId) {
        return AdminCarEquipment.builder()
                .name(adminCarEquipmentDto.getName())
                .carId(carId)
                .build();
    }

    public static AdminCarTechnicalSpecification mapToAdminCarTechSpec(AdminCarTechnicalSpecificationDto carTechSpecDto, Long techSpecId) {
        return AdminCarTechnicalSpecification.builder()
                .id(techSpecId)
                .power(carTechSpecDto.getPower())
                .engine(carTechSpecDto.getEngine())
                .drive(carTechSpecDto.getDrive())
                .acceleration(carTechSpecDto.getAcceleration())
                .gearbox(carTechSpecDto.getGearbox())
                .fuel(carTechSpecDto.getFuel())
                .seats(carTechSpecDto.getSeats())
                .build();
    }

    public static AdminCarPrice mapToAdminCarPrice(AdminCarPriceDto carPriceDto, Long id) {
        return AdminCarPrice.builder()
                .id(id)
                .priceDay(carPriceDto.getPriceDay())
                .priceHalfWeek(carPriceDto.getPriceHalfWeek())
                .priceWeek(carPriceDto.getPriceWeek())
                .priceTwoWeeks(carPriceDto.getPriceTwoWeeks())
                .priceMonth(carPriceDto.getPriceMonth())
                .deposit(carPriceDto.getDeposit())
                .build();
    }

    public static AdminCarPhoto mapToAdminCarPhoto(AdminCarPhotoDto photoDto, Long carId) {
        return AdminCarPhoto.builder()
                .photo(photoDto.getPhoto())
                .carId(carId)
                .build();
    }



}
