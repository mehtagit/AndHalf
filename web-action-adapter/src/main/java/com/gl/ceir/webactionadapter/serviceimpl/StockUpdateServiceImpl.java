package com.gl.ceir.webactionadapter.serviceimpl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gl.ceir.webactionadapter.conf.FileStorageProperties;
import com.gl.ceir.webactionadapter.factory.PrototypeBeanProvider;
import com.gl.ceir.webactionadapter.model.constants.Separator;
import com.gl.ceir.webactionadapter.parse.impl.ConsignmentFileParser;
import com.gl.ceir.webactionadapter.service.WebActionService;
import com.gl.ceir.webactionadapter.utility.Util;
import com.gl.ceir.config.model.DeviceDb;
import com.gl.ceir.config.model.StockMgmt;
import com.gl.ceir.config.model.WebActionDb;
import com.gl.ceir.config.model.constants.StockStatus;
import com.gl.ceir.config.model.constants.WebActionStatus;
import com.gl.ceir.config.repository.StockManagementRepository;
import com.gl.ceir.config.repository.StokeDetailsRepository;
import com.gl.ceir.config.repository.WebActionDbRepository;

@Service
public class StockUpdateServiceImpl implements WebActionService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	Util util;

	@Autowired
	WebActionDbRepository webActionDbRepository;

	@Autowired
	StokeDetailsRepository stokeDetailsRepository;

	@Autowired
	StockManagementRepository stockManagementRepository;
	
	@Autowired
	PrototypeBeanProvider<ConsignmentFileParser> fileParser;
	
	@Autowired
	@Qualifier("fileProperties")
	FileStorageProperties fileStorageProperties;

	@Override
	public boolean process(WebActionDb webActionDb){
		try {

			// Set WebAction state as Processing(1).
			webActionDb.setState(WebActionStatus.PROCESSING.getCode());
			webActionDbRepository.save(webActionDb);
			
			StockMgmt stockMgmt = stockManagementRepository.getByTxnId(webActionDb.getTxnId());
			stockMgmt.setStockStatus(StockStatus.PROCESSING.getCode());
			stockManagementRepository.save(stockMgmt);

			StringBuffer filePathBuffer = new StringBuffer().append(fileStorageProperties.getConsignmentsDir())
					.append(webActionDb.getTxnId())
					.append(Separator.SLASH)
					.append(stockMgmt.getFileName());
			
			Path filePath = Paths.get(filePathBuffer.toString());

			List<DeviceDb> devices = new ArrayList<DeviceDb>();
			List<String> contents = Files.readAllLines(filePath);

			for(String content : contents) {
				DeviceDb device = fileParser.getBean().parse(content);
				
				if(Objects.isNull(device)) {
					continue;
				}
				
				device.setImporterTxnId(webActionDb.getTxnId());
				device.setImporterUserId(1L);
				devices.add(device);
			}

			//check for save the data in DB is pending 
			for(DeviceDb device : devices) {

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