package pl.domanski.carrent.admin.car.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.domanski.carrent.admin.car.model.dto.AdminCarBasicInfo;
import pl.domanski.carrent.admin.car.model.dto.AdminCarDto;
import pl.domanski.carrent.admin.car.model.AdminCar;
import pl.domanski.carrent.admin.car.model.AdminCarPhoto;
import pl.domanski.carrent.admin.car.repository.AdminCarDescriptionRepository;
import pl.domanski.carrent.admin.car.repository.AdminCarEquipmentRepository;
import pl.domanski.carrent.admin.car.repository.AdminCarPhotoRepository;
import pl.domanski.carrent.admin.car.repository.AdminCarPriceRepository;
import pl.domanski.carrent.admin.car.repository.AdminCarRepository;
import pl.domanski.carrent.admin.car.repository.AdminCarTechnicalSpecificationRepository;
import pl.domanski.carrent.admin.car.mapper.AdminCarMapper;
import pl.domanski.carrent.admin.car.utils.CarSlugUtils;
import pl.domanski.carrent.admin.common.repository.AdminCategoryRepository;

import java.io.File;
import java.util.List;

import static pl.domanski.carrent.admin.car.mapper.AdminCarMapper.mapToAdminCar;
import static pl.domanski.carrent.admin.car.mapper.AdminCarMapper.mapToAdminCarDescription;
import static pl.domanski.carrent.admin.car.mapper.AdminCarMapper.mapToAdminCarEquipment;
import static pl.domanski.carrent.admin.car.mapper.AdminCarMapper.mapToAdminCarPrice;
import static pl.domanski.carrent.admin.car.mapper.AdminCarMapper.mapToAdminCarTechSpec;


@Service
@RequiredArgsConstructor
public class AdminCarService {

    private final static Long EMPTY_ID = null;
    private final String uploadDir = "./data/carPhotos/";

    private final AdminCarRepository adminCarRepository;
    private final AdminCarTechnicalSpecificationRepository adminCarTechnicalSpecificationRepository;
    private final AdminCarEquipmentRepository adminCarEquipmentRepository;
    private final AdminCarDescriptionRepository adminCarDescriptionRepository;
    private final AdminCarPriceRepository adminCarPriceRepository;
    private final AdminCarPhotoRepository adminCarPhotoRepository;
    private final AdminCategoryRepository adminCategoryRepository;

    public Page<AdminCarBasicInfo> getCars(Pageable pageable) {
        return adminCarRepository.findAll(pageable)
                .map(AdminCarMapper::mapToAdminCarBasicInfo);
    }

    public AdminCar getCar(Long id) {
        return adminCarRepository.findById(id).orElseThrow();
    }

    @Transactional
    public AdminCar createCar(AdminCarDto adminCarDto) {

        if(carBasicInfoAreNull(adminCarDto)) {
            throw new RuntimeException("Nie podano podstawowych informacji");
        }
        String slug = createSlug(adminCarDto, adminCarRepository);
        AdminCar adminCar = adminCarRepository.save(mapToAdminCar(adminCarDto, slug, 1L));

        ifExistSaveCarTechnicalSpecification(adminCarDto, adminCar);
        ifExistSaveCarEquipments(adminCarDto, adminCar);
        ifExistSaveCarDescriptions(adminCarDto, adminCar);
        ifExistSaveCarPrice(adminCarDto, adminCar);
        ifExistSaveCarPhotos(adminCarDto, adminCar);
        ifExistSetCarCategory(adminCarDto, adminCar);

        return adminCarRepository.save(adminCar);
    }

    private void ifExistSetCarCategory(AdminCarDto adminCarDto, AdminCar adminCar) {
        if(adminCarDto.getCategory() != null) {
            Long categoryId = adminCategoryRepository.findByName(adminCarDto.getCategory().getName()).orElseThrow().getId();
            adminCar.setCategoryId(categoryId);
        }
    }

    private void ifExistSaveCarPhotos(AdminCarDto adminCarDto, AdminCar adminCar) {
        if(adminCarDto.getPhotos() != null) {
            List<AdminCarPhoto> photoList = adminCarPhotoRepository.findAllPhotosWhatConstrainCarSlug(adminCar.getSlug() + "%");
            photoList.forEach(photo -> photo.setCarId(adminCar.getId()));
            adminCarPhotoRepository.saveAll(photoList);
        }
    }

    private void ifExistSaveCarPrice(AdminCarDto adminCarDto, AdminCar adminCar) {
        if(adminCarDto.getCarPrice() != null) {
            adminCar.setCarPrice(adminCarPriceRepository.save(mapToAdminCarPrice(adminCarDto.getCarPrice(), EMPTY_ID)));
        }
    }

    private void ifExistSaveCarDescriptions(AdminCarDto adminCarDto, AdminCar adminCar) {
        if(adminCarDto.getDescriptions() != null) {
            adminCar.setDescriptions(adminCarDescriptionRepository
                    .saveAll(adminCarDto.getDescriptions().stream().map(des -> mapToAdminCarDescription(des, adminCar.getId())).toList()));
        }
    }

    private void ifExistSaveCarEquipments(AdminCarDto adminCarDto, AdminCar adminCar) {
        if(adminCarDto.getEquipments() != null) {
            adminCar.setEquipments(adminCarEquipmentRepository
                    .saveAll(adminCarDto.getEquipments().stream().map(eq -> mapToAdminCarEquipment(eq, adminCar.getId())).toList()));
        }
    }

    private void ifExistSaveCarTechnicalSpecification(AdminCarDto adminCarDto, AdminCar adminCar) {
        if(adminCarDto.getCarTechnicalSpecification() != null) {
            adminCar.setAdminCarTechnicalSpecification(adminCarTechnicalSpecificationRepository
                    .save(mapToAdminCarTechSpec(adminCarDto.getCarTechnicalSpecification(), EMPTY_ID)));
        }
    }

    private String createSlug(AdminCarDto adminCarDto, AdminCarRepository adminCarRepository) {
        CarSlugUtils carSlugUtils = new CarSlugUtils(adminCarRepository);
        return carSlugUtils.createCarSlug(adminCarDto);
    }

    private static boolean carBasicInfoAreNull(AdminCarDto adminCarDto) {
        return adminCarDto.getBrand() == null || adminCarDto.getModel() == null || adminCarDto.getYear() == 0;
    }

    @Transactional
    public void deleteCar(Long id) {
        List<AdminCarPhoto> photos = adminCarPhotoRepository.findAllByCarId(id);
        photos.forEach(photo -> {
            File file = new File(uploadDir + photo.getPhoto());
            file.delete();
        });
        adminCarRepository.deleteById(id);
    }

}


