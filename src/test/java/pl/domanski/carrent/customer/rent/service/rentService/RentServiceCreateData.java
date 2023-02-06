package pl.domanski.carRent.customer.rent.service.rentService;

import pl.domanski.carRent.customer.common.model.Car;
import pl.domanski.carRent.customer.common.model.CarPrice;
import pl.domanski.carRent.customer.rent.model.dto.RentDateAndPlace;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class RentServiceCreateData {

    public static List<Car> createCarsList() {
        return List.of(Car.builder()
                        .id(1L)
                        .brand("audi")
                        .model("a3")
                        .carPrice(CarPrice.builder()
                                .priceDay(BigDecimal.valueOf(5))
                                .priceHalfWeek(BigDecimal.valueOf(4))
                                .priceWeek(BigDecimal.valueOf(3))
                                .priceTwoWeeks(BigDecimal.valueOf(2))
                                .priceMonth(BigDecimal.valueOf(1))
                                .transportPricePerKm(BigDecimal.valueOf(0.1))
                                .build())
                        .build(),
                Car.builder()
                        .id(2L)
                        .brand("bmw")
                        .model("seria 3")
                        .carPrice(CarPrice.builder()
                                .priceDay(BigDecimal.valueOf(50))
                                .priceHalfWeek(BigDecimal.valueOf(40))
                                .priceWeek(BigDecimal.valueOf(30))
                                .priceTwoWeeks(BigDecimal.valueOf(20))
                                .priceMonth(BigDecimal.valueOf(10))
                                .transportPricePerKm(BigDecimal.valueOf(1))
                                .build())
                        .build(),
                Car.builder()
                        .id(3L)
                        .brand("Mercedes")
                        .model("C Klasa")
                        .carPrice(CarPrice.builder()
                                .priceDay(BigDecimal.valueOf(500))
                                .priceHalfWeek(BigDecimal.valueOf(400))
                                .priceWeek(BigDecimal.valueOf(300))
                                .priceTwoWeeks(BigDecimal.valueOf(200))
                                .priceMonth(BigDecimal.valueOf(100))
                                .transportPricePerKm(BigDecimal.valueOf(10))
                                .build())
                        .build(),
                Car.builder()
                        .id(4L)
                        .brand("Porsche")
                        .model("911")
                        .carPrice(CarPrice.builder()
                                .priceDay(BigDecimal.valueOf(55))
                                .priceHalfWeek(BigDecimal.valueOf(44))
                                .priceWeek(BigDecimal.valueOf(33))
                                .priceTwoWeeks(BigDecimal.valueOf(22))
                                .priceMonth(BigDecimal.valueOf(11))
                                .transportPricePerKm(BigDecimal.valueOf(1))
                                .build())
                        .build(),
                Car.builder()
                        .id(5L)
                        .brand("Lamborghini")
                        .model("Aventador")
                        .carPrice(CarPrice.builder()
                                .priceDay(BigDecimal.valueOf(5000))
                                .priceHalfWeek(BigDecimal.valueOf(4000))
                                .priceWeek(BigDecimal.valueOf(3000))
                                .priceTwoWeeks(BigDecimal.valueOf(2000))
                                .priceMonth(BigDecimal.valueOf(1000))
                                .transportPricePerKm(BigDecimal.valueOf(10))
                                .build())
                        .build()
        );
    }

    public static RentDateAndPlace createRentDateAndPlace() {
        return RentDateAndPlace.builder()
                .rentalDate(LocalDateTime.now().plusDays(2))
                .returnDate(LocalDateTime.now().plusDays(5))
                .rentalPlace("Sochaczew")
                .returnPlace("Sochaczew")
                .build();
    }
}
