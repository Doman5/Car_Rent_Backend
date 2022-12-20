package pl.domanski.carRent.admin.adminCar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.domanski.carRent.admin.adminCar.controller.dto.AdminCarBasicInfo;
import pl.domanski.carRent.admin.adminCar.controller.dto.AdminCarDescriptionDto;
import pl.domanski.carRent.admin.adminCar.controller.dto.AdminCarEquipmentDto;
import pl.domanski.carRent.admin.adminCar.controller.dto.AdminCarPhotoDto;
import pl.domanski.carRent.admin.adminCar.controller.dto.AdminCarPriceDto;
import pl.domanski.carRent.admin.adminCar.controller.dto.AdminCarTechnicalSpecificationDto;
import pl.domanski.carRent.admin.adminCar.model.AdminCarDescription;
import pl.domanski.carRent.admin.adminCar.model.AdminCarEquipment;
import pl.domanski.carRent.admin.adminCar.model.AdminCarPhoto;
import pl.domanski.carRent.admin.adminCar.model.AdminCarPrice;
import pl.domanski.carRent.admin.adminCar.model.AdminCarTechnicalSpecification;
import pl.domanski.carRent.admin.adminCar.service.AdminCarUpdateService;

import java.util.List;

@RestController
@RequestMapping("/admin/cars/{id}")
@RequiredArgsConstructor
public class AdminCarUpdateController {

    private final AdminCarUpdateService adminCarUpdateService;

    @PutMapping("/carBasicInfo")
    public AdminCarBasicInfo updateBasicInfo(@PathVariable Long id, @RequestBody AdminCarBasicInfo carBasicInfo) {
        return adminCarUpdateService.updateBasicInfo(id, carBasicInfo);
    }

    @PutMapping("/carTechnicalSpecification")
    public AdminCarTechnicalSpecification updateCarTechSpec(@PathVariable Long id, @RequestBody AdminCarTechnicalSpecificationDto carTechSpec) {
        return adminCarUpdateService.updateCarTechSpec(id, carTechSpec);
    }

    @PutMapping("/carEquipment")
    public List<AdminCarEquipment> updateCarEquipment(@PathVariable Long id, @RequestBody List<AdminCarEquipmentDto> equipmentList) {
        return adminCarUpdateService.updateCarEquipment(id, equipmentList);
    }


    @PutMapping("/carDescription")
    public List<AdminCarDescription> updateCarDescription(@PathVariable Long id, @RequestBody List<AdminCarDescriptionDto> descriptionList) {
        return adminCarUpdateService.updateCarDescription(id, descriptionList);
    }

    @PutMapping("/carPrice")
    public AdminCarPrice updateCarPrice(@PathVariable Long id, @RequestBody AdminCarPriceDto carPriceDto) {
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
}
