package pl.domanski.carRent.customer.rent.mapper;

import pl.domanski.carRent.customer.rent.model.Payment;
import pl.domanski.carRent.customer.rent.model.dto.PaymentDto;

public class PaymentMapper {

    public static PaymentDto mapToPaymentDto(Payment payment) {
        return PaymentDto.builder()
                .id(payment.getId())
                .name(payment.getName())
                .note(payment.getNote())
                .defaultPayment(payment.isDefaultPayment())
                .description(payment.getDescription())
                .build();
    }
}
