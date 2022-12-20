package pl.domanski.carRent.admin.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.domanski.carRent.admin.category.model.AdminCategory;

import java.util.Optional;

@Repository
public interface AdminCategoryRepository extends JpaRepository<AdminCategory, Long> {
    Optional<AdminCategory> findByName(String name);
}
