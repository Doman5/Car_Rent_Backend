package pl.domanski.carRent.admin.common.utils;


import com.github.slugify.Slugify;
import org.apache.commons.io.FilenameUtils;

public class SlugifyUtils {

    public static String slugifyFileName(String fileName) {
        String name = FilenameUtils.getBaseName(fileName);
        Slugify slg = createSlugifyInstance();
        String changedName = slg.slugify(name);
        return changedName + "." + FilenameUtils.getExtension(fileName);
    }

    private static Slugify createSlugifyInstance() {
        return Slugify.builder()
                .customReplacement("_", "-")
                .build();

    }

}
