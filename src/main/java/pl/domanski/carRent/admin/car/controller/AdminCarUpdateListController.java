package pl.domanski.carRent.admin.car.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.domanski.carRent.admin.car.service.AdminCarUpdateListService;
import pl.domanski.carRent.admin.common.dto.AdminCategoryDto;

import java.util.List;

@RestController
@RequestMapping("/admin/cars")
@RequiredArgsConstructor
public class AdminCarUpdateListController {

    private final AdminCarUpdateListService adminCarUpdateListsService;

    @GetMapping("/categories")
    public List<AdminCategoryDto> getAllCarCategories() {
        return adminCarUpdateListsService.getAllCategories();
    }

    @GetMapping("/body-types")
    public List<String> getAllBodyTypes() {
        return adminCarUpdateListsService.getAllBodyTypes();
    }
}
