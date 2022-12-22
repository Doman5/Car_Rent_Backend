package pl.domanski.carRent.customer.common.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.domanski.carRent.customer.car.model.Car;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car,Long> {
    Optional<Car> findBySlug(String slug);
    List<Car> findAllByCategoryId(Long categoryId);
    List<Car> findAll(Specification<Car> spec);
}
