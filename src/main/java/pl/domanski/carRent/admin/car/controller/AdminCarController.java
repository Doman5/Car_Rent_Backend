package pl.domanski.carRent.admin.car.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pl.domanski.carRent.admin.car.controller.dto.AdminCarBasicInfo;
import pl.domanski.carRent.admin.car.controller.dto.AdminCarDto;
import pl.domanski.carRent.admin.car.controller.dto.AdminCarPhotoDto;
import pl.domanski.carRent.admin.car.model.AdminCar;
import pl.domanski.carRent.admin.car.service.AdminCarPhotoService;
import pl.domanski.carRent.admin.car.service.AdminCarService;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("admin/cars")
@RequiredArgsConstructor
public class AdminCarController {

    private final AdminCarService adminCarService;
    private final AdminCarPhotoService adminCarPhotoService;

    @GetMapping
    public Page<AdminCarBasicInfo> getCars(Pageable pageable) {
        return adminCarService.getCars(pageable);
    }

    @GetMapping("/{id}")
    public AdminCar getCar(@PathVariable Long id) {
        return  adminCarService.getCar(id);
    }

    @PostMapping
    public AdminCar createCar(@RequestBody @Valid AdminCarDto adminCarDto) {
        return adminCarService.createCar(adminCarDto);
    }

    @PostMapping("/upload-photos")
    public List<AdminCarPhotoDto> uploadImages(@RequestParam("file") MultipartFile[] multipartFile) {
        return adminCarPhotoService.uploadImages(multipartFile);
    }

    @GetMapping("/data/carPhoto/{filename}")
    public ResponseEntity<Resource> serveFiles(@PathVariable String filename) throws IOException {
        Resource file = adminCarPhotoService.serveFiles(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(Path.of(filename)))
                .body(file);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        adminCarService.deleteCar(id);
    }

}
