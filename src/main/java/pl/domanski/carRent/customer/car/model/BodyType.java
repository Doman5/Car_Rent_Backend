package pl.domanski.carRent.customer.car.model;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
public enum BodyType {
    COUPE("Coupe"),
    CABRIO("Kabriolet"),
    KOMBI("Kombi"),
    COMPACT("Kompakt"),
    SEDAN("Sedan"),
    SUV("Suv"),
    LIFTBACK("Liftback");

    private final String name;

    BodyType(String name) {
        this.name = name;
    }

    public static Optional<BodyType> get(String name) {
        return Arrays.stream(BodyType.values())
                .filter(bt -> bt.name.equals(name))
                .findFirst();
    }
}
