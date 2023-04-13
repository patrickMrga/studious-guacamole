package br.com.LL.fileprocessor.service;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class FileServiceTest {
    
    private final FileService fileService = new FileService();

    @Test
    public void givenFileSearch_whenInputPathIsEmpty_thenShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> fileService.getFilePaths(null));
    }

    @Test
    public void givenFileSearch_whenInputPathIsNotFileOrDirectory_thenShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> fileService.getFilePaths("src/test/resources/invalid"));
    }

    @Test
    public void givenFileSearch_whenInputPathIsAValidTextFile_thenShouldReturnFilePath() {
        List<Path> filePaths = fileService.getFilePaths("src/test/resources/data/input.txt");
        
        assertEquals(1, filePaths.size());
        assertEquals("input.txt", filePaths.get(0).getFileName().toString());
    }

    @Test
    public void givenFileSearch_whenInputPathIsAValidDirectory_thenShouldReturnListOfTextFilePaths() {
        List<Path> filePaths = fileService.getFilePaths("src/test/resources/data/");

        assertEquals(2, filePaths.size());
        
        var fileNames = filePaths.stream().map(p -> p.getFileName().toString()).toArray();
        assertTrue(Arrays.asList(fileNames).contains("input.txt"));
        assertTrue(Arrays.asList(fileNames).contains("input3.txt"));
    }
    
    @Test
    public void givenOutputFilePathCreation_whenFileAlreadyExists_thenShouldDeleteFile() throws IOException {
        var outputFilePath = Path.of("src/test/resources/data/input_output.json");
        
        if (Files.notExists(outputFilePath)) {
            Files.createFile(outputFilePath);
        }

        assertThat(outputFilePath).exists();
        
        Path createdFilePath = fileService.createOutputFilePath("src/test/resources/data/input.txt");
        assertEquals("src/test/resources/data/input_output.json", createdFilePath.toString());
        assertThat(outputFilePath).doesNotExist();
    }
}
