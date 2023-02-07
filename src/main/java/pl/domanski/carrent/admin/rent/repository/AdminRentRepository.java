package pl.domanski.carrent.admin.rent.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.domanski.carrent.admin.rent.model.AdminRent;

@Repository
public interface AdminRentRepository extends JpaRepository<AdminRent, Long> {
     Page<AdminRent> findAllByOrderByIdDesc(Pageable pageable);
}
