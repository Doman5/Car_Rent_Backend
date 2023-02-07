package pl.domanski.carrent.admin.car.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.domanski.carrent.admin.car.model.AdminCarEquipment;

import java.util.List;

@Repository
public interface AdminCarEquipmentRepository extends JpaRepository<pl.domanski.carrent.admin.car.model.AdminCarEquipment, Long> {
    List<AdminCarEquipment> findAllByCarId(Long id);
}
