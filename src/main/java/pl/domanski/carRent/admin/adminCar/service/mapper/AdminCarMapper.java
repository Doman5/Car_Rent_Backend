package pl.domanski.carRent.admin.adminCar.service.mapper;

import pl.domanski.carRent.admin.adminCar.controller.dto.AdminCarDto;
import pl.domanski.carRent.admin.adminCar.controller.dto.AdminCarEquipmentDto;
import pl.domanski.carRent.admin.adminCar.controller.dto.AdminCarListDto;
import pl.domanski.carRent.admin.adminCar.controller.dto.AdminCarTechnicalSpecificationDto;
import pl.domanski.carRent.admin.adminCar.model.AdminCar;
import pl.domanski.carRent.admin.adminCar.model.AdminCarEquipment;
import pl.domanski.carRent.admin.adminCar.model.AdminCarTechnicalSpecification;

import java.util.List;

public class AdminCarMapper {

    public static AdminCarListDto mapToCarList(AdminCar adminCar) {
        return AdminCarListDto.builder()
                .id(adminCar.getId())
                .brand(adminCar.getBrand())
                .model(adminCar.getModel())
                .year(adminCar.getYear())
                .build();
    }

    public static AdminCar mapToNewCar(AdminCarDto adminCarDto, Long carId, Long carTechSpecId, Long equipmentId) {
        return AdminCar.builder()
                .id(carId)
                .brand(adminCarDto.getBrand())
                .model(adminCarDto.getModel())
                .year(adminCarDto.getYear())
                .adminCarTechnicalSpecification(
                        mapToCarTechSpec(adminCarDto.getAdminCarTechnicalSpecificationDto(), carTechSpecId))
                .equipments(adminCarDto.getCarEquipments().stream()
                        .map(ev -> mapToCarEquipment(ev, carId, equipmentId))
                        .toList())
                .build();
    }

    public static AdminCar mapToCar(AdminCarDto adminCarDto, Long carId, Long carTechSpecId, List<AdminCarEquipment> adminCarEquipments) {
        return AdminCar.builder()
                .id(carId)
                .brand(adminCarDto.getBrand())
                .model(adminCarDto.getModel())
                .year(adminCarDto.getYear())
                .adminCarTechnicalSpecification(
                        mapToCarTechSpec(adminCarDto.getAdminCarTechnicalSpecificationDto(), carTechSpecId))
                .equipments(adminCarEquipments)
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

    public static AdminCarEquipment mapToCarEquipment(AdminCarEquipmentDto adminCarEquipmentDto, Long carId, Long equipmentId) {
        return AdminCarEquipment.builder()
                .id(equipmentId)
                .name(adminCarEquipmentDto.getName())
                .carId(carId)
                .build();
    }
}
