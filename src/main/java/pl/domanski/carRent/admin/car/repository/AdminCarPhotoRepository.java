package pl.domanski.carRent.admin.car.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.domanski.carRent.admin.car.model.AdminCarPhoto;

import java.util.List;

@Repository
public interface AdminCarPhotoRepository extends JpaRepository<AdminCarPhoto, Long> {
    List<AdminCarPhoto> findAllByCarId(Long id);
}
