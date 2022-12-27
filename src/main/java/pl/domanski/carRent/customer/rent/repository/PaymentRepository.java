package pl.domanski.carRent.customer.rent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.domanski.carRent.customer.rent.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
