package br.com.LL.fileprocessor.converter;

import br.com.LL.fileprocessor.converter.combiner.ClientCombiner;
import br.com.LL.fileprocessor.converter.reader.ClientFileReader;
import br.com.LL.fileprocessor.converter.writer.ClientWriter;
import br.com.LL.fileprocessor.service.FileService;

import java.io.IOException;


public class ClientFileToJsonConverter {
    
    private final FileService fileService;
    
    private final ClientFileReader clientFileReader;
    
    private final ClientCombiner clientCombiner;
    
    private final ClientWriter clientWriter;

    public ClientFileToJsonConverter(FileService fileService, ClientFileReader clientFileReader, ClientCombiner clientCombiner, ClientWriter clientWriter) {
        this.fileService = fileService;
        this.clientFileReader = clientFileReader;
        this.clientCombiner = clientCombiner;
        this.clientWriter = clientWriter;
    }

    public void convertFiles(String ... args) {
        var argsCount = args.length;
        if (argsCount != 1) {
            System.out.println("Invalid number of arguments: " + argsCount + 
                    ". Expected only one arg describing file/directory path to process");
            
            throw new IllegalArgumentException();
        }
        
        var path = args[0];
        System.out.println("Processing file/directory " + path);
        
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
