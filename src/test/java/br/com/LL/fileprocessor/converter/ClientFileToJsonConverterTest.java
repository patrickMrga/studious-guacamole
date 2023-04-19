package br.com.LL.fileprocessor.converter;

import br.com.LL.fileprocessor.converter.combiner.ClientCombiner;
import br.com.LL.fileprocessor.converter.reader.ClientFileReader;
import br.com.LL.fileprocessor.converter.reader.ClientMapper;
import br.com.LL.fileprocessor.converter.writer.ClientWriter;
import br.com.LL.fileprocessor.model.Client;
import br.com.LL.fileprocessor.service.FileService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ClientFileToJsonConverterTest {

    private static final String EXPECTED_OUTPUT_FILE = "src/test/resources/data/input_output.json";
    
    private static final String INPUT_FILE = "src/test/resources/data/input.txt";
    
    private final FileService fileService = new FileService();
    
    private final ClientFileReader clientFileReader = new ClientFileReader(new ClientMapper());
    
    private final ClientCombiner clientCombiner = new ClientCombiner();
    
    private final ClientWriter clientWriter = new ClientWriter();
    
    private final ClientFileToJsonConverter converter = new ClientFileToJsonConverter(fileService, clientFileReader, clientCombiner, clientWriter);
    
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void givenFileConversion_whenNoPathsAreInputted_thenShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> converter.convertFiles());
    }

    @Test
    public void givenFileConversion_whenMoreThanOneArgumentIsGiven_thenShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> converter.convertFiles("src/test/resources/data/input.txt", "src/test/resources/data/input3.txt"));
    }
    
    @Test
    public void givenFileConversion_whenInputFileIsValid_thenShouldProduceValidJsonOutputFile() throws IOException {
        Path output_path = Path.of(EXPECTED_OUTPUT_FILE);
        Files.deleteIfExists(output_path);
        
        converter.convertFiles(INPUT_FILE);
        assertTrue(Files.exists(output_path));

        var processedClients = objectMapper.readValue(new File(EXPECTED_OUTPUT_FILE), new TypeReference<List<Client>>() {});
        assertEquals(2, processedClients.size());

        var clientFilter = processedClients.stream().filter(c -> c.getUserId() == 61L).toList();
        assertEquals(1, clientFilter.size());
        var frodo = clientFilter.get(0);
        var frodoOrders = frodo.getOrders();
        assertEquals(2, frodoOrders.size());

        clientFilter = processedClients.stream().filter(c -> c.getUserId() == 77L).toList();
        assertEquals(1, clientFilter.size());
        var robert = clientFilter.get(0);
        var robertOrders = robert.getOrders();
        assertEquals(3, robertOrders.size());
        
        Files.deleteIfExists(output_path);
    }
}
