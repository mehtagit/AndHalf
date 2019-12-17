package com.gl.CEIR.FileProcess.ServiceImpl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.CEIR.FileProcess.Utility.Util;
import com.gl.CEIR.FileProcess.service.WebActionService;
import com.gl.ceir.config.model.ConsignmentMgmt;
import com.gl.ceir.config.model.DeviceDb;
import com.gl.ceir.config.model.WebActionDb;
import com.gl.ceir.config.model.constants.WebActionStatus;
import com.gl.ceir.config.repository.ConsignmentRepository;
import com.gl.ceir.config.repository.StokeDetailsRepository;
import com.gl.ceir.config.repository.WebActionDbRepository;

@Service
public class ConsignmentUpdateServiceImpl implements WebActionService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	Util util;

	@Autowired
	WebActionDbRepository webActionDbRepository;

	@Autowired
	ConsignmentRepository consignmentRepository;

	@Autowired
	StokeDetailsRepository stokeDetailsRepository;

	@Override
	public boolean process(WebActionDb webActionDb){
		try {

			webActionDb.setState(WebActionStatus.PROCESSING.getCode());

			webActionDbRepository.save(webActionDb);

			ConsignmentMgmt consignmentMgmt = consignmentRepository.getByTxnId(webActionDb.getTxnId());

			consignmentMgmt.setConsignmentStatus(1);
			consignmentRepository.save(consignmentMgmt);

			List<DeviceDb> devices = new ArrayList<DeviceDb>();

			Path filePath = Paths.get("/home/ubuntu/apache-tomcat-9.0.4/webapps/Design/" + webActionDb.getTxnId() + "/" + consignmentMgmt.getFileName());

			List<String> contents = Files.readAllLines(filePath);

			for(String content : contents) {

				DeviceDb device = util.parseDevice(content);
				device.setImporterTxnId(webActionDb.getTxnId());
				device.setImporterUserId(1L);

				devices.add(device);
			}

			//check for save the data in DB is pending 
			for( DeviceDb device :devices) {

				/*DeviceDb deviceDetails = stokeDetailsRepository.getByImeiEsnMeid(device.getImeiEsnMeid());

				if(deviceDetails == null) {
					stokeDetailsRepository.save(deviceDetails);
				}else {*/

				stokeDetailsRepository.save(device);
				//}
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}
}
