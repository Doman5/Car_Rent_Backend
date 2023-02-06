package pl.domanski.carRent.admin.car.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.domanski.carRent.admin.car.model.AdminCarPrice;

@Repository
public interface AdminCarPriceRepository extends JpaRepository<AdminCarPrice, Long> {
}
