package pl.domanski.carrent.admin.car.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.domanski.carrent.admin.car.model.AdminCarPhoto;

import java.util.List;

@Repository
public interface AdminCarPhotoRepository extends JpaRepository<AdminCarPhoto, Long> {
    List<AdminCarPhoto> findAllByCarId(Long id);

    @Query(value = "select p from AdminCarPhoto p where p.photo like :slug")
    List<AdminCarPhoto> findAllPhotosWhatConstrainCarSlug(String slug);
}
