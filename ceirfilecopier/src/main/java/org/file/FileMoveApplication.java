package org.file;

import org.file.service.FileTransfer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
@SpringBootApplication(scanBasePackages= {"org.file"})
public class FileMoveApplication{	
	public static void main(String[] args) {
		//String processName = args[0];
		ConfigurableApplicationContext	ctx =SpringApplication.run(FileMoveApplication.class, args);
		FileTransfer object = ctx.getBean(FileTransfer.class);
		object._processMethod("1");
	}

}
