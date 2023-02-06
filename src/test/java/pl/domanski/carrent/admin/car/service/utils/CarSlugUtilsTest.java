package pl.domanski.carRent.admin.car.service.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.domanski.carRent.admin.car.model.dto.AdminCarDto;
import pl.domanski.carRent.admin.car.model.AdminCar;
import pl.domanski.carRent.admin.car.repository.AdminCarRepository;
import pl.domanski.carRent.admin.car.utils.CarSlugUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CarSlugUtilsTest {

    @Mock
    AdminCarRepository adminCarRepository;

    @InjectMocks
    CarSlugUtils carSlugUtils;

    @Test
    void should_create_car_slug() {
        //given
        AdminCarDto adminCarDto1 = new AdminCarDto("AUDI", "A3", 2020);
        AdminCarDto adminCarDto2 = new AdminCarDto("BMW", "Seria 3", 2016);
        AdminCarDto adminCarDto3 = new AdminCarDto("Nissan", "370Z", 2007);
        //when
        String slug1 = carSlugUtils.createCarSlug(adminCarDto1);
        String slug2 = carSlugUtils.createCarSlug(adminCarDto2);
        String slug3 = carSlugUtils.createCarSlug(adminCarDto3);
        //then
        assertEquals("audi-a3-2020-1", slug1);
        assertEquals("bmw-seria-3-2016-1", slug2);
        assertEquals("nissan-370z-2007-1", slug3);
    }

    @Test
    void should_create_car_slug_when_car_with_that_slug_exist_in_database() {
        //given
        AdminCarDto adminCarDto = new AdminCarDto("AUDI", "A3", 2020);
        given(adminCarRepository.findBySlug("audi-a3-2020-1")).willReturn(Optional.of(AdminCar.builder().build()));
        //when
        String slug = carSlugUtils.createCarSlug(adminCarDto);
        //then
        assertEquals("audi-a3-2020-2", slug);
    }

    @Test
    void should_create_car_slug_with_additional_number() {
        //given
        AdminCarDto adminCarDto = new AdminCarDto("AUDI", "A3", 2020);
        given(adminCarRepository.findBySlug(any())).willReturn(Optional.of(AdminCar.builder().build()))
                .willReturn(Optional.of(AdminCar.builder().build()))
                .willReturn(Optional.empty());
        //when
        String slug = carSlugUtils.createCarSlug(adminCarDto);
        //then
        assertEquals("audi-a3-2020-3", slug);
    }

    @Test
    void should_create_slug_with_additional_number_and_it_already_exist() {
        //given
        AdminCarDto adminCarDto = new AdminCarDto("AUDI", "A3", 2020);
        given(adminCarRepository.findBySlug(any())).willReturn(Optional.of(AdminCar.builder().build()))
                .willReturn(Optional.of(AdminCar.builder().build()))
                .willReturn(Optional.of(AdminCar.builder().build()))
                .willReturn(Optional.empty());
        //when
        String slug = carSlugUtils.createCarSlug(adminCarDto);
        //then
        assertEquals("audi-a3-2020-4", slug);
    }




}