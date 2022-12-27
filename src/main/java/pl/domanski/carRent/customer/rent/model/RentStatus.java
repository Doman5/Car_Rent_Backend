package pl.domanski.carRent.customer.rent.model;

public enum RentStatus {
    NEW("Nowe"),
    PAID("Opłacone"),
    WAITING("Oczekujące"),
    RENTAL_IN_PROGRESS("W trakcie wypożyczenia"),
    COMPLETED("Zrealizowane"),
    CANCELED("Anulowane");

    private final String value;

    RentStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
