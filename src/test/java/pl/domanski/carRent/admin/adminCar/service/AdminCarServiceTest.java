package pl.domanski.carRent.admin.adminCar.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.domanski.carRent.admin.adminCar.controller.dto.AdminCarDto;
import pl.domanski.carRent.admin.adminCar.controller.dto.AdminCarEquipmentDto;
import pl.domanski.carRent.admin.adminCar.controller.dto.AdminCarTechnicalSpecificationDto;
import pl.domanski.carRent.admin.adminCar.model.AdminCar;
import pl.domanski.carRent.admin.adminCar.model.AdminCarEquipment;
import pl.domanski.carRent.admin.adminCar.model.AdminCarTechnicalSpecification;
import pl.domanski.carRent.admin.adminCar.repository.AdminCarEquipmentRepository;
import pl.domanski.carRent.admin.adminCar.repository.AdminCarRepository;
import pl.domanski.carRent.admin.adminCar.repository.AdminCarTechnicalSpecificationRepository;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class AdminCarServiceTest {

    @Mock
    AdminCarRepository adminCarRepository;
    @Mock
    AdminCarTechnicalSpecificationRepository adminCarTechSpecRepository;
    @Mock
    AdminCarEquipmentRepository adminCarEquipmentRepository;

    @InjectMocks
    AdminCarService adminCarService;

    @Test
    void should_update_Car() {
        //given
        Long carId = 1L;
        AdminCarDto adminCarDto = createAdminCarDto();
        given(adminCarRepository.findById(carId)).willReturn(Optional.of(createTestAdminCar()));
        given(adminCarEquipmentRepository.findAllByCarId(carId))
                .willReturn(createEquipmentList());
        given(adminCarTechSpecRepository.save(any())).willReturn(createAdminTechSpec());
        given(adminCarRepository.save(any())).willAnswer(invocationOnMock -> invocationOnMock.getArguments()[0]);
        //when
        AdminCar result = adminCarService.updateCar(adminCarDto, 1L);
        //then
        assertEquals(1L, result.getId());
        assertEquals("Audi", result.getBrand());
        assertEquals("A4", result.getModel());
        assertEquals(2010, result.getYear());
        assertEquals(200, result.getAdminCarTechnicalSpecification().getPower());
        assertEquals(2L, result.getAdminCarTechnicalSpecification().getId());
        assertThat(result.getEquipments(), hasSize(3));
        assertEquals(3L, result.getEquipments().get(2).getId());
    }

    private List<AdminCarEquipment> createEquipmentList() {
        return List.of(AdminCarEquipment.builder().id(1L).name("Klimatyzacja").build(),
                AdminCarEquipment.builder().id(2L).name("Pakiet Carbon").build(),
                AdminCarEquipment.builder().id(3L).name("S-Line").build()
        );
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
                                .build())
                .carEquipments(List.of(AdminCarEquipmentDto.builder().name("Klimatyzacja").build(),
                        AdminCarEquipmentDto.builder().name("Pakiet Carbon").build(),
                        AdminCarEquipmentDto.builder().name("S-Line").build()
                ))
                .build();
    }

}