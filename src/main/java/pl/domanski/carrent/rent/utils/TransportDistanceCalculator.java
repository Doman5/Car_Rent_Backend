package pl.domanski.carrent.rent.utils;

import pl.domanski.carrent.webclient.localization.DistanceCalculatorService;

public class TransportDistanceCalculator {

    public static double calculateTransportDistance(String destinationPlace, DistanceCalculatorService distanceCalculatorService) {
        String baseLocalization = "Sochaczew";
        if (destinationPlace.equals("Sochaczew")) {
            return 0;
        }
        return distanceCalculatorService.getInstance().calculateDistance(baseLocalization, destinationPlace);
    }
}
