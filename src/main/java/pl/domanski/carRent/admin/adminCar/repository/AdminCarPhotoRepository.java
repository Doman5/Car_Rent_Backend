package pl.domanski.carRent.admin.adminCar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.domanski.carRent.admin.adminCar.model.AdminCarPhoto;

@Repository
public interface AdminCarPhotoRepository extends JpaRepository<AdminCarPhoto, Long> {
}
