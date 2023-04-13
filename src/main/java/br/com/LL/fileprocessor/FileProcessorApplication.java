package br.com.LL.fileprocessor;

import br.com.LL.fileprocessor.converter.ClientFileToJsonConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class FileProcessorApplication implements CommandLineRunner {

	@Autowired
	private ClientFileToJsonConverter clientFileToJsonConverter;
	
	public static void main(String[] args) {
		SpringApplication.run(FileProcessorApplication.class, args);
	}

	@Override
	public void run(String... args) {
		var path = args[0];

		clientFileToJsonConverter.convertFiles(path);
	}
}
