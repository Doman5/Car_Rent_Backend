package pl.domanski.carrent.admin.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.domanski.carrent.admin.category.model.AdminCategory;

import java.util.Optional;

@Repository
public interface AdminCategoryRepository extends JpaRepository<AdminCategory, Long> {
    Optional<AdminCategory> findByName(String name);
}
