package pl.domanski.carrent.rent.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.domanski.carrent.common.repository.RentRepository;
import pl.domanski.carrent.rent.model.dto.RentDateAndPlace;
import pl.domanski.carrent.rent.model.Rent;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CheckCarAvailabilityUtils {

    private final RentRepository rentRepository;

    public boolean checkCarAvailability(Long carId, RentDateAndPlace rentDateAndPlace) {
        LocalDateTime rentalDate = rentDateAndPlace.getRentalDate();
        LocalDateTime returnDate = rentDateAndPlace.getReturnDate();
        List<Rent> rents = rentRepository.findAllByCarId(carId);

        if (rents.isEmpty()) {
            return true;
        }

        for (Rent rent : rents) {
            if (compareRentDates(rentalDate, returnDate, rent)) {
                return false;
            }
        }
        return true;
    }

    private static boolean compareRentDates(LocalDateTime rentalDate, LocalDateTime returnDate, Rent rent) {
        return (rentalDate.isAfter(rent.getRentalDate()) && rentalDate.isBefore(rent.getReturnDate()))
                || (returnDate.isAfter(rent.getRentalDate()) && returnDate.isBefore(rent.getReturnDate()));
    }

}
