package read.excel.ReadExcel.configuration;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

@Component
public class PropertiesFileReader {
	@Value("${source_path}")
	public String sourcePath;
}
