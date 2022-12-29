package pl.domanski.carRent.worker.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.domanski.carRent.worker.common.model.WorkerCar;

@Repository
public interface WorkerCarRepository extends JpaRepository<WorkerCar, Long> {
}
