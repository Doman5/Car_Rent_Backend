package pl.domanski.carRent.admin.adminCar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.domanski.carRent.admin.adminCar.controller.dto.AdminCarListDto;
import pl.domanski.carRent.admin.adminCar.model.AdminCar;
import pl.domanski.carRent.admin.adminCar.repository.AdminCarRepository;
import pl.domanski.carRent.admin.adminCar.repository.AdminCarTechnicalSpecificationRepository;

@Service
@RequiredArgsConstructor
public class AdminCarService {

    private final AdminCarRepository adminCarRepository;

    public Page<AdminCarListDto> getCars(Pageable pageable) {
        return adminCarRepository.findAll(pageable)
                .map(AdminCarService::mapToCarList);
    }


    public AdminCar getCar(Long id) {
        return adminCarRepository.findById(id).orElseThrow();
    }


    public AdminCar createCar(AdminCar adminCar) {
        return adminCarRepository.save(adminCar);
    }

    public AdminCar updateCar(AdminCar adminCar) {
        return adminCarRepository.save(adminCar);
    }

    public void deleteCar(Long id) {
        adminCarRepository.deleteById(id);
    }

    private static AdminCarListDto mapToCarList(AdminCar adminCar) {
        return AdminCarListDto.builder()
                .id(adminCar.getId())
                .brand(adminCar.getBrand())
                .model(adminCar.getModel())
                .year(adminCar.getYear())
                .build();
    }
}


