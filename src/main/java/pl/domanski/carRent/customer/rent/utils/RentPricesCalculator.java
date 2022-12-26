package pl.domanski.carRent.customer.rent.utils;

import lombok.RequiredArgsConstructor;
import pl.domanski.carRent.customer.common.model.Car;
import pl.domanski.carRent.webClient.localization.DistanceCalculatorService;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class RentPricesCalculator {

    public static BigDecimal calculateGrossValue(Car car, long days) {
        BigDecimal price;
        if(days < 0) throw new RuntimeException("Ilość dni nie może być na minusie");
        if(days <= 3) {
            price = car.getCarPrice().getPriceDay();
        } else if (days < 7) {
            price = car.getCarPrice().getPriceHalfWeek();
        } else if(days < 14) {
            price = car.getCarPrice().getPriceWeek();
        } else if (days < 30) {
            price = car.getCarPrice().getPriceTwoWeeks();
        } else {
            price = car.getCarPrice().getPriceMonth();
        }
        return price.multiply(BigDecimal.valueOf(days));
    }

    public static BigDecimal calculateTransportPrice(String destinationPlace, Car car, DistanceCalculatorService distanceCalculatorService) {
        String baseLocalization = "Sochaczew";
        if (destinationPlace.equals("Sochaczew")) {
            return BigDecimal.ZERO;
        }
        double distance = distanceCalculatorService.getInstance().calculateDistance(baseLocalization, destinationPlace);
        BigDecimal transportPricePerKm = car.getCarPrice().getTransportPricePerKm();

        return transportPricePerKm.multiply(BigDecimal.valueOf(distance));
    }
}
