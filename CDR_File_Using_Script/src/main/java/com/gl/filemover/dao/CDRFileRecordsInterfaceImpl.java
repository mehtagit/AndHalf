package com.gl.filemover.dao;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.filemover.configuration.PropertiesReader;
import com.gl.filemover.entity.CDRFileRecords;
import com.gl.filemover.repo.FileRepo;
import com.gl.filemover.service.CDRFileRecordsService;

@Service
public class CDRFileRecordsInterfaceImpl implements CDRFileRecordsService {
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	PropertiesReader propertiesReader;
	@Autowired
	FileRepo fileRepo;

	@Transactional
	@Override
	public CDRFileRecords save(CDRFileRecords entity) {
		CDRFileRecords object = new CDRFileRecords();
		object.setSource(entity.getSource());
		object.setOperator(entity.getOperator());
		object.setFileName(entity.getFileName());
		object.setStatusSIG1("INIT");
		object.setStatusSIG2("INIT");
		object.setCdrRecdServer(propertiesReader.cdrRecdServer);
		log.info("request : " + object);
		fileRepo.save(object);
		log.info("record saved for this request : " + object);
		return object;
	}

	@Override
	public Optional<List<CDRFileRecords>> findByOperatorAndStatusSIG1AndStatusSIG2(String operator, String statusSig1,
			String statusSig2) {
		// TODO Auto-generated method stub
		Optional<List<CDRFileRecords>> optional = Optional.of(
				Optional.ofNullable(fileRepo.findByOperatorAndStatusSIG1AndStatusSIG2(operator, statusSig1, statusSig2))
						.orElse(null));
		log.info("gettting response for operator : " + operator + " :::: " + optional);
		return optional;
	}

	
	@Override
	public boolean modifyFileStatus(String sig1Status, String sig2Status,String sig1_utime, String sig2_utime,long id) {
		// TODO Auto-generated method stub
		boolean isModifyFileStaus = false;
		try {
			log.info("we're going to update file for this ID: "+id);
			fileRepo.modifyFileStatus(sig1Status, sig2Status, sig1_utime,sig2_utime,id);
			isModifyFileStaus = true;
			log.info("updated the file for ID : "+id);
			return isModifyFileStaus;
		} catch (Exception e) {
			log.error("oops updation failed for ID : "+id);
			log.error(e.toString());
			return isModifyFileStaus;
		}

	}
/*
	@Override
	public Optional<List<CDRFileRecords>> findByStatusSIG1AndStatusSIG2(String statusSig1, String statusSig2) {
		// TODO Auto-generated method stub
		Optional<List<CDRFileRecords>> optional = Optional.of(Optional.ofNullable(fileRepo.findByStatusSIG1AndStatusSIG2(statusSig1, statusSig2))
						.orElse(Collections.emptyList()));
		log.info("gettting response only those files whose status marked DONE in table : " + optional);
		return optional;
	}
	
	
	
	@Override
	public Optional<List<CDRFileRecords>> findByOperatorAndStatusSIG1OrStatusSIG2(String operator, String statusSig1,
			String statusSig2) {
		// TODO Auto-generated method stub
		Optional<List<CDRFileRecords>> optional = Optional.of(
				Optional.ofNullable(fileRepo.findByOperatorAndStatusSIG1OrStatusSIG2(operator, statusSig1, statusSig2))
						.orElse(null));
		log.info("gettting response for operator : " + operator + " :::: " + optional);
		return optional;
	}
	
	
*/

	@Override
	public Optional<List<CDRFileRecords>> findByOperatorAndStatusSIG1AndCdrRecdServer(String operator,
			String statusSig1, String cdrRecdServer) {
		// TODO Auto-generated method stub
		Optional<List<CDRFileRecords>> optional = Optional.of(
				Optional.ofNullable(fileRepo.findByOperatorAndStatusSIG1AndCdrRecdServer(operator, statusSig1, cdrRecdServer))
						.orElse(null));
		log.info("gettting response for operator : " + operator + " :::: " + optional);
		return optional;
	}

	@Override
	public Optional<List<CDRFileRecords>> findByOperatorAndStatusSIG2AndCdrRecdServer(String operator,
			String statusSig2, String cdrRecdServer) {
		// TODO Auto-generated method stub
		Optional<List<CDRFileRecords>> optional = Optional.of(
				Optional.ofNullable(fileRepo.findByOperatorAndStatusSIG2AndCdrRecdServer(operator, statusSig2, cdrRecdServer))
						.orElse(null));
		log.info("gettting response for operator : " + operator + " :::: " + optional);
		return optional;
	}
}
