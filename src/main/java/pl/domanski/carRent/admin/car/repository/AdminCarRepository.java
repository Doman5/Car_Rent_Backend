package pl.domanski.carRent.admin.car.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.domanski.carRent.admin.car.model.AdminCar;

import java.util.Optional;

@Repository
public interface AdminCarRepository extends JpaRepository<AdminCar, Long> {
    Optional<AdminCar> findBySlug(String slug);
}
