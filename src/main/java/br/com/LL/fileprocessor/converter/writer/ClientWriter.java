package br.com.LL.fileprocessor.converter.writer;

import br.com.LL.fileprocessor.model.Client;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;

public class ClientWriter {
    
    public void writeJsonFile(Collection<Client> clients, Path outputPath) {
        System.out.println("Writing clients into file " + outputPath.getFileName());
        try {
            new ObjectMapper().writerWithDefaultPrettyPrinter().writeValue(Files.newBufferedWriter(outputPath), clients);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Finished writing clients into file " + outputPath.getFileName());
    }
}
