package pl.domanski.carrent.admin.car.controller;

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
import pl.domanski.carrent.admin.car.model.dto.AdminCarBasicInfo;
import pl.domanski.carrent.admin.car.model.dto.AdminCarDto;
import pl.domanski.carrent.admin.car.model.AdminCar;
import pl.domanski.carrent.admin.car.service.AdminCarService;
import pl.domanski.carrent.admin.car.service.AdminCarUpdateInitDataService;
import pl.domanski.carrent.admin.common.dto.AdminCategoryDto;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("admin/cars")
@RequiredArgsConstructor
public class AdminCarController {

    private final AdminCarService adminCarService;
    private final AdminCarUpdateInitDataService adminCarInitDataService;

    @GetMapping
    public Page<AdminCarBasicInfo> getCars(Pageable pageable) {
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

    @GetMapping("/categories")
    public List<AdminCategoryDto> getAllCarCategories() {
        return adminCarInitDataService.getAllCategories();
    }

    @GetMapping("/body-types")
    public List<String> getAllBodyTypes() {
        return adminCarInitDataService.getAllBodyTypes();
    }
}
