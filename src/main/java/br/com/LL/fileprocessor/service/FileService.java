package br.com.LL.fileprocessor.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@Slf4j
public class FileService {
    
    public List<Path> getFilePaths(String inputPath) {
        if (Objects.isNull(inputPath) || inputPath.length() == 0) {
            log.error("Input path must not be null or empty");

            throw new IllegalArgumentException();
        }
        
        var path = Path.of(inputPath);
        if (Files.notExists(path)) {
            log.error("Invalid file/directory path");
            
            throw new IllegalArgumentException();
        }

        try (var paths = Files.find(path, Integer.MAX_VALUE, (filePath, fileAttr) -> fileAttr.isRegularFile() && filePath.toString().toLowerCase().endsWith("txt"))) {
            List<Path> filePaths = paths.collect(Collectors.toList());
            log.info("Found " + filePaths.size() + " file(s) to process");
            
            return filePaths;
        } catch (IOException e) {
            log.error("Could not read file(s) on path " + inputPath);
            throw new RuntimeException(e);
        }
    }
    
    public Path createOutputFilePath(String inputFilePath) throws IOException {
        var suffixIndex = inputFilePath.lastIndexOf(".txt");
        var outputPath = Path.of(inputFilePath.substring(0, suffixIndex).concat("_output.json"));
        
        Files.deleteIfExists(outputPath);
        
        return outputPath;
    }
    
}
