package pl.domanski.carRent.admin.adminCar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.domanski.carRent.admin.adminCar.controller.dto.AdminCarDto;
import pl.domanski.carRent.admin.adminCar.controller.dto.AdminCarListDto;
import pl.domanski.carRent.admin.adminCar.model.AdminCar;
import pl.domanski.carRent.admin.adminCar.service.AdminCarService;

@RestController
@RequestMapping("admin/cars")
@RequiredArgsConstructor
public class AdminCarController {

    private final static Long EMPTY_ID = null;

    private final AdminCarService adminCarService;

    @GetMapping
    public Page<AdminCarListDto> getCars(Pageable pageable) {
        return adminCarService.getCars(pageable);
    }

    @GetMapping("/{id}")
    public AdminCar getCar(@PathVariable Long id) {
        return  adminCarService.getCar(id);
    }

    @PostMapping
    public AdminCar createCar(@RequestBody AdminCarDto adminCarDto) {
        return adminCarService.createCar(mapCar(adminCarDto, EMPTY_ID));
    }

    @PutMapping("/{id}")
    public AdminCar updateCar(@PathVariable Long id, @RequestBody AdminCarDto adminCarDto) {
        return adminCarService.updateCar(mapCar(adminCarDto, id));
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        adminCarService.deleteCar(id);
    }


    private static AdminCar mapCar(AdminCarDto adminCarDto, Long id) {
        return AdminCar.builder()
                .id(id)
                .brand(adminCarDto.getBrand())
                .model(adminCarDto.getModel())
                .year(adminCarDto.getYear())
                .adminCarTechnicalSpecification(
                        adminCarDto.getAdminCarTechnicalSpecification())
                .build();
    }
}
