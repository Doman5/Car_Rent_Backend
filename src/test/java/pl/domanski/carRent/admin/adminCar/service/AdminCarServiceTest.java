package pl.domanski.carRent.admin.adminCar.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.domanski.carRent.admin.adminCar.controller.dto.AdminCarDto;
import pl.domanski.carRent.admin.adminCar.model.AdminCar;
import pl.domanski.carRent.admin.adminCar.repository.AdminCarDescriptionRepository;
import pl.domanski.carRent.admin.adminCar.repository.AdminCarEquipmentRepository;
import pl.domanski.carRent.admin.adminCar.repository.AdminCarPhotoRepository;
import pl.domanski.carRent.admin.adminCar.repository.AdminCarPriceRepository;
import pl.domanski.carRent.admin.adminCar.repository.AdminCarRepository;
import pl.domanski.carRent.admin.adminCar.repository.AdminCarTechnicalSpecificationRepository;

import java.math.BigDecimal;

import static org.aspectj.bridge.MessageUtil.fail;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static pl.domanski.carRent.admin.adminCar.service.objectCreator.AdminCarServiceObjectCreator.creatCarDto;
import static pl.domanski.carRent.admin.adminCar.service.objectCreator.AdminCarServiceObjectCreator.createCar;
import static pl.domanski.carRent.admin.adminCar.service.objectCreator.AdminCarServiceObjectCreator.createCarDtoWithoutDescriptionEquipmentAndPhotos;
import static pl.domanski.carRent.admin.adminCar.service.objectCreator.AdminCarServiceObjectCreator.createCarPrice;
import static pl.domanski.carRent.admin.adminCar.service.objectCreator.AdminCarServiceObjectCreator.createCarTechSpec;
import static pl.domanski.carRent.admin.adminCar.service.objectCreator.AdminCarServiceObjectCreator.createEquipmentList;
import static pl.domanski.carRent.admin.adminCar.service.objectCreator.AdminCarServiceObjectCreator.creteCarDtoWithoutBasicInfo;
import static pl.domanski.carRent.admin.adminCar.service.objectCreator.AdminCarServiceObjectCreator.creteDescriptionList;
import static pl.domanski.carRent.admin.adminCar.service.objectCreator.AdminCarServiceObjectCreator.cretePhotoList;

@ExtendWith(MockitoExtension.class)
class AdminCarServiceTest {
    @Mock
    private AdminCarRepository adminCarRepository;
    @Mock
    private AdminCarTechnicalSpecificationRepository adminCarTechnicalSpecificationRepository;
    @Mock
    private AdminCarEquipmentRepository adminCarEquipmentRepository;
    @Mock
    private AdminCarDescriptionRepository adminCarDescriptionRepository;
    @Mock
    private AdminCarPriceRepository adminCarPriceRepository;
    @Mock
    private AdminCarPhotoRepository adminCarPhotoRepository;

    @InjectMocks
    private AdminCarService adminCarService;

    @Test
    void should_create_car() {
        //given
        AdminCarDto adminCarDto = creatCarDto();
        given(adminCarRepository.save(any())).willReturn(createCar()).willAnswer(invocationOnMock -> invocationOnMock.getArguments()[0]);
        given(adminCarTechnicalSpecificationRepository.save(any())).willReturn(createCarTechSpec());
        given(adminCarEquipmentRepository.saveAll(anyList())).willReturn(createEquipmentList());
        given(adminCarDescriptionRepository.saveAll(anyList())).willReturn(creteDescriptionList());
        given(adminCarPriceRepository.save(any())).willReturn(createCarPrice());
        given(adminCarPhotoRepository.saveAll(anyList())).willReturn(cretePhotoList());
        //when
        AdminCar result = adminCarService.createCar(adminCarDto);
        //then
        assertThat(result).isNotNull();
        assertEquals("test brand", result.getBrand());
        assertEquals("test model", result.getModel());
        assertThat(result.getPhotos()).asList();
        assertThat(result.getDescriptions()).asList();
        assertThat(result.getEquipments()).asList();
        assertEquals(BigDecimal.valueOf(10), result.getCarPrice().getPriceDay());
        assertEquals("test fuel1", result.getAdminCarTechnicalSpecification().getFuel());
    }

    @Test
    void should_create_car_without_description_equipment_and_photos() {
        //given
        AdminCarDto adminCarDto = createCarDtoWithoutDescriptionEquipmentAndPhotos();
        given(adminCarRepository.save(any())).willReturn(createCar()).willAnswer(invocationOnMock -> invocationOnMock.getArguments()[0]);
        given(adminCarTechnicalSpecificationRepository.save(any())).willReturn(createCarTechSpec());
        given(adminCarPriceRepository.save(any())).willReturn(createCarPrice());
        //when
        AdminCar result = adminCarService.createCar(adminCarDto);
        //then
        assertThat(result).isNotNull();
        assertEquals("test brand", result.getBrand());
        assertEquals("test model", result.getModel());
        assertThat(result.getPhotos()).isNull();
        assertThat(result.getDescriptions()).isNull();
        assertThat(result.getEquipments()).isNull();
        assertEquals(BigDecimal.valueOf(10), result.getCarPrice().getPriceDay());
        assertEquals("test fuel1", result.getAdminCarTechnicalSpecification().getFuel());
    }

    @Test
    void should_not_crete_car() {
        AdminCarDto adminCarDto = creteCarDtoWithoutBasicInfo();

        try {
            adminCarService.createCar(adminCarDto);
            fail("Expected exception to be thrown");
        } catch (Exception e) {
            assertEquals("Nie podano podstawowych informacji", e.getMessage());
        }
    }


}