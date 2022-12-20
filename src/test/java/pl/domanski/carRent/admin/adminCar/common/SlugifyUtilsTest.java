package pl.domanski.carRent.admin.adminCar.common;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SlugifyUtilsTest {

    @ParameterizedTest
    @CsvSource({
            "test test.png, test-test.png",
            "Hello world.png, hello-world.png",
            "gość.png, gosc.png",
            "Produkt 1.png, produkt-1.png",
            "Produkt_1.png, produkt-1.png"
    })
    void should_slugify_file_name(String in, String out) {
        String fileName = SlugifyUtils.slugifyFileName(in);
        assertEquals(fileName, out);
    }
}