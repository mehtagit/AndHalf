package com.gl.CEIR.FileProcess.ServiceImpl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gl.CEIR.FileProcess.Utility.Util;
import com.gl.CEIR.FileProcess.conf.FileStorageProperties;
import com.gl.CEIR.FileProcess.factory.PrototypeBeanProvider;
import com.gl.CEIR.FileProcess.model.ErrorRecord;
import com.gl.CEIR.FileProcess.model.constants.ConsignmentStatus;
import com.gl.CEIR.FileProcess.model.constants.Separator;
import com.gl.CEIR.FileProcess.model.entity.ConsignmentMgmt;
import com.gl.CEIR.FileProcess.model.entity.DeviceDb;
import com.gl.CEIR.FileProcess.model.entity.ErrorCodes;
import com.gl.CEIR.FileProcess.model.entity.WebActionDb;
import com.gl.CEIR.FileProcess.parse.impl.ConsignmentFileParser;
import com.gl.CEIR.FileProcess.repository.ConsignmentRepository;
import com.gl.CEIR.FileProcess.repository.ErrorCodesRepository;
import com.gl.CEIR.FileProcess.repository.StokeDetailsRepository;
import com.gl.CEIR.FileProcess.repository.WebActionDbRepository;
import com.gl.CEIR.FileProcess.service.DeviceDbValidator;
import com.gl.CEIR.FileProcess.service.WebActionService;
import com.gl.CEIR.FileProcess.service.dao.ConsignmentDao;
import com.gl.CEIR.FileProcess.service.dao.WebActionDao;

@Service
public class ConsignmentRegisterServiceImpl implements WebActionService {	

	private Logger log = LoggerFactory.getLogger(getClass());

	Map<String, String> deviceBufferMap;
	List<ErrorRecord> errorBuffer;

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
	PrototypeBeanProvider prototypeBeanProvider;

	@Autowired
	DeviceDbManipulatorImpl deviceDbManipulatorImpl;

	@Autowired
	WebActionDao webActionDao;

	@Autowired
	ConsignmentDao consignmentDao;

	@Autowired
	ErrorCodesRepository errorCodesRepository;

	@Autowired
	public ConsignmentRegisterServiceImpl() {
		deviceBufferMap  = new HashMap<String, String>();
		this.errorBuffer = new LinkedList<ErrorRecord>();
	}

	@Override
	@Transactional
	public boolean process(WebActionDb webActionDb) {
		try {

			webActionDao.setProcessing(webActionDb);

			// Fetch the current Consignment and update it's status as Processing(1).
			ConsignmentMgmt consignmentMgmt = consignmentDao.setProcessing(webActionDb);

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

			ConsignmentFileParser consignmentFileParser = prototypeBeanProvider.getConsignmentFileParserBean();
			DeviceDbValidator deviceDbValidator = prototypeBeanProvider.getDeviceDbValidatorBean();

			for(String content : contents) {
				DeviceDb device = consignmentFileParser.parse(content);

				if(Objects.isNull(device)) {
					continue;
				}

				// Setting default values to avoid not null issues while executing queries.
				deviceDbManipulatorImpl.setDefault(device);

				device.setImporterTxnId(webActionDb.getTxnId());
				device.setImporterUserId(Long.valueOf(consignmentMgmt.getUserId()));

				String value = deviceBufferMap.get(device.getImeiEsnMeid());

				if(Objects.isNull(value)) {
					deviceBufferMap.put(device.getImeiEsnMeid(), device.getImeiEsnMeid());

					Object object = deviceDbValidator.validate(device);
					if(object instanceof ErrorCodes) {
						createAndAddErrorCodeInBuffer(object, device.getImeiEsnMeid());
					}else if(object instanceof Boolean) {
						Boolean isValidated = (Boolean) object;
						if(isValidated) {
							devices.add(device);
						}else {

						}
					}else {
						
					}
				}else {
					createAndAddErrorCodeInBuffer(device.getImeiEsnMeid(), "016", "Duplicate IMEI/ESN/MEID");
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

			if(!errorBuffer.isEmpty()) {
				// TODO filename = <filename>_error.csv and Change Format for error file.
				String header = "IMEI/ESN/MEID, ErrorCode, Description";
				util.writeInFile(errorFilePath, header, errorBuffer, moveFIlePath);
			}else {
				log.info("Entities to save : " + devices);
				List<DeviceDb> savedEntities = stokeDetailsRepository.saveAll(devices);
				log.info("Saved Entities : " + savedEntities);
			}

			// TODO Save in consignment history DB
			consignmentMgmt.setConsignmentStatus(ConsignmentStatus.PENDING_APPROVAL_FROM_CEIR_AUTHORITY.getCode());
			consignmentRepository.save(consignmentMgmt);

			return Boolean.TRUE;

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return Boolean.FALSE;
		}
	}

	private boolean createAndAddErrorCodeInBuffer(Object object, String imeiEsnMeid) {
		ErrorCodes errorCodes = (ErrorCodes) object;

		errorCodes = errorCodesRepository.findByErrorCode(errorCodes.getErrorCode());
		if(Objects.isNull(errorCodes)) {
			return errorBuffer.add(new ErrorRecord(imeiEsnMeid, errorCodes.getErrorCode(), ""));	
		}else {
			return errorBuffer.add(new ErrorRecord(imeiEsnMeid, errorCodes.getErrorCode(), errorCodes.getDescription()));
		}

	}

	private boolean createAndAddErrorCodeInBuffer(String imeiEsnMeid, String errorCode, String description) {
		return errorBuffer.add(new ErrorRecord(imeiEsnMeid, errorCode, description));	
	}
}
