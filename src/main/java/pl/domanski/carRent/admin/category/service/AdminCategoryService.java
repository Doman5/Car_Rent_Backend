package pl.domanski.carRent.admin.category.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.domanski.carRent.admin.category.model.AdminCategory;
import pl.domanski.carRent.admin.common.dto.AdminCategoryDto;
import pl.domanski.carRent.admin.common.repository.AdminCategoryRepository;

import java.util.List;

import static pl.domanski.carRent.admin.category.mapper.AdminCategoryMapper.mapToCategory;
import static pl.domanski.carRent.admin.category.mapper.AdminCategoryMapper.mapToCategoryDto;

@Service
@RequiredArgsConstructor
public class AdminCategoryService {

    private static final Long EMPTY_ID = null;
    private final AdminCategoryRepository adminCategoryRepository;

    public List<AdminCategory> getCategories() {
        return adminCategoryRepository.findAll();
    }

    public AdminCategoryDto getCategory(Long id) {
        return mapToCategoryDto(adminCategoryRepository.findById(id).orElseThrow()) ;
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



}
