package pl.domanski.carRent.admin.adminCar.service.utils;

import pl.domanski.carRent.admin.adminCar.controller.dto.AdminCarBasicInfo;
import pl.domanski.carRent.admin.adminCar.controller.dto.AdminCarDescriptionDto;
import pl.domanski.carRent.admin.adminCar.controller.dto.AdminCarEquipmentDto;
import pl.domanski.carRent.admin.adminCar.controller.dto.AdminCarPriceDto;
import pl.domanski.carRent.admin.adminCar.controller.dto.AdminCarTechnicalSpecificationDto;
import pl.domanski.carRent.admin.adminCar.model.AdminCar;
import pl.domanski.carRent.admin.adminCar.model.AdminCarDescription;
import pl.domanski.carRent.admin.adminCar.model.AdminCarEquipment;
import pl.domanski.carRent.admin.adminCar.model.AdminCarPrice;
import pl.domanski.carRent.admin.adminCar.model.AdminCarTechnicalSpecification;

import java.util.ArrayList;
import java.util.List;

public class carUpdateUtils {

    public static void setNewCarPriceValues(AdminCarPriceDto newValues, AdminCarPrice oldValues) {
        oldValues.setPriceDay(newValues.getPriceDay());
        oldValues.setPriceHalfWeek(newValues.getPriceHalfWeek());
        oldValues.setPriceWeek(newValues.getPriceWeek());
        oldValues.setPriceTwoWeeks(newValues.getPriceTwoWeeks());
        oldValues.setPriceMonth(newValues.getPriceMonth());
        oldValues.setDeposit(newValues.getDeposit());
    }

    public static ArrayList<AdminCarDescription> setNewCarDescriptionValues(List<AdminCarDescriptionDto> newValues, List<AdminCarDescription> oldValues) {
        ArrayList<AdminCarDescription> newDescriptionsList = new ArrayList<>();
        if (newValues.size() <= oldValues.size()) {
            for (int i = 0; i < oldValues.size(); i++) {
                try {
                    oldValues.get(i).setDescription(newValues.get(i).getDescription());
                    newDescriptionsList.add(oldValues.get(i));
                } catch (IndexOutOfBoundsException e) {
                    return newDescriptionsList;
                }
            }
        } else {
            Long carId = oldValues.get(0).getCarId();

            for (int i = 0; i < newValues.size(); i++) {
                try {
                    oldValues.get(i).setDescription(newValues.get(i).getDescription());
                    newDescriptionsList.add(oldValues.get(i));
                } catch (IndexOutOfBoundsException e) {
                    AdminCarDescription newDescription = AdminCarDescription.builder()
                            .description(newValues.get(i).getDescription())
                            .carId(carId)
                            .build();
                    newDescriptionsList.add(newDescription);
                }
            }
        }
        return newDescriptionsList;
    }

    public static ArrayList<AdminCarEquipment> setNewCarEquipmentValues(List<AdminCarEquipmentDto> newValues, List<AdminCarEquipment> oldValues) {
        ArrayList<AdminCarEquipment> newEquipmentList = new ArrayList<>();
        if (newValues.size() <= oldValues.size()) {
            for (int i = 0; i < oldValues.size(); i++) {
                try {
                    oldValues.get(i).setName(newValues.get(i).getName());
                    newEquipmentList.add(oldValues.get(i));
                } catch (IndexOutOfBoundsException e) {
                    return newEquipmentList;
                }
            }
        } else {
            Long carId = oldValues.get(0).getCarId();

            for (int i = 0; i < newValues.size(); i++) {
                try {
                    oldValues.get(i).setName(newValues.get(i).getName());
                    newEquipmentList.add(oldValues.get(i));
                } catch (IndexOutOfBoundsException e) {
                    AdminCarEquipment newEquipment = AdminCarEquipment.builder()
                            .name(newValues.get(i).getName())
                            .carId(carId)
                            .build();
                    newEquipmentList.add(newEquipment);
                }
            }
        }
        return newEquipmentList;
    }

    public static void setNewCarBasicInfoValues(AdminCarBasicInfo newValues, AdminCar oldValues) {
        oldValues.setBrand(newValues.getBrand());
        oldValues.setModel(newValues.getModel());
        oldValues.setYear(newValues.getYear());
    }

    public static void setNewCarTechSpecValues(AdminCarTechnicalSpecificationDto newValues, AdminCarTechnicalSpecification oldValues) {
        oldValues.setPower(newValues.getPower());
        oldValues.setEngine(newValues.getEngine());
        oldValues.setDrive(newValues.getDrive());
        oldValues.setAcceleration(newValues.getAcceleration());
        oldValues.setGearbox(newValues.getGearbox());
        oldValues.setFuel(newValues.getFuel());
        oldValues.setSeats(newValues.getSeats());
    }


}
