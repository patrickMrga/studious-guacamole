package br.com.LL.fileprocessor.converter.reader;

import br.com.LL.fileprocessor.model.Client;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@Slf4j
@AllArgsConstructor
public class ClientFileReader {
    
    private ClientMapper clientMapper;
    
    public List<Client> readFile(Path inputFilePath) {
        log.info("Reading file " + inputFilePath.getFileName());
        
        var clients = new ArrayList<Client>();
        long lineIndex = 0L;
        
        try (var reader = Files.newBufferedReader(inputFilePath)) {
            String line;
            
            while (Objects.nonNull(line = reader.readLine())) {
                lineIndex++;
                clients.add(clientMapper.convert(line));
            }
        } catch (IOException | ParseException e) {
            log.error("Error parsing line " + lineIndex + " from file " + inputFilePath.getFileName(), e);
            
            throw new RuntimeException(e);
        }
        
        log.info("Finished reading file " + inputFilePath.getFileName());
        
        return clients;
    }
}
