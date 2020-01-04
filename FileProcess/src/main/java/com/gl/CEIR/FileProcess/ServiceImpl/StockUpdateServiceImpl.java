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
import com.gl.CEIR.FileProcess.model.entity.ErrorCodes;
import com.gl.CEIR.FileProcess.model.entity.StockMgmt;
import com.gl.CEIR.FileProcess.model.entity.WebActionDb;
import com.gl.CEIR.FileProcess.parse.impl.ConsignmentFileParser;
import com.gl.CEIR.FileProcess.repository.DeviceDbRepository;
import com.gl.CEIR.FileProcess.repository.StockManagementRepository;
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
	DeviceDbRepository deviceDbRepository;

	@Autowired
	StockManagementRepository stockManagementRepository;

	@Autowired
	PrototypeBeanProvider fileParser;

	@Autowired
	@Qualifier("fileProperties")
	FileStorageProperties fileStorageProperties;
	
	@Autowired
	PrototypeBeanProvider prototypeBeanProvider;

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

			ConsignmentFileParser consignmentFileParser = prototypeBeanProvider.getConsignmentFileParserBean();
			
			for(String content : contents) {
				DeviceDb device = null;
				Object parsedData = consignmentFileParser.parse(content);

				if(Objects.isNull(parsedData)) {
					continue;
				}
				
				if(parsedData instanceof DeviceDb) {
					device =  (DeviceDb) parsedData;
				}else {
					// TODO Discuss
					ErrorCodes errorCodes = (ErrorCodes) parsedData;
					// errorBuffer.add
					continue;
				}

				device.setImporterTxnId(webActionDb.getTxnId());
				device.setImporterUserId(1L);
				devices.add(device);
			}

			deviceDbRepository.saveAll(devices);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return Boolean.FALSE;
		}

		return Boolean.TRUE;
	}
}
