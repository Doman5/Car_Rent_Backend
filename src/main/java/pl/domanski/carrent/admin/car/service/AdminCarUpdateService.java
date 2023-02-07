package pl.domanski.carrent.admin.car.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.domanski.carrent.admin.car.model.dto.AdminCarBasicInfo;
import pl.domanski.carrent.admin.car.model.dto.AdminCarDescriptionDto;
import pl.domanski.carrent.admin.car.model.dto.AdminCarDto;
import pl.domanski.carrent.admin.car.model.dto.AdminCarEquipmentDto;
import pl.domanski.carrent.admin.car.model.dto.AdminCarPriceDto;
import pl.domanski.carrent.admin.car.model.dto.AdminCarTechnicalSpecificationDto;
import pl.domanski.carrent.admin.car.model.AdminCar;
import pl.domanski.carrent.admin.car.model.AdminCarDescription;
import pl.domanski.carrent.admin.car.model.AdminCarEquipment;
import pl.domanski.carrent.admin.car.model.AdminCarPrice;
import pl.domanski.carrent.admin.car.model.AdminCarTechnicalSpecification;
import pl.domanski.carrent.admin.car.repository.AdminCarDescriptionRepository;
import pl.domanski.carrent.admin.car.repository.AdminCarEquipmentRepository;
import pl.domanski.carrent.admin.car.repository.AdminCarPriceRepository;
import pl.domanski.carrent.admin.car.repository.AdminCarRepository;
import pl.domanski.carrent.admin.car.repository.AdminCarTechnicalSpecificationRepository;
import pl.domanski.carrent.admin.car.mapper.AdminCarMapper;
import pl.domanski.carrent.admin.car.utils.CarSlugUtils;
import pl.domanski.carrent.admin.category.model.AdminCategory;
import pl.domanski.carrent.admin.common.dto.AdminCategoryDto;
import pl.domanski.carrent.admin.common.repository.AdminCategoryRepository;
import pl.domanski.carrent.common.model.Car;
import pl.domanski.carrent.common.repository.CarRepository;

import java.util.ArrayList;
import java.util.List;

import static pl.domanski.carrent.admin.car.mapper.AdminCarMapper.mapToAdminCarBasicInfo;
import static pl.domanski.carrent.admin.car.utils.CarUpdateUtils.setNewCarBasicInfoValues;
import static pl.domanski.carrent.admin.car.utils.CarUpdateUtils.setNewCarDescriptionValues;
import static pl.domanski.carrent.admin.car.utils.CarUpdateUtils.setNewCarEquipmentValues;
import static pl.domanski.carrent.admin.car.utils.CarUpdateUtils.setNewCarPriceValues;
import static pl.domanski.carrent.admin.car.utils.CarUpdateUtils.setNewCarTechSpecValues;

@Service
@RequiredArgsConstructor
public class AdminCarUpdateService {

    private final AdminCarRepository adminCarRepository;
    private final AdminCarTechnicalSpecificationRepository adminCarTechnicalSpecificationRepository;
    private final AdminCarEquipmentRepository adminCarEquipmentRepository;
    private final AdminCarDescriptionRepository adminCarDescriptionRepository;
    private final AdminCarPriceRepository adminCarPriceRepository;
    private final AdminCategoryRepository adminCategoryRepository;
    private final CarRepository carRepository;

    @Transactional
    public AdminCarBasicInfo updateBasicInfo(Long id, AdminCarBasicInfo carBasicInfo) {
        AdminCar adminCar = adminCarRepository.findById(id).orElseThrow();
        String slug = createSlug(carBasicInfo, adminCarRepository);

        setNewCarBasicInfoValues(carBasicInfo, adminCar, slug);
        return mapToAdminCarBasicInfo(adminCarRepository.save(adminCar));
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

    private Long getCarTechSpecId(Long id) {
        return adminCarRepository.findById(id).orElseThrow().getAdminCarTechnicalSpecification().getId();
    }

    private Long getCarPriceId(Long id) {
        return adminCarRepository.findById(id).orElseThrow().getCarPrice().getId();
    }

    private void deleteEquipmentWhenNewListIsShorter(List<AdminCarEquipment> equipments, ArrayList<AdminCarEquipment> newCarEquipment) {
        if (equipments.size() > newCarEquipment.size()) {
            for (int i = 0; i < equipments.size(); i++) {
                try {
                    newCarEquipment.get(i);
                } catch (IndexOutOfBoundsException e) {
                    adminCarEquipmentRepository.deleteById(equipments.get(i).getId());
                }
            }
        }
    }

    private void deleteDescriptionWhenNewListIsShorter(List<AdminCarDescription> descriptions, ArrayList<AdminCarDescription> newCarDescription) {
        if (descriptions.size() > newCarDescription.size()) {
            for (int i = 0; i < descriptions.size(); i++) {
                try {
                    newCarDescription.get(i);
                } catch (IndexOutOfBoundsException e) {
                    adminCarDescriptionRepository.deleteById(descriptions.get(i).getId());
                }
            }
        }
    }

    public AdminCar updateCarCategory(Long id, AdminCategoryDto adminCategoryDto) {
        AdminCar adminCar = adminCarRepository.findById(id).orElseThrow();
        Long categoryId = adminCategoryRepository.findByName(adminCategoryDto.getName()).orElseThrow().getId();
        adminCar.setCategoryId(categoryId);
        return adminCarRepository.save(adminCar);
    }

    private String createSlug(AdminCarBasicInfo adminCarBasicInfo, AdminCarRepository adminCarRepository) {
        CarSlugUtils carSlugUtils = new CarSlugUtils(adminCarRepository);
        AdminCarDto adminCarDto = new AdminCarDto(adminCarBasicInfo.getBrand(),
                adminCarBasicInfo.getModel(),
                adminCarBasicInfo.getYear());
        return carSlugUtils.createCarSlug(adminCarDto);
    }

    public AdminCarBasicInfo getBasicInfo(Long id) {
        return mapToAdminCarBasicInfo(adminCarRepository.findById(id).orElseThrow());
    }

    public AdminCarTechnicalSpecification getCarTechSpec(Long id) {
        Car car = carRepository.findById(id).orElseThrow();
        return adminCarTechnicalSpecificationRepository.findById(car.getCarTechnicalSpecification().getId()).orElse(new AdminCarTechnicalSpecification());
    }

    public AdminCarPrice getCarPrice(Long id) {
        Car car = carRepository.findById(id).orElseThrow();
        return adminCarPriceRepository.findById(car.getCarPrice().getId()).orElse(new AdminCarPrice());
    }

    public AdminCategory getCarCategory(Long id) {
        Car car = carRepository.findById(id).orElseThrow();
        return adminCategoryRepository.findById(car.getCategoryId()).orElse(new AdminCategory());
    }

    public List<AdminCarDescriptionDto> getCarDescriptions(Long id) {
        AdminCar adminCar = adminCarRepository.findById(id).orElseThrow();
        return adminCar.getDescriptions().stream()
                .map(AdminCarMapper::mapToCarDescriptionDto)
                .toList();
    }


    public List<AdminCarEquipmentDto> getCarEquipment(Long id) {
        AdminCar adminCar = adminCarRepository.findById(id).orElseThrow();
        return adminCar.getEquipments().stream()
                .map(AdminCarMapper::mapToCarEquipmentDto)
                .toList();
    }


}
