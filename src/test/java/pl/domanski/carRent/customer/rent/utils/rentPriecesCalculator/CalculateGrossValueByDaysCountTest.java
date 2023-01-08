package pl.domanski.carRent.customer.rent.utils.rentPriecesCalculator;

import org.junit.Test;
import pl.domanski.carRent.customer.common.model.Car;
import pl.domanski.carRent.customer.common.model.CarPrice;
import pl.domanski.carRent.customer.rent.utils.RentPricesCalculator;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculateGrossValueByDaysCountTest {


    @Test
    public void should_calculate_Gross_value_for_6_days() {
        //given
        long days = 6L;
        Car car = createCar();
        //when
        BigDecimal result = RentPricesCalculator.calculateGrossValueByDaysCount(car, days);
        //then
        assertEquals(BigDecimal.valueOf(2400), result);
    }

    @Test
    public void should_calculate_Gross_value_for_7_days() {
        //given
        long days = 7L;
        Car car = createCar();
        //when
        BigDecimal result = RentPricesCalculator.calculateGrossValueByDaysCount(car, days);
        //then
        assertEquals(BigDecimal.valueOf(2100), result);
    }

    @Test
    public void should_calculate_Gross_value_for_13_days() {
        //given
        long days = 13L;
        Car car = createCar();
        //when
        BigDecimal result = RentPricesCalculator.calculateGrossValueByDaysCount(car, days);
        //then
        assertEquals(BigDecimal.valueOf(3900), result);
    }

    @Test
    public void should_calculate_Gross_value_for_14_days() {
        //given
        long days = 14L;
        Car car = createCar();
        //when
        BigDecimal result = RentPricesCalculator.calculateGrossValueByDaysCount(car, days);
        //then
        assertEquals(BigDecimal.valueOf(2800), result);
    }

    @Test
    public void should_calculate_Gross_value_for_29_days() {
        //given
        long days = 29L;
        Car car = createCar();
        //when
        BigDecimal result = RentPricesCalculator.calculateGrossValueByDaysCount(car, days);
        //then
        assertEquals(BigDecimal.valueOf(5800), result);
    }

    @Test
    public void should_calculate_Gross_value_for_30_days() {
        //given
        long days = 30L;
        Car car = createCar();
        //when
        BigDecimal result = RentPricesCalculator.calculateGrossValueByDaysCount(car, days);
        //then
        assertEquals(BigDecimal.valueOf(3000), result);
    }

    private Car createCar() {
        return Car.builder()
                .carPrice(CarPrice.builder()
                        .priceDay(BigDecimal.valueOf(500))
                        .priceHalfWeek(BigDecimal.valueOf(400))
                        .priceWeek(BigDecimal.valueOf(300))
                        .priceTwoWeeks(BigDecimal.valueOf(200))
                        .priceMonth(BigDecimal.valueOf(100))
                        .build())
                .build();
    }
}
