package pl.domanski.carRent.admin.car.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pl.domanski.carRent.admin.car.controller.dto.AdminCarPhotoDto;
import pl.domanski.carRent.admin.car.model.AdminCarPhoto;
import pl.domanski.carRent.admin.car.repository.AdminCarPhotoRepository;
import pl.domanski.carRent.admin.car.repository.AdminCarRepository;
import pl.domanski.carRent.admin.car.service.utils.ExistingFileNameUtils;
import pl.domanski.carRent.admin.common.utils.SlugifyUtils;

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

    private String uploadDir = "./data/carPhotos/";
    private final AdminCarPhotoRepository adminCarPhotoRepository;
    private final AdminCarRepository adminCarRepository;

    public AdminCarPhotoDto uploadImages(MultipartFile multipartFiles, Long carId) {
        String carName = adminCarRepository.findById(carId).orElseThrow().getSlug();
        String extension = FilenameUtils.getExtension(multipartFiles.getOriginalFilename());
        String newName = carName + "-1" + "." + extension;
        String slugifyFileName = SlugifyUtils.slugifyFileName(newName);
        String newFileName = ExistingFileNameUtils.renameIfExists(Path.of(uploadDir), slugifyFileName);
        Path filePath = Paths.get(uploadDir).resolve(newFileName);

        try (InputStream inputStream = multipartFiles.getInputStream()) {
            OutputStream outputStream = Files.newOutputStream(filePath);
            inputStream.transferTo(outputStream);
            AdminCarPhoto savedPhoto = adminCarPhotoRepository.save(AdminCarPhoto.builder()
                    .carId(carId)
                    .photo(newFileName)
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
        String path ="data/carPhotos/" + photo.getPhoto();
        System.out.println(path);
        try{
            File file = new File(path);
            if(!file.delete()) {
                throw new RuntimeException("Nie udało się usunąć pliku");
            }
        } catch (Exception e) {
            e.getMessage();
        }
        adminCarPhotoRepository.deleteById(photoId);
    }
}
