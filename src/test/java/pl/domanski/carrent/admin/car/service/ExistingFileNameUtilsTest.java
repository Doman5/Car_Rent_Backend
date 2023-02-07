package pl.domanski.carrent.admin.car.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import pl.domanski.carrent.admin.car.utils.FileNameUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class ExistingFileNameUtilsTest {

    @Test
    void shouldRenameFile(@TempDir Path tempDir) throws IOException {
        Files.createFile(tempDir.resolve("test.png"));
        String newName = FileNameUtils.renameIfExists(tempDir, "test.png");
        assertEquals("test-1.png", newName);
    }

    @Test
    void shouldRenameCarFile(@TempDir Path tempDir) throws IOException {
        Files.createFile(tempDir.resolve("audi-a4-2020-1.png"));
        String newName = FileNameUtils.renameIfExists(tempDir, "audi-a4-2020-1.png");
        assertEquals("audi-a4-2020-2.png", newName);
    }

    @Test
    void shouldRenameSecondTheSameCarFile(@TempDir Path tempDir) throws IOException {
        Files.createFile(tempDir.resolve("audi-a4-2020-2-1.png"));
        String newName = FileNameUtils.renameIfExists(tempDir, "audi-a4-2020-2-1.png");
        assertEquals("audi-a4-2020-2-2.png", newName);
    }

    @Test
    void shouldRenameManyExistingFile(@TempDir Path tempDir) throws IOException {
        Files.createFile(tempDir.resolve("test.png"));
        Files.createFile(tempDir.resolve("test-1.png"));
        Files.createFile(tempDir.resolve("test-2.png"));
        Files.createFile(tempDir.resolve("test-3.png"));
        String newName = FileNameUtils.renameIfExists(tempDir, "test.png");
        assertEquals("test-4.png", newName);
    }

    @Test
    void shouldRenameManyExistingCarFile(@TempDir Path tempDir) throws IOException {
        Files.createFile(tempDir.resolve("audi-a4-2020-1.png"));
        Files.createFile(tempDir.resolve("audi-a4-2020-2.png"));
        Files.createFile(tempDir.resolve("audi-a4-2020-3.png"));
        Files.createFile(tempDir.resolve("audi-a4-2020-4.png"));
        String newName = FileNameUtils.renameIfExists(tempDir, "audi-a4-2020-1.png");
        assertEquals("audi-a4-2020-5.png", newName);
    }


}