package pl.domanski.carRent.customer.rent.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.domanski.carRent.customer.common.model.Car;
import pl.domanski.carRent.customer.common.model.CarPrice;
import pl.domanski.carRent.customer.common.repository.CarRepository;
import pl.domanski.carRent.customer.rent.controller.dto.CarRentDto;
import pl.domanski.carRent.customer.rent.controller.dto.RentDateAndPlace;
import pl.domanski.carRent.customer.rent.utils.CheckCarAvailabilityUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class RentServiceTest {

    @Mock
    CheckCarAvailabilityUtils checkCarAvailabilityUtils;

    @Mock
    CarRepository carRepository;

    @InjectMocks
    RentService rentService;

    @Test
    void should_show_all_cars() {
        //given
        RentDateAndPlace rentDateAndPlace = createRentDateAndPlace();
        given(carRepository.findAll()).willReturn(createCarsList());
        given(checkCarAvailabilityUtils.checkCarAvailability(1L, rentDateAndPlace)).willReturn(false);
        given(checkCarAvailabilityUtils.checkCarAvailability(2L, rentDateAndPlace)).willReturn(false);
        given(checkCarAvailabilityUtils.checkCarAvailability(3L, rentDateAndPlace)).willReturn(true);
        given(checkCarAvailabilityUtils.checkCarAvailability(4L, rentDateAndPlace)).willReturn(true);
        given(checkCarAvailabilityUtils.checkCarAvailability(5L, rentDateAndPlace)).willReturn(true);
        //when
        List<CarRentDto> result = rentService.showCars(rentDateAndPlace, false);
        //then
        assertThat(result, hasSize(5));
        assertEquals(3, result.get(3).getDays());
        assertEquals(3, result.get(4).getDays());
        assertEquals(BigDecimal.valueOf(165), result.get(3).getGrossValue());
        assertEquals(BigDecimal.valueOf(15000), result.get(4).getGrossValue());
        assertFalse(result.get(0).isAvailable());
        assertFalse(result.get(1).isAvailable());
        assertTrue(result.get(3).isAvailable());
        assertTrue(result.get(4).isAvailable());
    }

    @Test
    void should_show_only_available_cars() {
        //given
        RentDateAndPlace rentDateAndPlace = createRentDateAndPlace();
        given(carRepository.findAll()).willReturn(createCarsList());
        given(checkCarAvailabilityUtils.checkCarAvailability(1L, rentDateAndPlace)).willReturn(false);
        given(checkCarAvailabilityUtils.checkCarAvailability(2L, rentDateAndPlace)).willReturn(false);
        given(checkCarAvailabilityUtils.checkCarAvailability(3L, rentDateAndPlace)).willReturn(true);
        given(checkCarAvailabilityUtils.checkCarAvailability(4L, rentDateAndPlace)).willReturn(true);
        given(checkCarAvailabilityUtils.checkCarAvailability(5L, rentDateAndPlace)).willReturn(true);
        //when
        List<CarRentDto> result = rentService.showCars(rentDateAndPlace, true);
        //then
        assertThat(result, hasSize(3));
        assertEquals(3, result.get(1).getDays());
        assertEquals(3, result.get(2).getDays());
        assertEquals(BigDecimal.valueOf(165), result.get(1).getGrossValue());
        assertEquals(BigDecimal.valueOf(15000), result.get(2).getGrossValue());
        assertTrue(result.get(1).isAvailable());
        assertTrue(result.get(2).isAvailable());
    }

    private List<Car> createCarsList() {
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

    private RentDateAndPlace createRentDateAndPlace() {
        return RentDateAndPlace.builder()
                .rentalDate(LocalDateTime.now().plusDays(2))
                .returnDate(LocalDateTime.now().plusDays(5))
                .rentalPlace("Sochaczew")
                .returnPlace("Sochaczew")
                .build();
    }
}