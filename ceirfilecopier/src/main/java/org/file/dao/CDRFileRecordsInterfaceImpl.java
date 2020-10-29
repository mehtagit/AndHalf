package org.file.dao;

import javax.transaction.Transactional;

import org.file.entity.CDRFileRecords;
import org.file.interfacePackage.CDRFileRecordsService;
import org.file.properties.PropertiesReader;
import org.file.repo.FileRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class CDRFileRecordsInterfaceImpl implements CDRFileRecordsService {
	private final Logger log = LoggerFactory.getLogger(getClass());

	PropertiesReader propertyFileReader=new PropertiesReader();
	@Autowired
	FileRepo fileRepo;

	@Transactional
	@Override
	public CDRFileRecords save(CDRFileRecords entity) {
		// TODO Auto-generated method stub
		CDRFileRecords object = new CDRFileRecords();
		object.setSource(entity.getSource());
		object.setOperator(entity.getOperator());
		object.setFileName(entity.getFileName());
		object.setStatusSIG1("INIT");
		object.setStatusSIG2("INIT");
		object.setCdrRecdServer(propertyFileReader.cdrRecdServer);
		log.info("request : " + object);
		fileRepo.save(object);		
		log.info("record saved for this request : " + object);
		return object;
	}	
	
}
