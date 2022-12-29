package pl.domanski.carRent.customer.common.utils;

import pl.domanski.carRent.customer.common.model.SortingType;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SortingUtils {

    public static <T> void sortCars(List<T> cars, Optional<SortingType> sorting, Comparator comparator) {
        if (sorting.isPresent()) {
            if (sorting.get() == SortingType.ASC) {
                cars.sort(comparator);
            } else if (sorting.get() == SortingType.DESC) {
                cars.sort(comparator);
                Collections.reverse(cars);
            }
        }
    }
}
