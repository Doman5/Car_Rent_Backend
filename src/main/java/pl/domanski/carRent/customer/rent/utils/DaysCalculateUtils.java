package pl.domanski.carRent.customer.rent.utils;

import pl.domanski.carRent.customer.rent.model.dto.RentDateAndPlace;

import static java.time.temporal.ChronoUnit.DAYS;

public class DaysCalculateUtils {

    public static long calculateCountOfDays(RentDateAndPlace rentDateAndPlace) {
        long days = DAYS.between(rentDateAndPlace.getRentalDate(), rentDateAndPlace.getReturnDate());
        if(days == 0 ) {
            return days + 1;
        }
        return days;
    }
}
