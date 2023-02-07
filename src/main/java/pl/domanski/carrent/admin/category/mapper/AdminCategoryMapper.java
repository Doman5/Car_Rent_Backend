package pl.domanski.carrent.admin.category.mapper;

import pl.domanski.carrent.admin.category.model.AdminCategory;
import pl.domanski.carrent.admin.common.dto.AdminCategoryDto;

public class AdminCategoryMapper {

    public static AdminCategory mapToCategory(AdminCategoryDto adminCategoryDto, Long id) {
        return AdminCategory.builder()
                .id(id)
                .name(adminCategoryDto.getName())
                .build();
    }
    public static AdminCategoryDto mapToCategoryDto(AdminCategory category) {
        return AdminCategoryDto.builder()
                .name(category.getName())
                .build();
    }
}
