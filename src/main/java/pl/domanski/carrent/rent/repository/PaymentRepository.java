package pl.domanski.carrent.rent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.domanski.carrent.common.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
