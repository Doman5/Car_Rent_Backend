package pl.domanski.carRent.admin.car.service.adminCarService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.domanski.carRent.admin.car.model.dto.AdminCarDto;
import pl.domanski.carRent.admin.car.model.AdminCar;
import pl.domanski.carRent.admin.car.repository.AdminCarDescriptionRepository;
import pl.domanski.carRent.admin.car.repository.AdminCarEquipmentRepository;
import pl.domanski.carRent.admin.car.repository.AdminCarPhotoRepository;
import pl.domanski.carRent.admin.car.repository.AdminCarPriceRepository;
import pl.domanski.carRent.admin.car.repository.AdminCarRepository;
import pl.domanski.carRent.admin.car.repository.AdminCarTechnicalSpecificationRepository;
import pl.domanski.carRent.admin.car.service.AdminCarService;
import pl.domanski.carRent.admin.common.repository.AdminCategoryRepository;

import java.math.BigDecimal;

import static org.aspectj.bridge.MessageUtil.fail;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static pl.domanski.carRent.admin.car.service.adminCarService.objectCreator.AdminCarServiceDataCreator.creatCarDto;
import static pl.domanski.carRent.admin.car.service.adminCarService.objectCreator.AdminCarServiceDataCreator.createCar;
import static pl.domanski.carRent.admin.car.service.adminCarService.objectCreator.AdminCarServiceDataCreator.createCarDtoWithoutDescriptionEquipmentAndPhotos;
import static pl.domanski.carRent.admin.car.service.adminCarService.objectCreator.AdminCarServiceDataCreator.createCarPrice;
import static pl.domanski.carRent.admin.car.service.adminCarService.objectCreator.AdminCarServiceDataCreator.createCarTechSpec;
import static pl.domanski.carRent.admin.car.service.adminCarService.objectCreator.AdminCarServiceDataCreator.createEquipmentList;
import static pl.domanski.carRent.admin.car.service.adminCarService.objectCreator.AdminCarServiceDataCreator.creteCarDtoWithoutAnyInfo;
import static pl.domanski.carRent.admin.car.service.adminCarService.objectCreator.AdminCarServiceDataCreator.creteCategory;
import static pl.domanski.carRent.admin.car.service.adminCarService.objectCreator.AdminCarServiceDataCreator.creteDescriptionList;
import static pl.domanski.carRent.admin.car.service.adminCarService.objectCreator.AdminCarServiceDataCreator.cretePhotoList;

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
    @Mock
    private AdminCategoryRepository adminCategoryRepository;


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
        given(adminCategoryRepository.findByName(any())).willReturn(creteCategory());
        //when
        AdminCar result = adminCarService.createCar(adminCarDto);
        //then
        assertThat(result).isNotNull();
        assertEquals("test-brand-test-model-9999", result.getSlug());
        assertEquals("test brand", result.getBrand());
        assertEquals("test model", result.getModel());
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
        given(adminCategoryRepository.findByName(any())).willReturn(creteCategory());
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
        AdminCarDto adminCarDto = creteCarDtoWithoutAnyInfo();

        try {
            adminCarService.createCar(adminCarDto);
            fail("Expected exception to be thrown");
        } catch (Exception e) {
            assertEquals("Nie podano podstawowych informacji", e.getMessage());
        }
    }


}