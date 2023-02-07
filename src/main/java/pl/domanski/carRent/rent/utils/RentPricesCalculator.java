package pl.domanski.carrent.rent.utils;

import lombok.RequiredArgsConstructor;
import pl.domanski.carrent.common.model.Car;
import pl.domanski.carrent.rent.model.dto.RentDto;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class RentPricesCalculator {

    public static BigDecimal calculateGrossValueByDaysCount(Car car, long days) {
        BigDecimal price;
        if(days < 0) throw new RuntimeException("Ilość dni nie może być na minusie");
        if(days < 3) {
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

    public static BigDecimal calculateTransportPrice(Car car, double distance) {
        BigDecimal transportPricePerKm = car.getCarPrice().getTransportPricePerKm();
        return transportPricePerKm.multiply(BigDecimal.valueOf(distance));
    }

    public static BigDecimal addTaxToFinalPrice(RentDto rentDto, double tax) {
        return rentDto.getPriceWithoutDeposit().add(rentDto.getRentalPrice()).add(rentDto.getReturnPrice()).multiply(BigDecimal.valueOf(tax)).setScale(2);
    }

    public static BigDecimal addDepositToFinalPrice(BigDecimal priceWithTax, BigDecimal deposit) {
        return priceWithTax.add(deposit);
    }

}
