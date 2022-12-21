package pl.domanski.carRent.admin.car.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.domanski.carRent.admin.car.service.utils.ExistingFileNameUtils;
import pl.domanski.carRent.admin.common.utils.SlugifyUtils;
import pl.domanski.carRent.admin.car.controller.dto.AdminCarPhotoDto;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminCarPhotoService {
    @Value("{app.uploadDir}")
    private String uploadDir = "";

    public List<AdminCarPhotoDto> uploadImages(MultipartFile[] multipartFiles) {
        try {
            File localDirectory = new File(uploadDir);
            List<AdminCarPhotoDto> carPhotos = new ArrayList<>();

            for (MultipartFile file : multipartFiles) {
                String fileName = file.getName();
                String slugifyFileName = SlugifyUtils.slugifyFileName(fileName);
                String newFileName = ExistingFileNameUtils.renameIfExists(Path.of(uploadDir), slugifyFileName);
                File destinationFile = new File(localDirectory, newFileName);
                file.transferTo(destinationFile);
                carPhotos.add(new AdminCarPhotoDto(newFileName));
            }

            return carPhotos;
        } catch (IOException e) {
            throw new RuntimeException("Nie można zapisać plików");
        }
    }

    public Resource serveFiles(String filename) {
        FileSystemResourceLoader fileSystemResourceLoader = new FileSystemResourceLoader();
        return fileSystemResourceLoader.getResource(uploadDir + filename);
    }
}
