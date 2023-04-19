package br.com.LL.fileprocessor;

import br.com.LL.fileprocessor.converter.ClientFileToJsonConverter;
import br.com.LL.fileprocessor.converter.combiner.ClientCombiner;
import br.com.LL.fileprocessor.converter.reader.ClientFileReader;
import br.com.LL.fileprocessor.converter.reader.ClientMapper;
import br.com.LL.fileprocessor.converter.writer.ClientWriter;
import br.com.LL.fileprocessor.service.FileService;


public class FileProcessorApplication {
	
	public static void main(String[] args) {
		ClientFileToJsonConverter clientFileToJsonConverter = new ClientFileToJsonConverter(
				new FileService(),
				new ClientFileReader(new ClientMapper()),
				new ClientCombiner(),
				new ClientWriter()
		);
		
		clientFileToJsonConverter.convertFiles(args);
	}
	
}
