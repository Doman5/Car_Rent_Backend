package pl.domanski.carRent.admin.car.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.domanski.carRent.admin.car.model.AdminCarEquipment;

import java.util.List;

@Repository
public interface AdminCarEquipmentRepository extends JpaRepository<pl.domanski.carRent.admin.car.model.AdminCarEquipment, Long> {
    List<AdminCarEquipment> findAllByCarId(Long id);
}
