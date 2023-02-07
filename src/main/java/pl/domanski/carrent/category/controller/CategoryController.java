package pl.domanski.carrent.category.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.domanski.carrent.category.model.Category;
import pl.domanski.carrent.category.service.CategoryService;
import pl.domanski.carrent.common.dto.CarBasicInfo;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public List<Category> getCategories() {
        return categoryService.getCategories();
    }

    @GetMapping("/{categoryName}")
    public List<CarBasicInfo> getCategoryCars(@PathVariable String categoryName) {
        return categoryService.getCategoriesCars(categoryName);
    }
}
