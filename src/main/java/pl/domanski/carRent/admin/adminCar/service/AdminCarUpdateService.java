package pl.domanski.carRent.admin.adminCar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.domanski.carRent.admin.adminCar.controller.dto.AdminCarBasicInfo;
import pl.domanski.carRent.admin.adminCar.controller.dto.AdminCarDescriptionDto;
import pl.domanski.carRent.admin.adminCar.controller.dto.AdminCarEquipmentDto;
import pl.domanski.carRent.admin.adminCar.controller.dto.AdminCarTechnicalSpecificationDto;
import pl.domanski.carRent.admin.adminCar.model.AdminCar;
import pl.domanski.carRent.admin.adminCar.model.AdminCarDescription;
import pl.domanski.carRent.admin.adminCar.model.AdminCarEquipment;
import pl.domanski.carRent.admin.adminCar.model.AdminCarTechnicalSpecification;
import pl.domanski.carRent.admin.adminCar.repository.AdminCarDescriptionRepository;
import pl.domanski.carRent.admin.adminCar.repository.AdminCarEquipmentRepository;
import pl.domanski.carRent.admin.adminCar.repository.AdminCarRepository;
import pl.domanski.carRent.admin.adminCar.repository.AdminCarTechnicalSpecificationRepository;

import java.util.List;

import static pl.domanski.carRent.admin.adminCar.service.mapper.AdminCarMapper.mapToCarBasicInfo;

@Service
@RequiredArgsConstructor
public class AdminCarUpdateService {

    private final AdminCarRepository adminCarRepository;
    private final AdminCarTechnicalSpecificationRepository adminCarTechnicalSpecificationRepository;
    private final AdminCarEquipmentRepository adminCarEquipmentRepository;
    private final AdminCarDescriptionRepository adminCarDescriptionRepository;

    @Transactional
    public AdminCarBasicInfo updateBasicInfo(Long id, AdminCarBasicInfo carBasicInfo) {
        AdminCar adminCar = adminCarRepository.findById(id).orElseThrow();
        setNewCarBasicInfoValues(carBasicInfo, adminCar);
        return mapToCarBasicInfo(adminCarRepository.save(adminCar));
    }

    @Transactional
    public AdminCarTechnicalSpecification updateCarTechSpec(Long id, AdminCarTechnicalSpecificationDto carTechSpecDto) {
        Long carTechSpecId = adminCarRepository.findById(id).orElseThrow().getAdminCarTechnicalSpecification().getId();
        AdminCarTechnicalSpecification carTechSpec = adminCarTechnicalSpecificationRepository.findById(carTechSpecId).orElseThrow();
        setNewCarTechSpecValues(carTechSpecDto, carTechSpec);
        return adminCarTechnicalSpecificationRepository.save(carTechSpec);
    }

    @Transactional
    public List<AdminCarEquipment> updateCarEquipment(Long id, List<AdminCarEquipmentDto> carEquipmentDtoList) {
        List<AdminCarEquipment> equipments = adminCarEquipmentRepository.findAllByCarId(id);
        setNewCarEquipmentValues(carEquipmentDtoList, equipments);
        return adminCarEquipmentRepository.saveAll(equipments);
    }

    @Transactional
    public List<AdminCarDescription> updateCarDescription(Long id, List<AdminCarDescriptionDto> descriptionDtoList) {
        List<AdminCarDescription> descriptions = adminCarDescriptionRepository.findAllByCarId(id);
        setNewCarDescriptionValues(descriptionDtoList, descriptions);
        return adminCarDescriptionRepository.saveAll(descriptions);
    }

    private static void setNewCarDescriptionValues(List<AdminCarDescriptionDto> descriptionDtoList, List<AdminCarDescription> descriptions) {
        for (int i = 0; i < descriptions.size(); i++) {
            if(descriptionDtoList.get(i) != null) {
                descriptions.get(i).setDescription(descriptionDtoList.get(i).getDescription());
            }
        }
    }

    private static void setNewCarEquipmentValues(List<AdminCarEquipmentDto> carEquipmentList, List<AdminCarEquipment> equipments) {
        for (int i = 0; i < equipments.size(); i++) {
            if(carEquipmentList.get(i) != null) {
                equipments.get(i).setName(carEquipmentList.get(i).getName());
            }
        }
    }


    private static void setNewCarBasicInfoValues(AdminCarBasicInfo carBasicInfo, AdminCar adminCar) {
        adminCar.setBrand(carBasicInfo.getBrand());
        adminCar.setModel(carBasicInfo.getModel());
        adminCar.setYear(carBasicInfo.getYear());
    }

    private static void setNewCarTechSpecValues(AdminCarTechnicalSpecificationDto carTechSpecDto, AdminCarTechnicalSpecification carTechSpec) {
        carTechSpec.setPower(carTechSpecDto.getPower());
        carTechSpec.setEngine(carTechSpecDto.getEngine());
        carTechSpec.setDrive(carTechSpecDto.getDrive());
        carTechSpec.setAcceleration(carTechSpecDto.getAcceleration());
        carTechSpec.setGearbox(carTechSpecDto.getGearbox());
        carTechSpec.setFuel(carTechSpecDto.getFuel());
        carTechSpec.setSeats(carTechSpecDto.getSeats());
    }

}
