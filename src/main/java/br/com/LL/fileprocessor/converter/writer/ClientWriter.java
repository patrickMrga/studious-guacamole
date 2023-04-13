package br.com.LL.fileprocessor.converter.writer;

import br.com.LL.fileprocessor.model.Client;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;

@Component
@Slf4j
public class ClientWriter {
    
    public void writeJsonFile(Collection<Client> clients, Path outputPath) {
        log.info("Writing clients into file " + outputPath.getFileName());
        try {
            new ObjectMapper().writerWithDefaultPrettyPrinter().writeValue(Files.newBufferedWriter(outputPath), clients);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        log.info("Finished writing clients into file " + outputPath.getFileName());
    }
}
