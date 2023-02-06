package pl.domanski.carRent.admin.car.utils;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import pl.domanski.carRent.admin.car.repository.AdminCarRepository;
import pl.domanski.carRent.admin.common.utils.SlugifyUtils;

import java.nio.file.Files;
import java.nio.file.Path;
@Component
@RequiredArgsConstructor
public class FileNameUtils {

    private final AdminCarRepository adminCarRepository;

    public static String renameIfExists(Path uploadDir, String fileName) {
        if(Files.exists(uploadDir.resolve(fileName))) {
            return renameAndCheck(uploadDir, fileName);
        }
        return fileName;
    }

    private static String renameAndCheck(Path uploadDir, String fileName) {
        String newName = renameFileName(fileName);
        if(Files.exists(uploadDir.resolve(newName))) {
            newName = renameAndCheck(uploadDir, newName);
        }
        return newName;
    }

    private static String renameFileName(String fileName) {
        String name = FilenameUtils.getBaseName(fileName);
        String[] split = name.split("-(?=[0-9]+$)");
        int counter = split.length > 1 ? Integer.parseInt(split[1]) + 1 : 1;
        return split[0] + "-" + counter + "." + FilenameUtils.getExtension(fileName);
    }

    public  String prepareFreeFileName(MultipartFile multipartFiles, String carName) {
        int carCounter = 1;
        while (adminCarRepository.findBySlug(carName + "-" + carCounter).isPresent()) {
            carCounter++;
        }
        String extension = FilenameUtils.getExtension(multipartFiles.getOriginalFilename());
        String carNameWithCounter = carName + "-" + carCounter + "-1" + "." + extension;
        String slugifyFileName = SlugifyUtils.slugifyFileName(carNameWithCounter);
        String uploadDir = "./data/carPhotos/";
        return FileNameUtils.renameIfExists(Path.of(uploadDir), slugifyFileName);
    }
}
