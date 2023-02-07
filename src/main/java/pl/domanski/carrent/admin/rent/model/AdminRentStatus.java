package pl.domanski.carrent.admin.rent.model;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
public enum AdminRentStatus {
    NEW("Nowe"),
    PART_PAID("Częściowo opłacone"),
    PAID("Opłacone"),
    WAITING("Oczekujące"),
    RENTAL_IN_PROGRESS("W trakcie wypożyczenia"),
    COMPLETED("Zrealizowane"),
    CANCELED("Anulowane");

    private final String name;

    AdminRentStatus(String name) {
        this.name = name;
    }

    public static Optional<AdminRentStatus> get(String name) {
        return Arrays.stream(AdminRentStatus.values()).filter(rs -> rs.name.equals(name))
                .findFirst();
    }
}
