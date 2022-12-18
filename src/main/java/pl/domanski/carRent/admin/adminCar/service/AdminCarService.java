package pl.domanski.carRent.admin.adminCar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.domanski.carRent.admin.adminCar.controller.dto.AdminCarBasicInfo;
import pl.domanski.carRent.admin.adminCar.controller.dto.AdminCarDto;
import pl.domanski.carRent.admin.adminCar.model.AdminCar;
import pl.domanski.carRent.admin.adminCar.model.AdminCarDescription;
import pl.domanski.carRent.admin.adminCar.model.AdminCarEquipment;
import pl.domanski.carRent.admin.adminCar.model.AdminCarPrice;
import pl.domanski.carRent.admin.adminCar.model.AdminCarTechnicalSpecification;
import pl.domanski.carRent.admin.adminCar.repository.AdminCarDescriptionRepository;
import pl.domanski.carRent.admin.adminCar.repository.AdminCarEquipmentRepository;
import pl.domanski.carRent.admin.adminCar.repository.AdminCarPriceRepository;
import pl.domanski.carRent.admin.adminCar.repository.AdminCarRepository;
import pl.domanski.carRent.admin.adminCar.repository.AdminCarTechnicalSpecificationRepository;
import pl.domanski.carRent.admin.adminCar.service.mapper.AdminCarMapper;

import java.util.List;

import static pl.domanski.carRent.admin.adminCar.service.mapper.AdminCarMapper.mapToCar;
import static pl.domanski.carRent.admin.adminCar.service.mapper.AdminCarMapper.mapToCarDescription;
import static pl.domanski.carRent.admin.adminCar.service.mapper.AdminCarMapper.mapToCarEquipment;
import static pl.domanski.carRent.admin.adminCar.service.mapper.AdminCarMapper.mapToCarPrice;
import static pl.domanski.carRent.admin.adminCar.service.mapper.AdminCarMapper.mapToCarTechSpec;


@Service
@RequiredArgsConstructor
public class AdminCarService {

    private final static Long EMPTY_ID = null;

    private final AdminCarRepository adminCarRepository;
    private final AdminCarTechnicalSpecificationRepository adminCarTechnicalSpecificationRepository;
    private final AdminCarEquipmentRepository adminCarEquipmentRepository;
    private final AdminCarDescriptionRepository adminCarDescriptionRepository;
    private final AdminCarPriceRepository adminCarPriceRepository;

    public Page<AdminCarBasicInfo> getCars(Pageable pageable) {
        return adminCarRepository.findAll(pageable)
                .map(AdminCarMapper::mapToCarBasicInfo);
    }

    public AdminCar getCar(Long id) {
        return adminCarRepository.findById(id).orElseThrow();
    }

    @Transactional
    public AdminCar createCar(AdminCarDto adminCarDto) {
        AdminCar adminCar = adminCarRepository.save(mapToCar(adminCarDto, EMPTY_ID));

        AdminCarTechnicalSpecification adminCarTechnicalSpecification = adminCarTechnicalSpecificationRepository.save(mapToCarTechSpec(adminCarDto.getCarTechnicalSpecificationDto(), EMPTY_ID));
        List<AdminCarEquipment> adminCarEquipments = adminCarEquipmentRepository.saveAll(adminCarDto.getEquipments().stream().map(eq -> mapToCarEquipment(eq, adminCar.getId())).toList());
        List<AdminCarDescription> carDescriptions = adminCarDescriptionRepository.saveAll(adminCarDto.getDescriptions().stream().map(des -> mapToCarDescription(des, adminCar.getId())).toList());
        AdminCarPrice price = adminCarPriceRepository.save(mapToCarPrice(adminCarDto.getCarPrice(), EMPTY_ID));

        adminCar.setAdminCarTechnicalSpecification(adminCarTechnicalSpecification);
        adminCar.setEquipments(adminCarEquipments);
        adminCar.setDescriptions(carDescriptions);
        adminCar.setCarPrice(price);

        return adminCarRepository.save(adminCar);
    }

    @Transactional
    public void deleteCar(Long id) {
        adminCarRepository.deleteById(id);
    }


}


