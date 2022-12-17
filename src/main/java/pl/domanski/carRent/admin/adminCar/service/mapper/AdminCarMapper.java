package pl.domanski.carRent.admin.adminCar.service.mapper;

import pl.domanski.carRent.admin.adminCar.controller.dto.AdminCarDto;
import pl.domanski.carRent.admin.adminCar.controller.dto.AdminCarListDto;
import pl.domanski.carRent.admin.adminCar.controller.dto.AdminCarTechnicalSpecificationDto;
import pl.domanski.carRent.admin.adminCar.model.AdminCar;
import pl.domanski.carRent.admin.adminCar.model.AdminCarTechnicalSpecification;

public class AdminCarMapper {

    public static AdminCarListDto mapToCarList(AdminCar adminCar) {
        return AdminCarListDto.builder()
                .id(adminCar.getId())
                .brand(adminCar.getBrand())
                .model(adminCar.getModel())
                .year(adminCar.getYear())
                .build();
    }

    public static AdminCar mapToCar(AdminCarDto adminCarDto, Long carId, Long carTechSpecId) {
        return AdminCar.builder()
                .id(carId)
                .brand(adminCarDto.getBrand())
                .model(adminCarDto.getModel())
                .year(adminCarDto.getYear())
                .adminCarTechnicalSpecification(
                        mapToCarTechSpec(adminCarDto.getAdminCarTechnicalSpecificationDto(), carTechSpecId))
                .build();
    }

    public static AdminCarTechnicalSpecification mapToCarTechSpec(AdminCarTechnicalSpecificationDto carTechSpecDto, Long carSpecId) {
        return AdminCarTechnicalSpecification.builder()
                .id(carSpecId)
                .power(carTechSpecDto.getPower())
                .engine(carTechSpecDto.getEngine())
                .drive(carTechSpecDto.getDrive())
                .acceleration(carTechSpecDto.getAcceleration())
                .gearbox(carTechSpecDto.getGearbox())
                .fuel(carTechSpecDto.getFuel())
                .seats(carTechSpecDto.getSeats())
                .build();
    }
}
