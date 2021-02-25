package read.excel.ReadExcel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import read.excel.ReadExcel.FileReader.FileReader;

@SpringBootApplication
public class ReadExcelApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ReadExcelApplication.class, args);
		FileReader reader = (FileReader) context.getBean("fileReader");
		if("MASTER".equalsIgnoreCase(args[0])) {
			reader.upload();
			}
		if("PROVINCE".equalsIgnoreCase(args[0])) {
			reader.province();
			}
		if("DISTRICT".equalsIgnoreCase(args[0])) {
		reader.fileRead();
		}
		
		else if("COMMUNE".equalsIgnoreCase(args[0])) {
			reader.commune();
		}
		else if("VILLAGE".equalsIgnoreCase(args[0])) {
		reader.village();
		}
		context = null;
	}

}
