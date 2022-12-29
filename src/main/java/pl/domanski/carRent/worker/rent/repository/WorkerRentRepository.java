package pl.domanski.carRent.worker.rent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.domanski.carRent.worker.rent.model.WorkerRent;
import pl.domanski.carRent.worker.rent.model.WorkerRentStatus;

import java.util.List;

@Repository
public interface WorkerRentRepository extends JpaRepository<WorkerRent, Long> {
    List<WorkerRent> findAllByRentStatusNotIn(List<WorkerRentStatus> rentStatus);
}
