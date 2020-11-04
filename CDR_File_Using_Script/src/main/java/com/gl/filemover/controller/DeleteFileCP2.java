package com.gl.filemover.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gl.filemover.configuration.PropertiesReader;
import com.gl.filemover.dao.CDRFileRecordsInterfaceImpl;
import com.gl.filemover.entity.CDRFileRecords;
import com.gl.filemover.entity.CDRFilesDeleteAud;
import com.gl.filemover.repo.FileDeleteAudRepo;
import com.gl.filemover.repo.FileRepo;

@Component
public class DeleteFileCP2 {
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	PropertiesReader propertiesReader;
	@Autowired
	CDRFileRecordsInterfaceImpl dao;
	@Autowired
	FileDeleteAudRepo fileDeleteAudRepo;
	@Autowired 
	FileRepo fileRepo;
	public void cp3(String operator_param,String source_param) {
		// TODO Auto-generated method stub
		String delete_file_from_dir = propertiesReader.targetPath.trim()+"/"+operator_param+"/"+source_param+"/";
		String cdrRecdServer = propertiesReader.cdrRecdServer;
		Optional<List<CDRFileRecords>> optional = dao.findByOperatorAndStatusSIG1AndStatusSIG2(operator_param, "DONE",
				"DONE");
		log.info(" Number of files to Delete: " + optional.get().size());
		if (optional.get().size() > 0) {
			for (CDRFileRecords result : optional.get()) {
				CDRFilesDeleteAud cdrFilesDeleteAud = new CDRFilesDeleteAud();
				try {
					File file = new File(delete_file_from_dir + result.getFileName());
					if(file.exists()) {
					Path path = Paths.get(delete_file_from_dir + result.getFileName());
					Files.delete(path);
					log.info("File removed from this path " + delete_file_from_dir);
					
					// updating file status
					//dao.modifyFileStatus("DELETE", "DELETE", result.getId());
			
					try {
						
						cdrFilesDeleteAud.setSource(source_param);
						cdrFilesDeleteAud.setOperator(operator_param);
						cdrFilesDeleteAud.setFileName(result.getFileName());
						cdrFilesDeleteAud.setStatusSIG1("DONE");
						cdrFilesDeleteAud.setStatusSIG2("DONE");
						cdrFilesDeleteAud.setCdrRecdServer(cdrRecdServer);
						cdrFilesDeleteAud.setSig1Utime(result.getSig1Utime());
						cdrFilesDeleteAud.setSig2Utime(result.getSig2Utime());						
						fileDeleteAudRepo.save(cdrFilesDeleteAud);
						fileRepo.deleteById(result.getId());
						
					} catch (Exception e) {
						log.error("unusual happend during insertion in cdr_file_delete_aud table : " + e.toString());
					}
					}
					else {
						log.error("file does not exist");
						
					}
				
					
				} catch (IOException e) {
					log.error("Failed to remove file from path " + delete_file_from_dir);
					e.printStackTrace();

				} catch (Exception e) {
					log.error("oops updation failed due to reason : " + e.toString());
				}
			}
		}
	}
}