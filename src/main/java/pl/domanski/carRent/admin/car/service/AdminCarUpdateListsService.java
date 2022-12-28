package pl.domanski.carRent.admin.car.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.domanski.carRent.admin.car.model.AdminBodyType;
import pl.domanski.carRent.admin.category.model.AdminCategory;
import pl.domanski.carRent.admin.common.dto.AdminCategoryDto;
import pl.domanski.carRent.admin.common.repository.AdminCategoryRepository;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminCarUpdateListsService {

    private final AdminCategoryRepository adminCategoryRepository;

    public List<AdminCategoryDto> getAllCategories() {
        return  adminCategoryRepository.findAll().stream()
                .map(this::mapToAdminCategoryDto)
                .toList();
    }



    private AdminCategoryDto mapToAdminCategoryDto(AdminCategory adminCategory) {
        return AdminCategoryDto.builder()
                .name(adminCategory.getName())
                .build();
    }

    public List<AdminBodyType> getAllBodyTypes() {
        return Arrays.stream(AdminBodyType.values()).toList();
    }
}
