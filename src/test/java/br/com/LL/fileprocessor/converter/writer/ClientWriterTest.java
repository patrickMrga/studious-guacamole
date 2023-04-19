package br.com.LL.fileprocessor.converter.writer;

import br.com.LL.fileprocessor.model.Client;
import br.com.LL.fileprocessor.utils.TestUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static br.com.LL.fileprocessor.utils.TestUtils.getClientsJsonOutput;
import static br.com.LL.fileprocessor.utils.TestUtils.getEmptyJsonOutput;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientWriterTest {

    private static final String OUTPUT_FILE = "src/test/resources/data/output.txt";
    
    private final ClientWriter clientWriter = new ClientWriter();

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void givenFileWriteOperation_whenInputClientListIsEmpty_thenGeneratedFileShouldBeEmptyJsonArray() throws IOException {
        var outputPath = Path.of(OUTPUT_FILE);
        clientWriter.writeJsonFile(new ArrayList<Client>(), outputPath);

        assertEquals(objectMapper.readTree(getEmptyJsonOutput()), objectMapper.readTree(Files.readString(outputPath)));
        
        Files.deleteIfExists(outputPath);
    }
    
    @Test
    public void givenFileWriteOperation_whenInputClientListIsNotEmpty_thenGeneratedFileShouldContainJsonObjectRepresentingClientsRead() throws IOException {
        Client cecilia = TestUtils.buildClientCecilia();
        cecilia.setOrders(TestUtils.buildSingleProductOrder1());

        var outputPath = Path.of(OUTPUT_FILE);
        clientWriter.writeJsonFile(List.of(cecilia), outputPath);
        
        assertEquals(objectMapper.readTree(getClientsJsonOutput()), objectMapper.readTree(Files.readString(outputPath)));
        
        Files.deleteIfExists(outputPath);
    }
}
