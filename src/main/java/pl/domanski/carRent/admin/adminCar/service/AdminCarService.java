package pl.domanski.carRent.admin.adminCar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.domanski.carRent.admin.adminCar.controller.dto.AdminCarDto;
import pl.domanski.carRent.admin.adminCar.controller.dto.AdminCarListDto;
import pl.domanski.carRent.admin.adminCar.model.AdminCar;
import pl.domanski.carRent.admin.adminCar.model.AdminCarDescription;
import pl.domanski.carRent.admin.adminCar.model.AdminCarEquipment;
import pl.domanski.carRent.admin.adminCar.model.AdminCarTechnicalSpecification;
import pl.domanski.carRent.admin.adminCar.repository.AdminCarDescriptionRepository;
import pl.domanski.carRent.admin.adminCar.repository.AdminCarEquipmentRepository;
import pl.domanski.carRent.admin.adminCar.repository.AdminCarRepository;
import pl.domanski.carRent.admin.adminCar.repository.AdminCarTechnicalSpecificationRepository;
import pl.domanski.carRent.admin.adminCar.service.mapper.AdminCarMapper;

import java.util.List;

import static pl.domanski.carRent.admin.adminCar.service.mapper.AdminCarMapper.mapToCar;
import static pl.domanski.carRent.admin.adminCar.service.mapper.AdminCarMapper.mapToCarDescription;
import static pl.domanski.carRent.admin.adminCar.service.mapper.AdminCarMapper.mapToCarEquipment;
import static pl.domanski.carRent.admin.adminCar.service.mapper.AdminCarMapper.mapToCarTechSpec;


@Service
@RequiredArgsConstructor
public class AdminCarService {

    private final static Long EMPTY_ID = null;

    private final AdminCarRepository adminCarRepository;
    private final AdminCarTechnicalSpecificationRepository adminCarTechnicalSpecificationRepository;
    private final AdminCarEquipmentRepository adminCarEquipmentRepository;
    private final AdminCarDescriptionRepository adminCarDescriptionRepository;

    public Page<AdminCarListDto> getCars(Pageable pageable) {
        return adminCarRepository.findAll(pageable)
                .map(AdminCarMapper::mapToCarList);
    }

    public AdminCar getCar(Long id) {
        return adminCarRepository.findById(id).orElseThrow();
    }

    @Transactional
    public AdminCar createCar(AdminCarDto adminCarDto) {
        AdminCar adminCar = adminCarRepository.save(mapToCar(adminCarDto, EMPTY_ID));

        AdminCarTechnicalSpecification adminCarTechnicalSpecification = adminCarTechnicalSpecificationRepository.save(mapToCarTechSpec(adminCarDto.getAdminCarTechnicalSpecificationDto(), EMPTY_ID));
        List<AdminCarEquipment> adminCarEquipments = adminCarEquipmentRepository.saveAll(adminCarDto.getEquipments().stream().map(eq -> mapToCarEquipment(eq, adminCar.getId())).toList());
        List<AdminCarDescription> carDescriptions = adminCarDescriptionRepository.saveAll(adminCarDto.getDescriptions().stream().map(des -> mapToCarDescription(des, adminCar.getId())).toList());

        adminCar.setAdminCarTechnicalSpecification(adminCarTechnicalSpecification);
        adminCar.setEquipments(adminCarEquipments);
        adminCar.setDescriptions(carDescriptions);
        return adminCarRepository.save(adminCar);
    }

    @Transactional
    public void deleteCar(Long id) {
        adminCarRepository.deleteById(id);
    }


}


