package pl.domanski.carRent.customer.rent.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.domanski.carRent.customer.common.model.Car;
import pl.domanski.carRent.customer.rent.model.dto.RentDateAndPlace;
import pl.domanski.carRent.customer.rent.model.Rent;
import pl.domanski.carRent.customer.common.repository.RentRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CheckCarAvailabilityUtilsTest {

    @Mock
    RentRepository rentRepository;

    @InjectMocks
    CheckCarAvailabilityUtils checkCarAvailabilityUtils;


    @Test
    void should_return_car_availability_false() {
        //given
        given(rentRepository.findAllByCarId(2L)).willReturn(generateRentsForCarId());
        //when
        boolean result = checkCarAvailabilityUtils.checkCarAvailability(2L, createBusyRentDate());
        //then
        assertFalse(result);
    }

    @Test
    void should_return_car_availability_true() {
        //given
        given(rentRepository.findAllByCarId(2L)).willReturn(generateRentsForCarId());
        //when
        boolean result = checkCarAvailabilityUtils.checkCarAvailability(2L, createFreeRentDate());
        //then
        assertTrue(result);
    }

    private RentDateAndPlace createFreeRentDate() {
        return RentDateAndPlace.builder()
                .rentalDate(LocalDateTime.of(2022, 12, 6, 10, 0))
                .returnDate(LocalDateTime.of(2022, 12, 7, 10, 0))
                .build();
    }

    private List<Rent> generateRentsForCarId() {
        return List.of(Rent.builder()
                        .car(Car.builder()
                                .id(2L)
                                .build())
                        .rentalDate(LocalDateTime.of(2022,12,2,10,0))
                        .returnDate(LocalDateTime.of(2022, 12, 4, 10, 0))
                        .build(),
                Rent.builder()
                        .car(Car.builder()
                                .id(2L)
                                .build())
                        .rentalDate(LocalDateTime.of(2022,12,8,10,0))
                        .returnDate(LocalDateTime.of(2022, 12, 10, 10, 0))
                        .build(),
                Rent.builder()
                        .car(Car.builder()
                                .id(2L)
                                .build())
                        .rentalDate(LocalDateTime.of(2022,12,7,10,0))
                        .returnDate(LocalDateTime.of(2022, 12, 8, 10, 0))
                        .build()
                );

    }

    private RentDateAndPlace createBusyRentDate() {
        return RentDateAndPlace.builder()
                .rentalDate(LocalDateTime.of(2022, 12, 1, 10, 0))
                .returnDate(LocalDateTime.of(2022, 12, 3, 10, 0))
                .build();
    }
}