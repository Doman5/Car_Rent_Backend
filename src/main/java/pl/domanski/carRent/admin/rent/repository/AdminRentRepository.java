package pl.domanski.carRent.admin.rent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.domanski.carRent.admin.rent.model.AdminRent;

@Repository
public interface AdminRentRepository extends JpaRepository<AdminRent, Long> {
}
