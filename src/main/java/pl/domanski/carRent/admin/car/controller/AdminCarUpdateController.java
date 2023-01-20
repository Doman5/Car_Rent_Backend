package pl.domanski.carRent.admin.car.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.domanski.carRent.admin.car.controller.dto.AdminCarBasicInfo;
import pl.domanski.carRent.admin.car.controller.dto.AdminCarDescriptionDto;
import pl.domanski.carRent.admin.car.controller.dto.AdminCarEquipmentDto;
import pl.domanski.carRent.admin.car.controller.dto.AdminCarPriceDto;
import pl.domanski.carRent.admin.car.controller.dto.AdminCarTechnicalSpecificationDto;
import pl.domanski.carRent.admin.car.model.AdminCar;
import pl.domanski.carRent.admin.car.model.AdminCarDescription;
import pl.domanski.carRent.admin.car.model.AdminCarEquipment;
import pl.domanski.carRent.admin.car.model.AdminCarPrice;
import pl.domanski.carRent.admin.car.model.AdminCarTechnicalSpecification;
import pl.domanski.carRent.admin.car.service.AdminCarUpdateService;
import pl.domanski.carRent.admin.category.model.AdminCategory;
import pl.domanski.carRent.admin.common.dto.AdminCategoryDto;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin/cars/{id}")
@RequiredArgsConstructor
public class AdminCarUpdateController {

    private final AdminCarUpdateService adminCarUpdateService;

    @GetMapping("/carBasicInfo")
    public AdminCarBasicInfo getBasicInfo(@PathVariable Long id) {
        return adminCarUpdateService.getBasicInfo(id);
    }

    @PutMapping("/carBasicInfo")
    public AdminCarBasicInfo updateBasicInfo(@PathVariable Long id, @RequestBody @Valid AdminCarBasicInfo carBasicInfo) {
        return adminCarUpdateService.updateBasicInfo(id, carBasicInfo);
    }

    @GetMapping("/carTechnicalSpecification")
    public AdminCarTechnicalSpecification getCarTechSpec(@PathVariable Long id) {
        return adminCarUpdateService.getCarTechSpec(id);
    }

    @PutMapping("/carTechnicalSpecification")
    public AdminCarTechnicalSpecification updateCarTechSpec(@PathVariable Long id, @RequestBody @Valid AdminCarTechnicalSpecificationDto carTechSpec) {
        return adminCarUpdateService.updateCarTechSpec(id, carTechSpec);
    }

    @PutMapping("/carEquipment")
    public List<AdminCarEquipment> updateCarEquipment(@PathVariable Long id, @RequestBody @Valid List<AdminCarEquipmentDto> equipmentList) {
        return adminCarUpdateService.updateCarEquipment(id, equipmentList);
    }


    @PutMapping("/carDescription")
    public List<AdminCarDescription> updateCarDescription(@PathVariable Long id, @RequestBody @Valid List<AdminCarDescriptionDto> descriptionList) {
        return adminCarUpdateService.updateCarDescription(id, descriptionList);
    }

    @GetMapping("/carPrice")
    public AdminCarPrice getCarPrice(@PathVariable Long id) {
        return adminCarUpdateService.getCarPrice(id);
    }

    @PutMapping("/carPrice")
    public AdminCarPrice updateCarPrice(@PathVariable Long id, @RequestBody @Valid AdminCarPriceDto carPriceDto) {
        return adminCarUpdateService.updateCarPrice(id, carPriceDto);
    }


    @GetMapping("/category")
    public AdminCategory getCategory(@PathVariable Long id) {
        return adminCarUpdateService.getCarCategory(id);
    }

    @PutMapping("/category")
    public AdminCar updateCarCategory(@PathVariable Long id, @RequestBody @Valid AdminCategoryDto adminCategoryDto) {
        return adminCarUpdateService.updateCarCategory(id, adminCategoryDto);
    }
}
