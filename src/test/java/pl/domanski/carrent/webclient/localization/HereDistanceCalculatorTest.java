package pl.domanski.carrent.webclient.localization;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class HereDistanceCalculatorTest {


    @Test
    public void should_calculate_distance_between_two_locations() {
        //given
        String from = "Warszawa";
        String to = "Sochaczew";
        DistanceCalculator distanceCalculator = new HereDistanceCalculator();
        //when
        Double result = distanceCalculator.calculateDistance(from, to);
        //then
        assertThat(result).isBetween(70.0, 73.0);
    }

    @Test
    public void should_calculate_distance_between_two_locations_with_log_distance_between() {
        //given
        String from = "Bruksela";
        String to = "Paryż";
        DistanceCalculator distanceCalculator = new HereDistanceCalculator();
        //when
        Double result = distanceCalculator.calculateDistance(from, to);
        //then
        assertThat(result).isBetween(300.0, 330.0);
    }
}