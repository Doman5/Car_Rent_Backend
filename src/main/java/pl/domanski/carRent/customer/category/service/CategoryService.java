package pl.domanski.carRent.customer.category.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.domanski.carRent.customer.common.model.Car;
import pl.domanski.carRent.customer.common.mapper.CarMapper;
import pl.domanski.carRent.customer.category.model.Category;
import pl.domanski.carRent.customer.category.repository.CategoryRepository;
import pl.domanski.carRent.customer.common.dto.CarBasicInfo;
import pl.domanski.carRent.customer.common.repository.CarRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CarRepository carRepository;

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public List<CarBasicInfo> getCategoriesCars(String categoryName) {
        Long categoryId = categoryRepository.findByName(categoryName).getId();
        List<Car> cars = carRepository.findAllByCategoryId(categoryId);
        return cars.stream().map(CarMapper::mapToCarBasicInfo).toList();
    }
}
