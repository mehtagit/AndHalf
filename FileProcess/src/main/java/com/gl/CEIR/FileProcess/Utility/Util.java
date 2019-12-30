package com.gl.CEIR.FileProcess.Utility;

import java.io.File;
import java.io.FileWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.gl.CEIR.FileProcess.model.constants.Separator;

@Component
public class Util {

	private Logger log = LoggerFactory.getLogger(getClass());

	File tmpDir;

	public void writeInFile(String fileName, String header, String record, String nameFileLocation) {

		try {
			tmpDir = new File(fileName);
			FileWriter fw = new FileWriter(fileName, Boolean.TRUE);

			if(tmpDir.exists()) {
				tmpDir.renameTo(new File(nameFileLocation));
				tmpDir.delete();
			}else {
				fw.append(header);
			}

			fw.append(Separator.NEW_LINE);
			fw.append(record);
			fw.close();

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

}
