package com.gl.filemover.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gl.filemover.configuration.PropertiesReader;
import com.gl.filemover.dao.CDRFileRecordsInterfaceImpl;
import com.gl.filemover.entity.CDRFileRecords;

@Component
public class FileMoveOnCurrentMachine {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	CDRFileRecordsInterfaceImpl cDRFileRecordsServiceImpl;

	@Autowired
	PropertiesReader propertiesReader;

	String start_timeStamp = null;
	long start_time = 0;
	 int count = 0;
	public void cp1(String operator_param,String source_param) {
		String sourceDirectory = propertiesReader.sourcePath.trim()+"/"+operator_param+"/"+source_param+"/";
		String targetDirectory = propertiesReader.targetPath.trim()+"/"+operator_param+"/"+source_param+"/";
		log.info("source_directory : " + sourceDirectory+" >> target_directory : " + targetDirectory);
		// If this pathname does not denote a directory, then listFiles() returns null.
		File[] files = new File(sourceDirectory).listFiles();
		log.info("Operator "+operator_param+" >> source "+source_param+" ready to move Number of files : " + files.length);
		for (File file : files) {
			CDRFileRecords entity = new CDRFileRecords();
			if (file.isFile()) {
				 start_time =  System.currentTimeMillis();
				 start_timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
				String destinationPath = targetDirectory + file.getName();
				log.info("moving file to destinationPath:" + destinationPath);
				Path temp;
				try {
					temp = Files.move(Paths.get(sourceDirectory + file.getName()), Paths.get(destinationPath));
					log.info("File moved successfully to destination path " + destinationPath);
					count = count +1;
					entity.setSource(source_param.trim());
					entity.setOperator(operator_param.trim());
					entity.setFileName(file.getName());
					entity.setSig1Utime(null);
					entity.setSig2Utime(null);
					// calling save method()
					cDRFileRecordsServiceImpl.save(entity);
			
				} catch (IOException e) {
					// TODO Auto-generated catch block
					log.info("Failed to move the file due to reason : "+ e.toString());
					e.printStackTrace();
				}
		
			}
		}
		long end_time=System.currentTimeMillis();
		String end_timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
		//		Start time, end time, file transferred, total time taken, operator, source, file transferred per second.
		log.info("MOVE PROCESS ::  start time : "+start_timeStamp +" >> end time : "+end_timeStamp+" >> file transferred : "+count+" >> total time taken :"+((end_time-start_time) / 1000) / 60 +"minutes and "+((end_time-start_time) / 1000) % 60+" seconds >>"+" operator :"+operator_param +" >> source :"+source_param +" >> file transferred per second");	
		//count / ((((end_time-start_time) / 1000) / 60)*60) +(((end_time-start_time) / 1000) % 60 )
	}

}
