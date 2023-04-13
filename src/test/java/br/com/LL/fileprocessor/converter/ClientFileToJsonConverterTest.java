package br.com.LL.fileprocessor.converter;

import br.com.LL.fileprocessor.converter.combiner.ClientCombiner;
import br.com.LL.fileprocessor.converter.reader.ClientFileReader;
import br.com.LL.fileprocessor.converter.reader.ClientMapper;
import br.com.LL.fileprocessor.converter.writer.ClientWriter;
import br.com.LL.fileprocessor.service.FileService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class ClientFileToJsonConverterTest {
    
    private final FileService fileService = new FileService();
    
    private final ClientFileReader clientFileReader = new ClientFileReader(new ClientMapper());
    
    private final ClientCombiner clientCombiner = new ClientCombiner();
    
    private final ClientWriter clientWriter = new ClientWriter();
    
    private final ClientFileToJsonConverter converter = new ClientFileToJsonConverter(fileService, clientFileReader, clientCombiner, clientWriter);
    
    @Test
    public void givenFileConversion_whenInputFileIsValid_thenShouldProduceValidJsonOutputFile() {
        converter.convertFiles("src/test/resources/data/input.txt");
        //TODO create inputFile and compare output to model  
    }
}
