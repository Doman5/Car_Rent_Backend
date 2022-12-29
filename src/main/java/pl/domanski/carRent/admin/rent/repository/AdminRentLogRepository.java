package pl.domanski.carRent.admin.rent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.domanski.carRent.admin.rent.model.AdminRentLog;

@Repository
public interface AdminRentLogRepository extends JpaRepository<AdminRentLog, Long> {
}
