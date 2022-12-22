package pl.domanski.carRent.admin.category.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.domanski.carRent.admin.category.model.AdminCategory;
import pl.domanski.carRent.admin.common.dto.AdminCategoryDto;
import pl.domanski.carRent.admin.common.repository.AdminCategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminCategoryService {

    private static final Long EMPTY_ID = null;
    private final AdminCategoryRepository adminCategoryRepository;

    public List<AdminCategory> getCategories() {
        return adminCategoryRepository.findAll();
    }

    public AdminCategory getCategory(Long id) {
        return adminCategoryRepository.findById(id).orElseThrow();
    }

    public AdminCategory createCategory(AdminCategoryDto adminCategoryDto) {
        return adminCategoryRepository.save(mapToCategory(adminCategoryDto, EMPTY_ID));
    }

    public AdminCategory updateCategory(Long id, AdminCategoryDto adminCategoryDto) {
        AdminCategory category = adminCategoryRepository.findById(id).orElseThrow();
        category.setName(adminCategoryDto.getName());
        return adminCategoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        adminCategoryRepository.deleteById(id);
    }

    private AdminCategory mapToCategory(AdminCategoryDto adminCategoryDto, Long id) {
        return AdminCategory.builder()
                .id(id)
                .name(adminCategoryDto.getName())
                .build();
    }
}
