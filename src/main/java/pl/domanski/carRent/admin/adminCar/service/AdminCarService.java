package pl.domanski.carRent.admin.adminCar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.domanski.carRent.admin.adminCar.controller.dto.AdminCarDto;
import pl.domanski.carRent.admin.adminCar.controller.dto.AdminCarListDto;
import pl.domanski.carRent.admin.adminCar.model.AdminCar;
import pl.domanski.carRent.admin.adminCar.model.AdminCarTechnicalSpecification;
import pl.domanski.carRent.admin.adminCar.repository.AdminCarRepository;
import pl.domanski.carRent.admin.adminCar.repository.AdminCarTechnicalSpecificationRepository;
import pl.domanski.carRent.admin.adminCar.service.mapper.AdminCarMapper;

import static pl.domanski.carRent.admin.adminCar.service.mapper.AdminCarMapper.mapToCar;
import static pl.domanski.carRent.admin.adminCar.service.mapper.AdminCarMapper.mapToCarTechSpec;

@Service
@RequiredArgsConstructor
public class AdminCarService {

    private final static Long EMPTY_ID = null;

    private final AdminCarRepository adminCarRepository;
    private final AdminCarTechnicalSpecificationRepository adminCarTechnicalSpecificationRepository;

    public Page<AdminCarListDto> getCars(Pageable pageable) {
        return adminCarRepository.findAll(pageable)
                .map(AdminCarMapper::mapToCarList);
    }

    public AdminCar getCar(Long id) {
        return adminCarRepository.findById(id).orElseThrow();
    }


    public AdminCar createCar(AdminCarDto adminCarDto) {
        return adminCarRepository.save(mapToCar(adminCarDto, EMPTY_ID, EMPTY_ID));
    }

    @Transactional
    public AdminCar updateCar(AdminCarDto adminCarDto, Long id) {
        Long adminCarTechSpecId = adminCarRepository.findById(id).orElseThrow().getAdminCarTechnicalSpecification().getId();
        AdminCarTechnicalSpecification adminCarTechSpec = adminCarTechnicalSpecificationRepository
                .save(mapToCarTechSpec(adminCarDto.getAdminCarTechnicalSpecificationDto(), adminCarTechSpecId));
        AdminCar adminCar = mapToCar(adminCarDto, id , adminCarTechSpec.getId());
        return adminCarRepository.save(adminCar);
    }

    @Transactional
    public void deleteCar(Long id) {
        adminCarRepository.deleteById(id);
    }
}


