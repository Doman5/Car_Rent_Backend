package pl.domanski.carRent.admin.adminCar.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.domanski.carRent.admin.adminCar.controller.dto.AdminCarDto;
import pl.domanski.carRent.admin.adminCar.controller.dto.AdminCarTechnicalSpecificationDto;
import pl.domanski.carRent.admin.adminCar.model.AdminCar;
import pl.domanski.carRent.admin.adminCar.model.AdminCarTechnicalSpecification;
import pl.domanski.carRent.admin.adminCar.repository.AdminCarRepository;
import pl.domanski.carRent.admin.adminCar.repository.AdminCarTechnicalSpecificationRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdminCarServiceTest {

    @Mock
    AdminCarRepository adminCarRepository;
    @Mock
    AdminCarTechnicalSpecificationRepository adminCarTechSpecRepository;

    @InjectMocks
    AdminCarService adminCarService;

    @Test
    void should_update_Car() {
        //given
        Long carId = 1L;
        AdminCarDto adminCarDto = createAdminCarDto();
        given(adminCarRepository.findById(carId)).willReturn(Optional.of(createTestAdminCar()));
        when(adminCarTechSpecRepository.save(any())).thenReturn(createAdminTechSpec());
        given(adminCarRepository.save(any())).willReturn(createAdminCar());
        //when
        AdminCar result = adminCarService.updateCar(adminCarDto, 1L);
        //then
        assertEquals(1L, result.getId());
        assertEquals("Audi", result.getBrand());
        assertEquals("A4", result.getModel());
        assertEquals(2010, result.getYear());
        assertEquals(200, result.getAdminCarTechnicalSpecification().getPower());
        assertEquals(2L, result.getAdminCarTechnicalSpecification().getId());
    }

    private static AdminCar createTestAdminCar() {
        return AdminCar.builder()
                .adminCarTechnicalSpecification(AdminCarTechnicalSpecification.builder()
                        .id(2L).build()).build();
    }

    private AdminCarTechnicalSpecification createAdminTechSpec() {
        return AdminCarTechnicalSpecification.builder()
                .id(2L)
                .power(200)
                .engine("R4 2.0")
                .drive("FWD")
                .acceleration("7.2")
                .gearbox("automatic")
                .fuel("diesel")
                .seats("5")
                .build();
    }

    private AdminCarDto createAdminCarDto() {
        return AdminCarDto.builder()
                .brand("Audi")
                .model("A4")
                .year(2010)
                .adminCarTechnicalSpecificationDto(
                        AdminCarTechnicalSpecificationDto.builder()
                                .power(200)
                                .engine("R4 2.0")
                                .drive("FWD")
                                .acceleration("7.2")
                                .gearbox("automatic")
                                .fuel("diesel")
                                .seats("5")
                                .build()
                )
                .build();
    }
    private AdminCar createAdminCar() {
        return AdminCar.builder()
                .id(1L)
                .brand("Audi")
                .model("A4")
                .year(2010)
                .adminCarTechnicalSpecification(
                        AdminCarTechnicalSpecification.builder()
                                .id(2L)
                                .power(200)
                                .engine("R4 2.0")
                                .drive("FWD")
                                .acceleration("7.2")
                                .gearbox("automatic")
                                .fuel("diesel")
                                .seats("5")
                                .build()
                )
                .build();
    }
}