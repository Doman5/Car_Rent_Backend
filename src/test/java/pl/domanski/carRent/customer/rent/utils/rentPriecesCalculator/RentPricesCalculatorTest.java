package pl.domanski.carRent.customer.rent.utils.rentPriecesCalculator;

import org.junit.jupiter.api.Test;
import pl.domanski.carRent.customer.common.model.Car;
import pl.domanski.carRent.customer.common.model.CarPrice;
import pl.domanski.carRent.customer.rent.model.dto.RentDto;
import pl.domanski.carRent.customer.rent.utils.RentPricesCalculator;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RentPricesCalculatorTest {


    @Test
    void should_calculate_transport_price_for_distance_27_km() {
        //given
        Car car = createCar();
        double distance = 27;
        //when
        BigDecimal result = RentPricesCalculator.calculateTransportPrice(car, distance);
        //then
        assertEquals(BigDecimal.valueOf(81.0), result);
    }

    @Test
    void should_calculate_transport_price_for_distance_5678_km() {
        //given
        Car car = createCar();
        double distance = 5678;
        //when
        BigDecimal result = RentPricesCalculator.calculateTransportPrice(car, distance);
        //then
        assertEquals(BigDecimal.valueOf(17034.0), result);
    }


    @Test
    void should_add_tax23_to_final_price() {
        //given
        RentDto rentDto = createRentDtoWithRentPrices();
        double tax = 1.23;
        //when
        BigDecimal result = RentPricesCalculator.addTaxToFinalPrice(rentDto, tax);
        //then
        assertEquals(BigDecimal.valueOf(2868.36), result);
    }

    @Test
    void should_add_tax5_to_final_price() {
        //given
        RentDto rentDto = createRentDtoWithRentPrices();
        double tax = 1.05;
        //when
        BigDecimal result = RentPricesCalculator.addTaxToFinalPrice(rentDto, tax);
        //then
        assertEquals(BigDecimal.valueOf(2448.60).setScale(2), result);
    }

    private static RentDto createRentDtoWithRentPrices() {
        return RentDto.builder()
                .priceWithoutDeposit(BigDecimal.valueOf(2021))
                .rentalPrice(BigDecimal.valueOf(311))
                .returnPrice(BigDecimal.ZERO)
                .build();
    }

    private Car createCar() {
        return Car.builder()
                .carPrice(CarPrice.builder()
                        .transportPricePerKm(BigDecimal.valueOf(3))
                        .build())
                .build();
    }
}