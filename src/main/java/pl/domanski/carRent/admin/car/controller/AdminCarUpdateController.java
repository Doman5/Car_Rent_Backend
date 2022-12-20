package pl.domanski.carRent.admin.car.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.domanski.carRent.admin.car.controller.dto.AdminCarBasicInfo;
import pl.domanski.carRent.admin.car.controller.dto.AdminCarDescriptionDto;
import pl.domanski.carRent.admin.car.controller.dto.AdminCarEquipmentDto;
import pl.domanski.carRent.admin.car.controller.dto.AdminCarPhotoDto;
import pl.domanski.carRent.admin.car.controller.dto.AdminCarPriceDto;
import pl.domanski.carRent.admin.car.controller.dto.AdminCarTechnicalSpecificationDto;
import pl.domanski.carRent.admin.car.model.AdminCar;
import pl.domanski.carRent.admin.car.model.AdminCarDescription;
import pl.domanski.carRent.admin.car.model.AdminCarEquipment;
import pl.domanski.carRent.admin.car.model.AdminCarPhoto;
import pl.domanski.carRent.admin.car.model.AdminCarPrice;
import pl.domanski.carRent.admin.car.model.AdminCarTechnicalSpecification;
import pl.domanski.carRent.admin.car.service.AdminCarUpdateService;
import pl.domanski.carRent.admin.common.dto.AdminCategoryDto;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin/cars/{id}")
@RequiredArgsConstructor
public class AdminCarUpdateController {

    private final AdminCarUpdateService adminCarUpdateService;

    @PutMapping("/carBasicInfo")
    public AdminCarBasicInfo updateBasicInfo(@PathVariable Long id, @RequestBody @Valid AdminCarBasicInfo carBasicInfo) {
        return adminCarUpdateService.updateBasicInfo(id, carBasicInfo);
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

    @PutMapping("/carPrice")
    public AdminCarPrice updateCarPrice(@PathVariable Long id, @RequestBody @Valid AdminCarPriceDto carPriceDto) {
        return adminCarUpdateService.updateCarPrice(id, carPriceDto);
    }

    @PostMapping("/carPhoto")
    public AdminCarPhoto addCarPhoto(@RequestBody AdminCarPhotoDto adminCarPhotoDto, @PathVariable Long id) {
        return adminCarUpdateService.addCarPhoto(adminCarPhotoDto, id);
    }

    @DeleteMapping("/carPhoto/{photoId}")
    public void addCarPhoto(@PathVariable Long photoId) {
        adminCarUpdateService.deleteCarPhoto(photoId);
    }

    @PutMapping("/category")
    public AdminCar updateCarCategory(@PathVariable Long id, @RequestBody @Valid AdminCategoryDto adminCategoryDto) {
        return adminCarUpdateService.updateCarCategory(id, adminCategoryDto);
    }
}
