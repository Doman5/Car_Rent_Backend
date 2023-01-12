package pl.domanski.carRent.customer.rent.utils;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.domanski.carRent.customer.rent.model.dto.RentDateAndPlace;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.domanski.carRent.customer.rent.utils.DaysCalculateUtils.calculateCountOfDays;

class DaysCalculateUtilsTest {

    @ParameterizedTest
    @MethodSource("provideDatesForDaysCalculator")
    void should_calculate_days_count(RentDateAndPlace rentDateAndPlace, long expected) {
        //given
        //when
        long result = calculateCountOfDays(rentDateAndPlace);
        //then
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> provideDatesForDaysCalculator() {
        return Stream.of(
                Arguments.of(RentDateAndPlace.builder()
                                .rentalDate(LocalDateTime.of(2022, 1, 1, 15, 0))
                                .returnDate(LocalDateTime.of(2022, 1, 1, 16, 0))
                                .build()
                        , 1),
                Arguments.of(RentDateAndPlace.builder()
                                .rentalDate(LocalDateTime.of(2022, 1, 1, 15, 0))
                                .returnDate(LocalDateTime.of(2022, 1, 3, 15, 0))
                                .build()
                        , 2),
                Arguments.of(RentDateAndPlace.builder()
                                .rentalDate(LocalDateTime.of(2022, 1, 1, 15, 0))
                                .returnDate(LocalDateTime.of(2022, 1, 9, 15, 0))
                                .build()
                        , 8),
                Arguments.of(RentDateAndPlace.builder()
                                .rentalDate(LocalDateTime.of(2022, 1, 1, 15, 0))
                                .returnDate(LocalDateTime.of(2022, 1, 30, 15, 0))
                                .build()
                        , 29),
                Arguments.of(RentDateAndPlace.builder()
                                .rentalDate(LocalDateTime.of(2022, 1, 1, 15, 0))
                                .returnDate(LocalDateTime.of(2022, 2, 1, 15, 0))
                                .build()
                        , 31)
        );
    }
}