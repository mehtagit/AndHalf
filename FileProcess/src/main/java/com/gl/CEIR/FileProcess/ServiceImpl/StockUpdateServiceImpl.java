package com.gl.CEIR.FileProcess.ServiceImpl;

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

import com.gl.CEIR.FileProcess.Utility.Util;
import com.gl.CEIR.FileProcess.conf.FileStorageProperties;
import com.gl.CEIR.FileProcess.factory.PrototypeBeanProvider;
import com.gl.CEIR.FileProcess.model.constants.Separator;
import com.gl.CEIR.FileProcess.model.constants.StockStatus;
import com.gl.CEIR.FileProcess.model.constants.WebActionStatus;
import com.gl.CEIR.FileProcess.model.entity.DeviceDb;
import com.gl.CEIR.FileProcess.model.entity.StockMgmt;
import com.gl.CEIR.FileProcess.model.entity.WebActionDb;
import com.gl.CEIR.FileProcess.repository.StockManagementRepository;
import com.gl.CEIR.FileProcess.repository.StokeDetailsRepository;
import com.gl.CEIR.FileProcess.repository.WebActionDbRepository;
import com.gl.CEIR.FileProcess.service.WebActionService;

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
	PrototypeBeanProvider fileParser;
	
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

			StringBuffer filePathBuffer = new StringBuffer().append(fileStorageProperties.getStockDir())
					.append(webActionDb.getTxnId())
					.append(Separator.SLASH)
					.append(stockMgmt.getFileName());
			
			Path filePath = Paths.get(filePathBuffer.toString());

			List<DeviceDb> devices = new ArrayList<DeviceDb>();
			List<String> contents = Files.readAllLines(filePath);

			for(String content : contents) {
				DeviceDb device = fileParser.getConsignmentFileParserBean().parse(content);
				
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
