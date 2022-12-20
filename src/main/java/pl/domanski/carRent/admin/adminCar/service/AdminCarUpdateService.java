package pl.domanski.carRent.admin.adminCar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.domanski.carRent.admin.adminCar.controller.dto.AdminCarBasicInfo;
import pl.domanski.carRent.admin.adminCar.controller.dto.AdminCarDescriptionDto;
import pl.domanski.carRent.admin.adminCar.controller.dto.AdminCarEquipmentDto;
import pl.domanski.carRent.admin.adminCar.controller.dto.AdminCarPhotoDto;
import pl.domanski.carRent.admin.adminCar.controller.dto.AdminCarPriceDto;
import pl.domanski.carRent.admin.adminCar.controller.dto.AdminCarTechnicalSpecificationDto;
import pl.domanski.carRent.admin.adminCar.model.AdminCar;
import pl.domanski.carRent.admin.adminCar.model.AdminCarDescription;
import pl.domanski.carRent.admin.adminCar.model.AdminCarEquipment;
import pl.domanski.carRent.admin.adminCar.model.AdminCarPhoto;
import pl.domanski.carRent.admin.adminCar.model.AdminCarPrice;
import pl.domanski.carRent.admin.adminCar.model.AdminCarTechnicalSpecification;
import pl.domanski.carRent.admin.adminCar.repository.AdminCarDescriptionRepository;
import pl.domanski.carRent.admin.adminCar.repository.AdminCarEquipmentRepository;
import pl.domanski.carRent.admin.adminCar.repository.AdminCarPhotoRepository;
import pl.domanski.carRent.admin.adminCar.repository.AdminCarPriceRepository;
import pl.domanski.carRent.admin.adminCar.repository.AdminCarRepository;
import pl.domanski.carRent.admin.adminCar.repository.AdminCarTechnicalSpecificationRepository;

import java.util.ArrayList;
import java.util.List;

import static pl.domanski.carRent.admin.adminCar.service.mapper.AdminCarMapper.mapToCarBasicInfo;
import static pl.domanski.carRent.admin.adminCar.service.mapper.AdminCarMapper.mapToCarPhoto;
import static pl.domanski.carRent.admin.adminCar.service.utils.carUpdateUtils.setNewCarBasicInfoValues;
import static pl.domanski.carRent.admin.adminCar.service.utils.carUpdateUtils.setNewCarDescriptionValues;
import static pl.domanski.carRent.admin.adminCar.service.utils.carUpdateUtils.setNewCarEquipmentValues;
import static pl.domanski.carRent.admin.adminCar.service.utils.carUpdateUtils.setNewCarPriceValues;
import static pl.domanski.carRent.admin.adminCar.service.utils.carUpdateUtils.setNewCarTechSpecValues;

@Service
@RequiredArgsConstructor
public class AdminCarUpdateService {

    private final AdminCarRepository adminCarRepository;
    private final AdminCarTechnicalSpecificationRepository adminCarTechnicalSpecificationRepository;
    private final AdminCarEquipmentRepository adminCarEquipmentRepository;
    private final AdminCarDescriptionRepository adminCarDescriptionRepository;
    private final AdminCarPriceRepository adminCarPriceRepository;
    private final AdminCarPhotoRepository adminCarPhotoRepository;

    @Transactional
    public AdminCarBasicInfo updateBasicInfo(Long id, AdminCarBasicInfo carBasicInfo) {
        AdminCar adminCar = adminCarRepository.findById(id).orElseThrow();
        setNewCarBasicInfoValues(carBasicInfo, adminCar);
        return mapToCarBasicInfo(adminCarRepository.save(adminCar));
    }

    @Transactional
    public AdminCarTechnicalSpecification updateCarTechSpec(Long carId, AdminCarTechnicalSpecificationDto carTechSpecDto) {
        Long carTechSpecId = getCarTechSpecId(carId);
        AdminCarTechnicalSpecification carTechSpec = adminCarTechnicalSpecificationRepository.findById(carTechSpecId).orElseThrow();
        setNewCarTechSpecValues(carTechSpecDto, carTechSpec);
        return adminCarTechnicalSpecificationRepository.save(carTechSpec);
    }


    @Transactional
    public List<AdminCarEquipment> updateCarEquipment(Long id, List<AdminCarEquipmentDto> carEquipmentDtoList) {
        List<AdminCarEquipment> equipments = adminCarEquipmentRepository.findAllByCarId(id);
        ArrayList<AdminCarEquipment> newCarEquipment = setNewCarEquipmentValues(carEquipmentDtoList, equipments);
        deleteEquipmentWhenNewListIsShorter(equipments, newCarEquipment);
        return adminCarEquipmentRepository.saveAll(newCarEquipment);
    }



    @Transactional
    public List<AdminCarDescription> updateCarDescription(Long id, List<AdminCarDescriptionDto> descriptionDtoList) {
        List<AdminCarDescription> descriptions = adminCarDescriptionRepository.findAllByCarId(id);
        ArrayList<AdminCarDescription> newCarDescription = setNewCarDescriptionValues(descriptionDtoList, descriptions);
        deleteDescriptionWhenNewListIsShorter(descriptions, newCarDescription);
        return adminCarDescriptionRepository.saveAll(newCarDescription);
    }

    @Transactional
    public AdminCarPrice updateCarPrice(Long carId, AdminCarPriceDto carPriceDto) {
        Long carPriceId = getCarPriceId(carId);
        AdminCarPrice carPrice = adminCarPriceRepository.findById(carPriceId).orElseThrow();
        setNewCarPriceValues(carPriceDto, carPrice);
        return adminCarPriceRepository.save(carPrice);
    }


    public AdminCarPhoto addCarPhoto(AdminCarPhotoDto adminCarPhotoDto, Long carId) {
        return adminCarPhotoRepository.save(mapToCarPhoto(adminCarPhotoDto, carId));
    }

    public void deleteCarPhoto(Long photoId) {
        adminCarPhotoRepository.deleteById(photoId);
    }

    private Long getCarTechSpecId(Long id) {
        return adminCarRepository.findById(id).orElseThrow().getAdminCarTechnicalSpecification().getId();
    }

    private Long getCarPriceId(Long id) {
        return adminCarRepository.findById(id).orElseThrow().getCarPrice().getId();
    }

    private void deleteEquipmentWhenNewListIsShorter(List<AdminCarEquipment> equipments, ArrayList<AdminCarEquipment> newCarEquipment) {
        if(equipments.size() > newCarEquipment.size()) {
            for (int i = 0; i < equipments.size(); i++) {
                try{
                    newCarEquipment.get(i);
                } catch (IndexOutOfBoundsException e) {
                    adminCarEquipmentRepository.deleteById(equipments.get(i).getId());
                }
            }
        }
    }

    private void deleteDescriptionWhenNewListIsShorter(List<AdminCarDescription> descriptions, ArrayList<AdminCarDescription> newCarDescription) {
        if(descriptions.size() > newCarDescription.size()) {
            for (int i = 0; i < descriptions.size(); i++) {
                try{
                    newCarDescription.get(i);
                } catch (IndexOutOfBoundsException e) {
                    adminCarDescriptionRepository.deleteById(descriptions.get(i).getId());
                }
            }
        }
    }
}
