package com.gl.CEIR.FileProcess.ServiceImpl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import javax.transaction.Transactional;

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
	PrototypeBeanProvider<ConsignmentFileParser> fileParser;

	ConcurrentHashMap<String, String> deviceBufferMap;
	ConcurrentHashMap<String, String> errorBufferMap;

	@Override
	@Transactional
	public boolean process(WebActionDb webActionDb) {
		try {

			// Set WebAction state as Processing(1).
			webActionDb.setState(WebActionStatus.PROCESSING.getCode());
			webActionDbRepository.save(webActionDb);

			// Fetch the current Consignment and update it's status as Processing(1).
			ConsignmentMgmt consignmentMgmt = consignmentRepository.getByTxnId(webActionDb.getTxnId());
			consignmentMgmt.setConsignmentStatus(ConsignmentStatus.PROCESSING.getCode());
			consignmentRepository.save(consignmentMgmt);
			
			// TODO Add Consignment to History table.
			
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
				DeviceDb device = fileParser.getBean().parse(content);
				
				if(Objects.isNull(device)) {
					continue;
				}
				
				device.setImporterTxnId(webActionDb.getTxnId());
				device.setImporterUserId(Long.valueOf(consignmentMgmt.getUserId()));

				String value = deviceBufferMap.get(device.getImeiEsnMeid());

				if(Objects.isNull(value)) {
					deviceBufferMap.put(device.getImeiEsnMeid(), device.getImeiEsnMeid());
					devices.add(device);
					
				}else {
					// TODO filename = <filename>_error.csv and Change Format for error file.
					String header = "ErrorCode, Description";	
					String record = "";
					util.writeInFile(errorFilePath, header, record, moveFIlePath);	
				}
				
				// TODO Validation must have on/off capability.
				
				// TODO Tac Validation is a four step process.
				// 1. Check Temp error Tac DB.
				// 2. Check Local TAC DB.
				// 3. Check GSMA
				// 4. Flush Temp eoor tac DB for current file.
			}

			// TODO Update only if it is Complete success.
			// TODO Alter Db Name -> Device_Info_DB.
			stokeDetailsRepository.saveAll(devices);

			// TODO Save in consignment history DB
			consignmentMgmt.setConsignmentStatus(ConsignmentStatus.PENDING_APPROVAL_FROM_CEIR_AUTHORITY.getCode());
			consignmentRepository.save(consignmentMgmt);

			return Boolean.TRUE;

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return Boolean.FALSE;
		}
	}
}
