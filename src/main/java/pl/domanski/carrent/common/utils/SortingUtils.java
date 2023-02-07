package pl.domanski.carrent.common.utils;

import pl.domanski.carrent.common.model.SortingType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SortingUtils {

    public static <T> List<T> sortCars(List<T> cars, Optional<SortingType> sorting, Comparator comparator) {
        List<T> carsToSort = new ArrayList<T>(cars);
        if (sorting.isPresent()) {
            if (sorting.get() == SortingType.ASC) {
                carsToSort.sort(comparator);
            } else if (sorting.get() == SortingType.DESC) {
                carsToSort.sort(comparator);
                Collections.reverse(carsToSort);
            }
        }
        return carsToSort;
    }
}
