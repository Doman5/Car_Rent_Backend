package pl.domanski.carRent.admin.car.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pl.domanski.carRent.admin.car.controller.dto.AdminCarPhotoDto;
import pl.domanski.carRent.admin.car.service.AdminCarPhotoService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequiredArgsConstructor
public class AdminCarPhotoController {

    private final AdminCarPhotoService adminCarImageService;

    @PostMapping("/admin/cars/upload-photo")
    public AdminCarPhotoDto uploadImages(@RequestParam("file") MultipartFile multipartFile) {
        return adminCarImageService.uploadImages(multipartFile);
    }

    @GetMapping("/data/carPhotos/{filename}")
    public ResponseEntity<Resource> serveFiles(@PathVariable String filename) throws IOException {
        Resource file = adminCarImageService.serveFiles(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(Path.of(filename)))
                .body(file);
    }
}
