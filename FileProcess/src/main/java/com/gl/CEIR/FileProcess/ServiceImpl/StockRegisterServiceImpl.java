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
import com.gl.CEIR.FileProcess.model.constants.StockStatus;
import com.gl.CEIR.FileProcess.model.constants.WebActionStatus;
import com.gl.CEIR.FileProcess.model.entity.DeviceDb;
import com.gl.CEIR.FileProcess.model.entity.StockMgmt;
import com.gl.CEIR.FileProcess.model.entity.WebActionDb;
import com.gl.CEIR.FileProcess.repository.DeviceDbRepository;
import com.gl.CEIR.FileProcess.repository.StockManagementRepository;
import com.gl.CEIR.FileProcess.repository.WebActionDbRepository;
import com.gl.CEIR.FileProcess.service.WebActionService;

@Service
public class StockRegisterServiceImpl implements WebActionService{

	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	Util util;

	@Autowired
	@Qualifier("fileProperties")
	FileStorageProperties fileStorageProperties;

	@Autowired
	WebActionDbRepository webActionDbRepository;

	@Autowired
	StockManagementRepository stockManagementRepository;

	@Autowired
	PrototypeBeanProvider fileParser;

	@Autowired
	DeviceDbRepository deviceDbRepository;

	ConcurrentHashMap<String, String> deviceBufferMap;
	ConcurrentHashMap<String, String> errorBufferMap;

	@Override
	public boolean process(WebActionDb webActionDb) {
		try {

			// Set WebAction state as Processing(1).
			webActionDb.setState(WebActionStatus.PROCESSING.getCode());
			webActionDbRepository.save(webActionDb);

			// Fetch the current Consignment and update it's status as Processing(1).
			StockMgmt stockMgmt = stockManagementRepository.getByTxnId(webActionDb.getTxnId());
			stockMgmt.setStockStatus(StockStatus.PROCESSING.getCode());
			stockManagementRepository.save(stockMgmt);

			log.info("File Status is update as processing ");
			StringBuffer filePathBuffer = new StringBuffer().append(fileStorageProperties.getStockDir())
					.append(webActionDb.getTxnId())
					.append(Separator.SLASH)
					.append(stockMgmt.getFileName()); 

			Path filePath = Paths.get(filePathBuffer.toString());

			String errorFilePath = "";
			String moveFIlePath  = "";

			List<DeviceDb> devices = new LinkedList<>();
			List<String> contents = Files.readAllLines(filePath);

			log.info("File reading starts = " + contents);

			deviceBufferMap = new ConcurrentHashMap<String, String>();

			for(String content : contents) {
				DeviceDb device = fileParser.getConsignmentFileParserBean().parse(content);

				if(Objects.isNull(device)) {
					continue;
				}

				device.setImporterTxnId(webActionDb.getTxnId());
				device.setImporterUserId(Long.valueOf(stockMgmt.getUserId()));

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

			deviceDbRepository.saveAll(devices);

			stockMgmt.setStockStatus(StockStatus.SUCCESS.getCode());
			stockManagementRepository.save(stockMgmt);

			return Boolean.TRUE;

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return Boolean.FALSE;
		}
	}
}
