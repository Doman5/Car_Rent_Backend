package pl.domanski.carrent.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.domanski.carrent.rent.model.Rent;

import java.util.List;

@Repository
public interface RentRepository extends JpaRepository<Rent,Long> {
    List<Rent> findAllByCarId(Long carId);
    List<Rent> findAllByUserIdOrderByIdDesc(Long userId);
}
