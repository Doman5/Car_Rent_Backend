package pl.domanski.carRent.admin.car.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pl.domanski.carRent.admin.car.model.dto.AdminCarPhotoDto;
import pl.domanski.carRent.admin.car.model.AdminCarPhoto;
import pl.domanski.carRent.admin.car.repository.AdminCarPhotoRepository;
import pl.domanski.carRent.admin.car.repository.AdminCarRepository;
import pl.domanski.carRent.admin.car.utils.FileNameUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminCarPhotoService {

    private final String uploadDir = "./data/carPhotos/";
    private final AdminCarPhotoRepository adminCarPhotoRepository;
    private final AdminCarRepository adminCarRepository;
    private final FileNameUtils fileNameUtils;

    public AdminCarPhotoDto uploadImages(MultipartFile multipartFiles, Long carId) {
        String carName = adminCarRepository.findById(carId).orElseThrow().getSlug();
        String freeFileName = fileNameUtils.prepareFreeFileName(multipartFiles, carName);
        Path filePath = Paths.get(uploadDir).resolve(freeFileName);

        try (InputStream inputStream = multipartFiles.getInputStream()) {
            OutputStream outputStream = Files.newOutputStream(filePath);
            inputStream.transferTo(outputStream);
            AdminCarPhoto savedPhoto = adminCarPhotoRepository.save(AdminCarPhoto.builder()
                    .carId(carId)
                    .photo(freeFileName)
                    .build());
            return mapToAdminCarPhotoDto(savedPhoto);
        } catch (IOException e) {
            throw new RuntimeException("nie mogę zapisać pliku", e);
        }
    }

    public AdminCarPhotoDto uploadImagesForNewCar(MultipartFile multipartFiles, String carName) {
        String freeFileName = fileNameUtils.prepareFreeFileName(multipartFiles, carName);
        Path filePath = Paths.get(uploadDir).resolve(freeFileName);

        try (InputStream inputStream = multipartFiles.getInputStream()) {
            OutputStream outputStream = Files.newOutputStream(filePath);
            inputStream.transferTo(outputStream);
            AdminCarPhoto savedPhoto = adminCarPhotoRepository.save(AdminCarPhoto.builder()
                    .photo(freeFileName)
                    .build());
            return mapToAdminCarPhotoDto(savedPhoto);
        } catch (IOException e) {
            throw new RuntimeException("nie mogę zapisać pliku", e);
        }

    }

    public Resource serveFiles(String filename) {
        FileSystemResourceLoader fileSystemResourceLoader = new FileSystemResourceLoader();
        return fileSystemResourceLoader.getResource(uploadDir + filename);
    }

    public List<AdminCarPhotoDto> getCarPhotos(Long id) {
        return adminCarPhotoRepository.findAllByCarId(id).stream()
                .map(this::mapToAdminCarPhotoDto)
                .toList();
    }

    private AdminCarPhotoDto mapToAdminCarPhotoDto(AdminCarPhoto photo) {
        return AdminCarPhotoDto.builder()
                .id(photo.getId())
                .photo(photo.getPhoto())
                .build();
    }

    @Transactional
    public void deletePhoto(Long photoId) {
        AdminCarPhoto photo = adminCarPhotoRepository.findById(photoId).orElseThrow();
        String path = "data/carPhotos/" + photo.getPhoto();
        System.out.println(path);
        File file = new File(path);
        if (!file.delete()) {
            throw new RuntimeException("Nie udało się usunąć pliku");
        }
        adminCarPhotoRepository.deleteById(photoId);
    }
}
