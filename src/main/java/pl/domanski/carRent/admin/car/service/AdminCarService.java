package pl.domanski.carRent.admin.car.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.domanski.carRent.admin.car.controller.dto.AdminCarBasicInfo;
import pl.domanski.carRent.admin.car.controller.dto.AdminCarDto;
import pl.domanski.carRent.admin.car.model.AdminCar;
import pl.domanski.carRent.admin.car.repository.AdminCarDescriptionRepository;
import pl.domanski.carRent.admin.car.repository.AdminCarEquipmentRepository;
import pl.domanski.carRent.admin.car.repository.AdminCarPhotoRepository;
import pl.domanski.carRent.admin.car.repository.AdminCarPriceRepository;
import pl.domanski.carRent.admin.car.repository.AdminCarRepository;
import pl.domanski.carRent.admin.car.repository.AdminCarTechnicalSpecificationRepository;
import pl.domanski.carRent.admin.car.service.mapper.AdminCarMapper;
import pl.domanski.carRent.admin.common.repository.AdminCategoryRepository;

import static pl.domanski.carRent.admin.car.service.mapper.AdminCarMapper.mapToCar;
import static pl.domanski.carRent.admin.car.service.mapper.AdminCarMapper.mapToCarDescription;
import static pl.domanski.carRent.admin.car.service.mapper.AdminCarMapper.mapToCarEquipment;
import static pl.domanski.carRent.admin.car.service.mapper.AdminCarMapper.mapToCarPhoto;
import static pl.domanski.carRent.admin.car.service.mapper.AdminCarMapper.mapToCarPrice;
import static pl.domanski.carRent.admin.car.service.mapper.AdminCarMapper.mapToCarTechSpec;


@Service
@RequiredArgsConstructor
public class AdminCarService {

    private final static Long EMPTY_ID = null;

    private final AdminCarRepository adminCarRepository;
    private final AdminCarTechnicalSpecificationRepository adminCarTechnicalSpecificationRepository;
    private final AdminCarEquipmentRepository adminCarEquipmentRepository;
    private final AdminCarDescriptionRepository adminCarDescriptionRepository;
    private final AdminCarPriceRepository adminCarPriceRepository;
    private final AdminCarPhotoRepository adminCarPhotoRepository;
    private final AdminCategoryRepository adminCategoryRepository;

    public Page<AdminCarBasicInfo> getCars(Pageable pageable) {
        return adminCarRepository.findAll(pageable)
                .map(AdminCarMapper::mapToCarBasicInfo);
    }

    public AdminCar getCar(Long id) {
        return adminCarRepository.findById(id).orElseThrow();
    }

    @Transactional
    public AdminCar createCar(AdminCarDto adminCarDto) {

        if(carBasicInfoAreNull(adminCarDto)) {
            throw new RuntimeException("Nie podano podstawowych informacji");
        }

        AdminCar adminCar = adminCarRepository.save(mapToCar(adminCarDto, EMPTY_ID));

        if(adminCarDto.getCarTechnicalSpecification() != null) {
            adminCar.setAdminCarTechnicalSpecification(adminCarTechnicalSpecificationRepository
                    .save(mapToCarTechSpec(adminCarDto.getCarTechnicalSpecification(), EMPTY_ID)));
        }

        if(adminCarDto.getEquipments() != null) {
            adminCar.setEquipments(adminCarEquipmentRepository
                    .saveAll(adminCarDto.getEquipments().stream().map(eq -> mapToCarEquipment(eq, adminCar.getId())).toList()));
        }

        if(adminCarDto.getDescriptions() != null) {
            adminCar.setDescriptions(adminCarDescriptionRepository
                    .saveAll(adminCarDto.getDescriptions().stream().map(des -> mapToCarDescription(des, adminCar.getId())).toList()));
        }

        if(adminCarDto.getCarPrice() != null) {
            adminCar.setCarPrice(adminCarPriceRepository.save(mapToCarPrice(adminCarDto.getCarPrice(), EMPTY_ID)));
        }

        if(adminCarDto.getPhotos() != null) {
            adminCar.setPhotos(adminCarPhotoRepository
                    .saveAll(adminCarDto.getPhotos().stream().map(photo -> mapToCarPhoto(photo, adminCar.getId())).toList()));
        }

        if(adminCarDto.getCategory() != null) {
            Long categoryId = adminCategoryRepository.findByName(adminCarDto.getCategory().getName()).orElseThrow().getId();
            adminCar.setCategoryId(categoryId);
        }

        return adminCarRepository.save(adminCar);
    }

    private static boolean carBasicInfoAreNull(AdminCarDto adminCarDto) {
        return adminCarDto.getBrand() == null || adminCarDto.getModel() == null || adminCarDto.getYear() == 0;
    }

    @Transactional
    public void deleteCar(Long id) {
        adminCarRepository.deleteById(id);
    }

}

