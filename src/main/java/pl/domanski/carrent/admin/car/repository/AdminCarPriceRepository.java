package pl.domanski.carrent.admin.car.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.domanski.carrent.admin.car.model.AdminCarPrice;

@Repository
public interface AdminCarPriceRepository extends JpaRepository<AdminCarPrice, Long> {
}
