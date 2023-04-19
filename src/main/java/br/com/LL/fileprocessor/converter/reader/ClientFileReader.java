package br.com.LL.fileprocessor.converter.reader;

import br.com.LL.fileprocessor.model.Client;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClientFileReader {
    
    private final ClientMapper clientMapper;
    
    public ClientFileReader(ClientMapper mapper) {
        this.clientMapper = mapper;
    }
    
    public List<Client> readFile(Path inputFilePath) {
        System.out.println("Reading file " + inputFilePath.getFileName());
        
        var clients = new ArrayList<Client>();
        long lineIndex = 0L;
        
        try (var reader = Files.newBufferedReader(inputFilePath)) {
            String line;
            
            while (Objects.nonNull(line = reader.readLine())) {
                lineIndex++;
                clients.add(clientMapper.convert(line));
            }
        } catch (ParseException | NumberFormatException e) {
            System.out.println("Error parsing line " + lineIndex + " from file " + inputFilePath.getFileName() + " - " + e.getMessage());
            
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("Error reading input file " + inputFilePath + " - " + e.getMessage());
            
            throw new RuntimeException(e);
        }

        System.out.println("Finished reading file " + inputFilePath.getFileName());
        
        return clients;
    }
}
