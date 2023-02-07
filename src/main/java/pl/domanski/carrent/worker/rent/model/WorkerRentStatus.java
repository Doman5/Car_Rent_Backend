package pl.domanski.carrent.worker.rent.model;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
public enum WorkerRentStatus {
    NEW("Nowe"),
    PART_PAID("Częściowo opłacone"),
    PAID("Opłacone"),
    WAITING("Oczekujące"),
    RENTAL_IN_PROGRESS("W trakcie wypożyczenia"),
    COMPLETED("Zrealizowane"),
    CANCELED("Anulowane");

    private final String value;

    WorkerRentStatus(String value) {
        this.value = value;
    }

    public static Optional<WorkerRentStatus> get(String value) {
        return Arrays.stream(WorkerRentStatus.values()).filter(rs -> rs.value.equals(value))
                .findFirst();
    }
}
