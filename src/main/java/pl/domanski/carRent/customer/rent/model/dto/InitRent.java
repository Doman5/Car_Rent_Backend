package pl.domanski.carRent.customer.rent.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class InitRent {
    List<String> sortingTypes;
    List<PaymentDto> paymentTypes;
}
