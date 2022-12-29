package pl.domanski.carRent.customer.common.model;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
public enum SortingType {
    ASC("Ascending"),
    DESC("Descending");

    private final String name;

    SortingType(String name) {
        this.name = name;
    }

    public static Optional<SortingType> get(String name) {
        return Arrays.stream(SortingType.values())
                .filter(st -> st.name.equals(name))
                .findFirst();
    }


}
