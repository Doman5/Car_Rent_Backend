package pl.domanski.carRent.admin.car.service;

import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.domanski.carRent.admin.car.controller.dto.AdminCarPhotoDto;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class AdminCarPhotoService {

    private final String uploadDir = "./data/carPhotos/";

    public AdminCarPhotoDto uploadImages(MultipartFile multipartFiles) {
        String filename = multipartFiles.getOriginalFilename();
        Path filePath = Paths.get(uploadDir).resolve(filename);

        try(InputStream inputStream = multipartFiles.getInputStream()) {
            OutputStream outputStream = Files.newOutputStream(filePath);
            inputStream.transferTo(outputStream);
            return new AdminCarPhotoDto(filename);
        } catch (IOException e) {
            throw new RuntimeException("nie mogę zapisać pliku", e);
        }

//        try {
//            File localDirectory = new File(uploadDir);
//            List<AdminCarPhotoDto> carPhotos = new ArrayList<>();
//
//                String fileName = file.getName();
//                String slugifyFileName = SlugifyUtils.slugifyFileName(fileName);
//                String newFileName = ExistingFileNameUtils.renameIfExists(Path.of(uploadDir), slugifyFileName);
//                File destinationFile = new File(localDirectory, newFileName);
//                file.transferTo(destinationFile);
//                carPhotos.add(new AdminCarPhotoDto(newFileName));


//            return carPhotos;
//        } catch (IOException e) {
//            throw new RuntimeException("Nie można zapisać plików");
//        }
    }

    public Resource serveFiles(String filename) {
        FileSystemResourceLoader fileSystemResourceLoader = new FileSystemResourceLoader();
        return fileSystemResourceLoader.getResource(uploadDir + filename);
    }
}
