package pl.domanski.carRent.admin.car.service.utils;

import com.github.slugify.Slugify;
import lombok.AllArgsConstructor;
import pl.domanski.carRent.admin.car.controller.dto.AdminCarDto;
import pl.domanski.carRent.admin.car.repository.AdminCarRepository;

@AllArgsConstructor
public class CarSlugUtils {

    private final AdminCarRepository adminCarRepository;

    public String createCarSlug(AdminCarDto adminCarDto) {
        Slugify slg = createSlugify();

        String slug = slg.slugify(String.join("-",adminCarDto.getBrand(),
                adminCarDto.getModel(),
                String.valueOf(adminCarDto.getYear())));

        slug = checkSlugIsExisting(slug);
        return slug;
    }

    
    private String checkSlugIsExisting(String slug) {
        if(adminCarRepository.findBySlug(slug).isPresent()) {
            if (slug.matches(".*-\\d")) {
                int actualCarNumber = Integer.parseInt(String.valueOf(slug.charAt(slug.length() - 1)));
                String newSlug = slug.substring(0, slug.length() - 1) + (actualCarNumber + 1);
                return checkSlugIsExisting(newSlug);
            } else {
                return checkSlugIsExisting(slug + "-2");
            }
        }
        return slug;
    }

    private static Slugify createSlugify() {
        return Slugify.builder()
                .customReplacement("_", "-")
                .build();
    }
}




