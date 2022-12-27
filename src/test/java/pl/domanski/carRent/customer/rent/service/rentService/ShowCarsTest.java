package pl.domanski.carRent.customer.rent.service.rentService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.domanski.carRent.customer.common.repository.CarRepository;
import pl.domanski.carRent.customer.rent.controller.dto.CarRentDto;
import pl.domanski.carRent.customer.rent.controller.dto.RentDateAndPlace;
import pl.domanski.carRent.customer.rent.service.RentService;
import pl.domanski.carRent.customer.rent.utils.CheckCarAvailabilityUtils;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static pl.domanski.carRent.customer.rent.service.rentService.RentServiceCreateData.createCarsList;
import static pl.domanski.carRent.customer.rent.service.rentService.RentServiceCreateData.createRentDateAndPlace;

@ExtendWith(MockitoExtension.class)
public class ShowCarsTest {

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
}
