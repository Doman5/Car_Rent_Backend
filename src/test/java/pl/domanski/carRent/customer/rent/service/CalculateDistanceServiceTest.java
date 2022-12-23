package pl.domanski.carRent.customer.rent.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.domanski.carRent.webClient.localization.HereDistanceClient;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class CalculateDistanceServiceTest {

    @Spy
    HereDistanceClient hereDistanceClient;

    @InjectMocks
    CalculateDistanceService calculateDistanceService;

    @Test
    public void should_calculate_distance_between_two_locations() {
        //given
        String from = "Warszawa";
        String to = "Sochaczew";
        //when
        Double result = calculateDistanceService.calculateDistanceBetweenTwoLocations(from, to);
        //then
        assertThat(result).isBetween(70.0, 73.0);
    }

    @Test
    public void should_calculate_distance_between_two_locations_with_log_distance_between() {
        //given
        String from = "Bruksela";
        String to = "Paryż";
        //when
        Double result = calculateDistanceService.calculateDistanceBetweenTwoLocations(from, to);
        //then
        assertThat(result).isBetween(310.0, 313.0);
    }

}