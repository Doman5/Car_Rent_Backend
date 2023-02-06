package pl.domanski.carRent.worker.rent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.domanski.carRent.worker.rent.model.WorkerRentLog;

@Repository
public interface WorkerRentLogRepository extends JpaRepository<WorkerRentLog, Long> {
}
