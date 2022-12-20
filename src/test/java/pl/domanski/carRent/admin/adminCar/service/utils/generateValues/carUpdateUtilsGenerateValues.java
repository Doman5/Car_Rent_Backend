package pl.domanski.carRent.admin.adminCar.service.utils.generateValues;

import pl.domanski.carRent.admin.adminCar.controller.dto.AdminCarDescriptionDto;
import pl.domanski.carRent.admin.adminCar.controller.dto.AdminCarEquipmentDto;
import pl.domanski.carRent.admin.adminCar.model.AdminCarDescription;
import pl.domanski.carRent.admin.adminCar.model.AdminCarEquipment;

import java.util.ArrayList;
import java.util.List;

public class carUpdateUtilsGenerateValues {

    public static List<AdminCarDescriptionDto> createNewDescriptionsListWithOneMoreValue() {
        ArrayList<AdminCarDescriptionDto> carDescriptions = new ArrayList<>();
        carDescriptions.add(AdminCarDescriptionDto.builder()
                .description("new description 1")
                .build());
        carDescriptions.add(AdminCarDescriptionDto.builder()
                .description("new description 2")
                .build());
        carDescriptions.add(AdminCarDescriptionDto.builder()
                .description("new description 3")
                .build());
        carDescriptions.add(AdminCarDescriptionDto.builder()
                .description("new description 4")
                .build());
        return carDescriptions;
    }


    public static List<AdminCarDescriptionDto> createNewDescriptionsList() {
        ArrayList<AdminCarDescriptionDto> carDescriptions = new ArrayList<>();
        carDescriptions.add(AdminCarDescriptionDto.builder()
                .description("new description 1")
                .build());
        carDescriptions.add(AdminCarDescriptionDto.builder()
                .description("new description 2")
                .build());
        carDescriptions.add(AdminCarDescriptionDto.builder()
                .description("new description 3")
                .build());
        return carDescriptions;
    }

    public static List<AdminCarDescription> createOldDescriptionsList() {
        return List.of(AdminCarDescription.builder()
                        .id(1L)
                        .carId(1L)
                        .description("old description 1")
                        .build(),
                AdminCarDescription.builder()
                        .id(2L)
                        .carId(1L)
                        .description("old description 2")
                        .build(),
                AdminCarDescription.builder()
                        .id(3L)
                        .carId(1L)
                        .description("old description 3")
                        .build()
        );
    }

    public static List<AdminCarDescriptionDto> createNewShorterDescriptionsList() {
        ArrayList<AdminCarDescriptionDto> carDescriptions = new ArrayList<>();
        carDescriptions.add(AdminCarDescriptionDto.builder()
                .description("new description 1")
                .build());
        carDescriptions.add(AdminCarDescriptionDto.builder()
                .description("new description 2")
                .build());
        return carDescriptions;
    }

    public static List<AdminCarEquipmentDto> createNewShorterEquipmentList() {
        ArrayList<AdminCarEquipmentDto> adminCarEquipments = new ArrayList<>();
        adminCarEquipments.add(AdminCarEquipmentDto.builder()
                .name("new equipment 1")
                .build());
        adminCarEquipments.add(AdminCarEquipmentDto.builder()
                .name("new equipment 2")
                .build());
        return adminCarEquipments;
    }

    public static List<AdminCarEquipmentDto> createNewEquipmentListWithOneMoreValue() {
        ArrayList<AdminCarEquipmentDto> adminCarEquipments = new ArrayList<>();
        adminCarEquipments.add(AdminCarEquipmentDto.builder()
                .name("new equipment 1")
                .build());
        adminCarEquipments.add(AdminCarEquipmentDto.builder()
                .name("new equipment 2")
                .build());
        adminCarEquipments.add(AdminCarEquipmentDto.builder()
                .name("new equipment 3")
                .build());
        adminCarEquipments.add(AdminCarEquipmentDto.builder()
                .name("new equipment 4")
                .build());
        return adminCarEquipments;
    }

    public static List<AdminCarEquipment> createOldEquipmentList() {
        return List.of(AdminCarEquipment.builder()
                        .id(1L)
                        .name("old equipment 1")
                        .carId(2L)
                        .build(),
                AdminCarEquipment.builder()
                        .id(2L)
                        .name("old equipment 2")
                        .carId(2L)
                        .build(),
                AdminCarEquipment.builder()
                        .id(3L)
                        .name("old equipment 2")
                        .carId(2L)
                        .build());
    }

    public static List<AdminCarEquipmentDto> createNewEquipmentList() {
        ArrayList<AdminCarEquipmentDto> adminCarEquipments = new ArrayList<>();
        adminCarEquipments.add(AdminCarEquipmentDto.builder()
                .name("new equipment 1")
                .build());
        adminCarEquipments.add(AdminCarEquipmentDto.builder()
                .name("new equipment 2")
                .build());
        adminCarEquipments.add(AdminCarEquipmentDto.builder()
                .name("new equipment 3")
                .build());
        return adminCarEquipments;
    }
}
