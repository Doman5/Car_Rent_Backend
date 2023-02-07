package pl.domanski.carrent.admin.rent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.domanski.carrent.admin.rent.model.AdminRentLog;

import java.util.List;

@Repository
public interface AdminRentLogRepository extends JpaRepository<AdminRentLog, Long> {
    List<AdminRentLog> findAllByRentIdOrderByIdDesc(Long id);
}
