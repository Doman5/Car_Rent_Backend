package pl.domanski.carRent.customer.car.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.domanski.carRent.customer.car.model.Car;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car,Long> {
    Optional<Car> findBySlug(String slug);
}
