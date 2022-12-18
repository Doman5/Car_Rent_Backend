package pl.domanski.carRent.admin.adminCar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.domanski.carRent.admin.adminCar.controller.dto.AdminCarDto;
import pl.domanski.carRent.admin.adminCar.controller.dto.AdminCarListDto;
import pl.domanski.carRent.admin.adminCar.model.AdminCar;
import pl.domanski.carRent.admin.adminCar.service.AdminCarService;

import javax.validation.Valid;

@RestController
@RequestMapping("admin/cars")
@RequiredArgsConstructor
public class AdminCarController {

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
    public AdminCar createCar(@RequestBody @Valid AdminCarDto adminCarDto) {
        return adminCarService.createCar(adminCarDto);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        adminCarService.deleteCar(id);
    }

}
