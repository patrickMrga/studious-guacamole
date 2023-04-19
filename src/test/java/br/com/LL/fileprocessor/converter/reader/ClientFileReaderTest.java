package br.com.LL.fileprocessor.converter.reader;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ClientFileReaderTest {

    private static final String VALID_INPUT_FILE = "src/test/resources/data/input.txt";

    private static final String MALFORMED_INPUT_FILE = "src/test/resources/data/input3.txt";
    
    private final ClientFileReader clientFileReader = new ClientFileReader(new ClientMapper());
    
    @Test
    public void givenClientFileReading_whenInputFileIsValid_thenShouldReturnListOfAllProcessedClients() {
        var clients = clientFileReader.readFile(Path.of(VALID_INPUT_FILE));
        assertEquals(5, clients.size());

        var clientRobertEntries = clients.stream().filter(c -> c.getUserId() == 77).toList();
        assertEquals(3, clientRobertEntries.size());

        var clientFrodoEntries = clients.stream().filter(c -> c.getUserId() == 61).toList();
        assertEquals(2, clientFrodoEntries.size());
    }

    @Test
    public void givenClientFileReading_whenInputFileIsMalformed_thenShouldThrowException() {
        assertThrows(RuntimeException.class, () -> clientFileReader.readFile(Path.of(MALFORMED_INPUT_FILE)));
    }
    
}
