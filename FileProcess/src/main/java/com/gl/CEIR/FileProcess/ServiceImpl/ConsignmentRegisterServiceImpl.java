package com.gl.CEIR.FileProcess.ServiceImpl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gl.CEIR.FileProcess.Utility.Util;
import com.gl.CEIR.FileProcess.conf.FileStorageProperties;
import com.gl.CEIR.FileProcess.factory.PrototypeBeanProvider;
import com.gl.CEIR.FileProcess.model.constants.Separator;
import com.gl.CEIR.FileProcess.parse.impl.ConsignmentFileParser;
import com.gl.CEIR.FileProcess.service.WebActionService;
import com.gl.ceir.config.model.ConsignmentMgmt;
import com.gl.ceir.config.model.DeviceDb;
import com.gl.ceir.config.model.WebActionDb;
import com.gl.ceir.config.model.constants.ConsignmentStatus;
import com.gl.ceir.config.model.constants.WebActionStatus;
import com.gl.ceir.config.repository.ConsignmentRepository;
import com.gl.ceir.config.repository.StokeDetailsRepository;
import com.gl.ceir.config.repository.WebActionDbRepository;

@Service
public class ConsignmentRegisterServiceImpl implements WebActionService {	

	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	Util util;
	
	@Autowired
	@Qualifier("fileProperties")
	FileStorageProperties fileStorageProperties;

	@Autowired
	WebActionDbRepository webActionDbRepository;

	@Autowired
	ConsignmentRepository consignmentRepository;

	@Autowired
	StokeDetailsRepository stokeDetailsRepository;
	
	@Autowired
	PrototypeBeanProvider<ConsignmentFileParser> consignmentFileParser;

	ConcurrentHashMap<String, String> deviceBufferMap;
	ConcurrentHashMap<String, String> errorBufferMap;

	@Override
	public boolean process(WebActionDb webActionDb) {
		try {

			// Set WebAction state as Processing(1).
			webActionDb.setState(WebActionStatus.PROCESSING.getCode());
			webActionDbRepository.save(webActionDb);

			// Fetch the current Consignment and update it's status as Processing(1).
			ConsignmentMgmt consignmentMgmt = consignmentRepository.getByTxnId(webActionDb.getTxnId());
			consignmentMgmt.setConsignmentStatus(ConsignmentStatus.PROCESSING.getCode());
			consignmentRepository.save(consignmentMgmt);
			
			log.info("File Status is update as processing ");
			StringBuffer filePathBuffer = new StringBuffer().append(fileStorageProperties.getConsignmentsDir())
					.append(webActionDb.getTxnId())
					.append(Separator.SLASH)
					.append(consignmentMgmt.getFileName()); 
			
			Path filePath = Paths.get(filePathBuffer.toString());

			String errorFilePath = "";
			String moveFIlePath  = "";

			List<DeviceDb> devices = new LinkedList<>();
			List<String> contents = Files.readAllLines(filePath);

			log.info("File reading starts = " + contents);
			
			deviceBufferMap = new ConcurrentHashMap<String, String>();

			for(String content : contents) {
				DeviceDb device = consignmentFileParser.getBean().parse(content);
				
				device.setImporterTxnId(webActionDb.getTxnId());
				device.setImporterUserId(Long.valueOf(consignmentMgmt.getUserId()));

				String value = deviceBufferMap.get(device.getImeiEsnMeid());

				if(Objects.isNull(value)) {
					deviceBufferMap.put(device.getImeiEsnMeid(), device.getImeiEsnMeid());
					devices.add(device);
					
				}else {
					String header = "ErrorCode, Description";	
					String record = "";
					util.writeInFile(errorFilePath, header, record, moveFIlePath);	
				}
			}

			stokeDetailsRepository.saveAll(devices);

			consignmentMgmt.setConsignmentStatus(ConsignmentStatus.PENDING_APPROVAL_FROM_CEIR_AUTHORITY.getCode());
			consignmentRepository.save(consignmentMgmt);

			return Boolean.TRUE;

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return Boolean.FALSE;
		}
	}
}
