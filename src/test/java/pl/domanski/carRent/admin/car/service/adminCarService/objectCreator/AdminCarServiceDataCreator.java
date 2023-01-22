package pl.domanski.carRent.admin.car.service.adminCarService.objectCreator;

import pl.domanski.carRent.admin.car.model.dto.AdminCarDescriptionDto;
import pl.domanski.carRent.admin.car.model.dto.AdminCarDto;
import pl.domanski.carRent.admin.car.model.dto.AdminCarEquipmentDto;
import pl.domanski.carRent.admin.car.model.dto.AdminCarPhotoDto;
import pl.domanski.carRent.admin.car.model.dto.AdminCarPriceDto;
import pl.domanski.carRent.admin.car.model.dto.AdminCarTechnicalSpecificationDto;
import pl.domanski.carRent.admin.car.model.AdminCar;
import pl.domanski.carRent.admin.car.model.AdminCarDescription;
import pl.domanski.carRent.admin.car.model.AdminCarEquipment;
import pl.domanski.carRent.admin.car.model.AdminCarPhoto;
import pl.domanski.carRent.admin.car.model.AdminCarPrice;
import pl.domanski.carRent.admin.car.model.AdminCarTechnicalSpecification;
import pl.domanski.carRent.admin.category.model.AdminCategory;
import pl.domanski.carRent.admin.common.dto.AdminCategoryDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class AdminCarServiceDataCreator {

    public static AdminCarDto createCarDtoWithoutDescriptionEquipmentAndPhotos() {
        return AdminCarDto.builder()
                .brand("test brand")
                .model("test model")
                .year(9999)
                .bodyType("Sedan")
                .carTechnicalSpecification(AdminCarTechnicalSpecificationDto.builder()
                        .power(111)
                        .acceleration("test acceleration1")
                        .seats("test seats1")
                        .fuel("test fuel1")
                        .gearbox("test gearbox1")
                        .drive("test drive1")
                        .engine("test engine1")
                        .build())
                .carPrice(AdminCarPriceDto.builder()
                        .priceDay(BigDecimal.valueOf(10))
                        .priceHalfWeek(BigDecimal.valueOf(20))
                        .priceWeek(BigDecimal.valueOf(30))
                        .priceTwoWeeks(BigDecimal.valueOf(40))
                        .priceMonth(BigDecimal.valueOf(50))
                        .build())
                .category(AdminCategoryDto.builder()
                        .name("test category")
                        .build())
                .build();
    }

    public static List<AdminCarPhoto> cretePhotoList() {
        return List.of(AdminCarPhoto.builder()
                .id(1L)
                .photo("test photo1")
                .carId(1L)
                .build());
    }

    public static AdminCarPrice createCarPrice() {
        return AdminCarPrice.builder()
                .priceDay(BigDecimal.valueOf(10))
                .priceHalfWeek(BigDecimal.valueOf(20))
                .priceWeek(BigDecimal.valueOf(30))
                .priceTwoWeeks(BigDecimal.valueOf(40))
                .priceMonth(BigDecimal.valueOf(50))
                .build();
    }

    public static List<AdminCarDescription> creteDescriptionList() {
        return List.of(AdminCarDescription.builder()
                        .id(1L)
                        .description("test description1")
                        .carId(1L)
                        .build(),
                AdminCarDescription.builder()
                        .id(2L)
                        .description("test description2")
                        .carId(1L)
                        .build());
    }

    public static List<AdminCarEquipment> createEquipmentList() {
        return List.of(AdminCarEquipment.builder()
                        .id(1L)
                        .name("test equipment 1")
                        .carId(1L)
                        .build(),
                AdminCarEquipment.builder()
                        .id(2L)
                        .name("test equipment 2")
                        .carId(1L)
                        .build(),
                AdminCarEquipment.builder()
                        .id(3L)
                        .name("test equipment 3")
                        .carId(1L)
                        .build());
    }

    public static AdminCarTechnicalSpecification createCarTechSpec() {
        return AdminCarTechnicalSpecification.builder()
                .id(1L)
                .power(111)
                .acceleration("test acceleration1")
                .seats("test seats1")
                .fuel("test fuel1")
                .gearbox("test gearbox1")
                .drive("test drive1")
                .engine("test engine1")
                .build();
    }

    public static AdminCar createCar() {
        return AdminCar.builder()
                .id(1L)
                .brand("test brand")
                .model("test model")
                .year(9999)
                .slug("test-brand-test-model-9999")
                .build();
    }

    public static AdminCarDto creatCarDto() {
        return AdminCarDto.builder()
                .brand("test brand")
                .model("test model")
                .year(9999)
                .bodyType("Suv")
                .carTechnicalSpecification(AdminCarTechnicalSpecificationDto.builder()
                        .power(111)
                        .acceleration("test acceleration1")
                        .seats("test seats1")
                        .fuel("test fuel1")
                        .gearbox("test gearbox1")
                        .drive("test drive1")
                        .engine("test engine1")
                        .build())
                .equipments(List.of(AdminCarEquipmentDto.builder()
                                .name("test equipment 1")
                                .build(),
                        AdminCarEquipmentDto.builder()
                                .name("test equipment 2")
                                .build(),
                        AdminCarEquipmentDto.builder()
                                .name("test equipment 3")
                                .build()))
                .descriptions(List.of(AdminCarDescriptionDto.builder()
                                .description("test description1")
                                .build(),
                        AdminCarDescriptionDto.builder()
                                .description("test description2")
                                .build()))
                .carPrice(AdminCarPriceDto.builder()
                        .priceDay(BigDecimal.valueOf(10))
                        .priceHalfWeek(BigDecimal.valueOf(20))
                        .priceWeek(BigDecimal.valueOf(30))
                        .priceTwoWeeks(BigDecimal.valueOf(40))
                        .priceMonth(BigDecimal.valueOf(50))
                        .build())
                .photos(List.of(AdminCarPhotoDto.builder()
                        .photo("test photo1")
                        .build()))
                .category(AdminCategoryDto.builder()
                        .name("test category")
                        .build())
                .build();
    }

    public static AdminCarDto creteCarDtoWithoutAnyInfo() {
        return AdminCarDto.builder().build();
    }

    public static Optional<AdminCategory> creteCategory() {
        return Optional.of(AdminCategory.builder()
                .id(1L)
                .name("test category")
                .build());
    }
}
