package com.gl.CEIR.FileProcess.ServiceImpl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.CEIR.FileProcess.Utility.Util;
import com.gl.ceir.config.model.ConsignmentMgmt;
import com.gl.ceir.config.model.DeviceDb;
import com.gl.ceir.config.model.WebActionDb;
import com.gl.ceir.config.repository.ConsignmentRepository;
import com.gl.ceir.config.repository.StokeDetailsRepository;
import com.gl.ceir.config.repository.WebActionDbRepository;

@Service
public class ConsignmentRegisterServiceImpl {	

	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	Util util;

	@Autowired
	WebActionDbRepository webActionDbRepository;

	@Autowired
	ConsignmentRepository consignmentRepository;

	@Autowired
	StokeDetailsRepository stokeDetailsRepository;

	ConcurrentHashMap<String, String> chm =  new ConcurrentHashMap<String, String>();

	public boolean saveprocess(WebActionDb webActionDb) {

		try {

			webActionDb.setState(1);
			webActionDbRepository.save(webActionDb);

			ConsignmentMgmt consignmentMgmt = consignmentRepository.getByTxnId(webActionDb.getTxnId());

			consignmentMgmt.setConsignmentStatus(1);
			consignmentRepository.save(consignmentMgmt);
			log.info("File Status is update as processing ");

			Path filePath = Paths.get("/home/ubuntu/apache-tomcat-9.0.4/webapps/Design/"+webActionDb.getTxnId()+"/"+consignmentMgmt.getFileName());

			String errorFilePath ="";
			String moveFIlePath =" ";

			List<DeviceDb> devices = new ArrayList<DeviceDb>();

			List<String> contents = Files.readAllLines(filePath);

			log.info("File is reading starts="+contents);
			for(String content : contents) {

				DeviceDb device = util.parseDevice(content);
				device.setImporterTxnId(webActionDb.getTxnId());
				device.setImporterUserId(1L);

				String value = chm.get(device.getImeiEsnMeid());

				if(Objects.isNull(value)) {

					chm.put(device.getImeiEsnMeid(), device.getImeiEsnMeid());
				}else {

					String header = "ErrorCode,Description";	
					String record = "";
					util.writeInFile(errorFilePath, header, record, moveFIlePath);	
				}

				devices.add(device);
			}

			// Status to check if entries are saved.
			stokeDetailsRepository.saveAll(devices);

			consignmentMgmt.setConsignmentStatus(3);
			consignmentRepository.save(consignmentMgmt);

			return Boolean.TRUE;

		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage(), e);
			return Boolean.FALSE;
		}

	}

}
