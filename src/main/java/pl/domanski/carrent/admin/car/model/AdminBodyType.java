package pl.domanski.carrent.admin.car.model;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
public enum AdminBodyType {
    COUPE("Coupe"),
    CABRIO("Kabriolet"),
    KOMBI("Kombi"),
    COMPACT("Kompakt"),
    SEDAN("Sedan"),
    SUV("Suv"),
    LIFTBACK("Liftback");

    private final String name;

    AdminBodyType(String name) {
        this.name = name;
    }

    public static Optional<AdminBodyType> get(String name) {
        return Arrays.stream(AdminBodyType.values())
                .filter(bt -> bt.name.equals(name))
                .findFirst();
    }

}
