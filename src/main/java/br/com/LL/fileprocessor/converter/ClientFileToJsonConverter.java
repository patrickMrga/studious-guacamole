package br.com.LL.fileprocessor.converter;

import br.com.LL.fileprocessor.converter.combiner.ClientCombiner;
import br.com.LL.fileprocessor.converter.reader.ClientFileReader;
import br.com.LL.fileprocessor.converter.writer.ClientWriter;
import br.com.LL.fileprocessor.service.FileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
@AllArgsConstructor
public class ClientFileToJsonConverter {
    
    private FileService fileService;
    
    private ClientFileReader clientFileReader;
    
    private ClientCombiner clientCombiner;
    
    private ClientWriter clientWriter;
    
    public void convertFiles(String path) {
        log.info("Processing file/directory " + path);
        
        var inputFilePaths = fileService.getFilePaths(path);
        inputFilePaths.forEach(p -> {
            var clients = clientFileReader.readFile(p);
            var joinedClients = clientCombiner.joinClientsById(clients);

            try {
                var outputPath = fileService.createOutputFilePath(p.toString());
                clientWriter.writeJsonFile(joinedClients, outputPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
