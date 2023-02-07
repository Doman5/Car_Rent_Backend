package pl.domanski.carrent.common.model;

import lombok.Getter;

@Getter
public enum RentStatus {
    NEW("Nowe"),
    PART_PAID("Częściowo opłacone"),
    PAID("Opłacone"),
    WAITING("Oczekujące"),
    RENTAL_IN_PROGRESS("W trakcie wypożyczenia"),
    COMPLETED("Zrealizowane"),
    CANCELED("Anulowane");

    private final String value;

    RentStatus(String value) {
        this.value = value;
    }

}
