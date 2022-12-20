package pl.domanski.carRent.admin.car.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.domanski.carRent.admin.car.model.AdminCarDescription;

import java.util.List;

@Repository
public interface AdminCarDescriptionRepository extends JpaRepository<AdminCarDescription, Long> {
    List<AdminCarDescription> findAllByCarId(Long id);
}
