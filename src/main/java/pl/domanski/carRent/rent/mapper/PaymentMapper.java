package pl.domanski.carrent.rent.mapper;

import pl.domanski.carrent.common.model.Payment;
import pl.domanski.carrent.rent.model.dto.PaymentDto;

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
