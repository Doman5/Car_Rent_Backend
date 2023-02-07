package pl.domanski.carrent.common.model;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
public enum SortingType {
    ASC("Rosnaco"),
    DESC("Malejaco");

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
