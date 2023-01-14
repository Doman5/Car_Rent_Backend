package pl.domanski.carRent.customer.rent.model.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PaymentDto {
    private Long id;
    private String name;
    private boolean defaultPayment;
    private String note;
    private String description;
}
