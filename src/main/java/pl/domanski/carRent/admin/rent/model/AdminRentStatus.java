package pl.domanski.carRent.admin.rent.model;

public enum AdminRentStatus {
    NEW("Nowe"),
    PAID("Opłacone"),
    WAITING("Oczekujące"),
    RENTAL_IN_PROGRESS("W trakcie wypożyczenia"),
    COMPLETED("Zrealizowane"),
    CANCELED("Anulowane");

    private final String value;

    AdminRentStatus(String value) {
        this.value = value;
    }

}
