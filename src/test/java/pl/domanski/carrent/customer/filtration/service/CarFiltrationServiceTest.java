package pl.domanski.carrent.customer.filtration.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.domanski.carrent.car.service.CarFiltrationService;
import pl.domanski.carrent.common.repository.CarRepository;
import pl.domanski.carrent.car.model.dto.FiltrationDto;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CarFiltrationServiceTest {

    @Mock
    CarRepository carRepository;

    @InjectMocks
    CarFiltrationService carFiltrationService;

    @Test
    void should_return_brands_filter_option() {
        //given
        given(carRepository.findAllBrandNames()).willReturn(List.of("BMW", "Audi", "Porsche"));
        given(carRepository.countByBrand("BMW")).willReturn(3L);
        given(carRepository.countByBrand("Audi")).willReturn(1L);
        given(carRepository.countByBrand("Porsche")).willReturn(2L);
        //when
        FiltrationDto carFilters = carFiltrationService.getCarFilters();
        //then
        assertEquals(Set.of("BMW","Audi","Porsche"), carFilters.getBrands().keySet());
        assertEquals(3 , carFilters.getBrands().size());
        assertEquals(1L, carFilters.getBrands().get("Audi"));
    }

    @Test
    void should_return_years_filter_option() {
        //given
        given(carRepository.findAllCarsYears()).willReturn(List.of(2020, 2015, 2010, 1999));
        given(carRepository.countByYear(2020)).willReturn(1L);
        given(carRepository.countByYear(2015)).willReturn(3L);
        given(carRepository.countByYear(2010)).willReturn(2L);
        given(carRepository.countByYear(1999)).willReturn(3L);
        //when
        FiltrationDto carFilters = carFiltrationService.getCarFilters();
        //then
        assertEquals(Set.of(2020, 2015, 2010, 1999), carFilters.getYears().keySet());
        assertEquals(4 , carFilters.getYears().size());
        assertEquals(3L, carFilters.getYears().get(1999));
    }

    @Test
    void should_return_all_filters_options() {
        //given
        given(carRepository.findAllBrandNames()).willReturn(List.of("a", "b", "c"));
        given(carRepository.countByBrand("a")).willReturn(4L);
        given(carRepository.countByBrand("b")).willReturn(9L);
        given(carRepository.countByBrand("c")).willReturn(11L);
        given(carRepository.findAllCarsYears()).willReturn(List.of(1, 2, 3, 4));
        given(carRepository.countByYear(1)).willReturn(1L);
        given(carRepository.countByYear(2)).willReturn(11L);
        given(carRepository.countByYear(3)).willReturn(22L);
        given(carRepository.countByYear(4)).willReturn(33L);
        //when
        FiltrationDto carFilters = carFiltrationService.getCarFilters();
        //then
        assertEquals(Set.of("a","b","c"), carFilters.getBrands().keySet());
        assertEquals(3 , carFilters.getBrands().size());
        assertEquals(4L, carFilters.getBrands().get("a"));
        assertEquals(Set.of(1, 2, 3, 4), carFilters.getYears().keySet());
        assertEquals(4 , carFilters.getYears().size());
        assertEquals(22L, carFilters.getYears().get(3));
    }
}