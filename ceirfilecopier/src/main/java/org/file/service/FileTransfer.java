package org.file.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.file.dao.CDRFileRecordsInterfaceImpl;
import org.file.entity.CDRFileRecords;
import org.file.properties.PropertiesReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FileTransfer {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	CDRFileRecordsInterfaceImpl cDRFileRecordsServiceImpl;

	@Autowired
	SocketAliveUitlity socketAliveUitlity;
	@Autowired
	PropertiesReader propertiesReader;

	// configuration parameter
	// String source_directory ="C:/Users/user/Videos/bk/";
	// String target_directory ="C:/Users/user/Videos/tmp/";
	String source_directory = propertiesReader.sourcePath;
	String target_directory = propertiesReader.targetPath;
//	String hostName = propertiesReader.hostName;
	//int port = propertiesReader.serverPort;
	//int timeout = propertiesReader.timeout;

	public void _processMethod(String processName) {
		switch (processName) {
		case "1":
			_move_to_temp();
			break;

		case "2":
			_temp_to_actual_dir();
			break;

		case "3":
			_delete_file();
			break;

		}
	}

	public void _move_to_temp() {
		log.info("source_directory : " + source_directory);
		log.info("target_directory : " + target_directory);

		String[] arr = source_directory.split("/");
		String source = arr[arr.length - 1];
		String operator = arr[arr.length - 2];

		// If this pathname does not denote a directory, then listFiles() returns null.
		File[] files = new File(source_directory).listFiles();
		log.info("Number of files : " + files.length);
		for (File file : files) {
			CDRFileRecords entity = new CDRFileRecords();
			if (file.isFile()) {
				String destinationPath = target_directory + file.getName();
				log.info("moving file to destinationPath:" + destinationPath);
				Path temp;
				try {
					temp = Files.move(Paths.get(source_directory + file.getName()), Paths.get(destinationPath));
					log.info("File moved successfully to destination path " + destinationPath);
					entity.setSource(source);
					entity.setOperator(operator);
					entity.setFileName(file.getName());

					// calling save method()
					cDRFileRecordsServiceImpl.save(entity);

				} catch (IOException e) {
					// TODO Auto-generated catch block
					log.info("Failed to move the file");
					e.printStackTrace();
				}

			}
		}
	}

	public void _temp_to_actual_dir() {
		// checking isServerUtilityAlive
	/*	log.info("inside _temp_to_actual_dir method");
		boolean isServerUtilityAlive = socketAliveUitlity.isSocketAlive(hostName, port, timeout);
		if (isServerUtilityAlive == true) {

		}
		*/
	}

	private void _delete_file() {
		// TODO Auto-generated method stub
		log.info("not implemented yet");
	}

}
