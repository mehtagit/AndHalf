package com.gl.CEIR.FileProcess.ServiceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gl.CEIR.FileProcess.Utility.Validation;
import com.gl.CEIR.FileProcess.model.constants.WebActionStatus;
import com.gl.CEIR.FileProcess.model.entity.ConsignmentMgmt;
import com.gl.CEIR.FileProcess.model.entity.DeviceDb;
import com.gl.CEIR.FileProcess.model.entity.DeviceDbHistory;
import com.gl.CEIR.FileProcess.model.entity.WebActionDb;
import com.gl.CEIR.FileProcess.repository.ConsignmentRepository;
import com.gl.CEIR.FileProcess.repository.StockDetailsOperationRepository;
import com.gl.CEIR.FileProcess.repository.StokeDetailsRepository;
import com.gl.CEIR.FileProcess.repository.WebActionDbRepository;
import com.gl.CEIR.FileProcess.service.WebActionService;
import com.gl.ceir.config.model.constants.ConsignmentStatus;

@Service
public class ConsignmentDeleteServiceImpl implements WebActionService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	WebActionDbRepository webActionDbRepository;

	@Autowired
	ConsignmentRepository consignmentRepository;

	@Autowired
	StokeDetailsRepository stokeDetailsRepository;

	@Autowired
	StockDetailsOperationRepository stockDetailsOperationRepository;
	
	@Autowired
	Validation validation;

	@Override
	@Transactional
	public boolean process(WebActionDb webActionDb) {

		try {
			DeviceDbHistory deviceDbHistory = new DeviceDbHistory();

			webActionDb.setState(WebActionStatus.PROCESSING.getCode());

			webActionDbRepository.save(webActionDb);

			ConsignmentMgmt consignmentMgmt = consignmentRepository.getByTxnId(webActionDb.getTxnId());

			List<DeviceDb> deviceList = stokeDetailsRepository.getByImporterTxnId(webActionDb.getTxnId());

			boolean result = validation.deviceExistValidator(deviceList);

			if(result) {
				// stokeDetailsRepository.deleteByTxnId(webActionDb.getTxnId());
				deviceDbHistory.setFileName(consignmentMgmt.getFileName());
				deviceDbHistory.setFilePath("");
				deviceDbHistory.setTxnId(webActionDb.getTxnId());

				stockDetailsOperationRepository.save(deviceDbHistory);

			}else {

				for(DeviceDb deviceDetails : deviceList) {

					deviceDbHistory.setDeviceAction(deviceDetails.getDeviceAction());
					deviceDbHistory.setDeviceIdType(deviceDetails.getDeviceIdType());
					deviceDbHistory.setDeviceLaunchDate(deviceDetails.getDeviceLaunchDate());
					deviceDbHistory.setSnOfDevice(deviceDetails.getSnOfDevice());
					deviceDbHistory.setDeviceStatus(deviceDetails.getDeviceStatus());
					deviceDbHistory.setImeiEsnEeid(deviceDetails.getImeiEsnMeid());
					deviceDbHistory.setMultipleSimStatus(deviceDetails.getMultipleSimStatus());
					deviceDbHistory.setOperation(1);
					deviceDbHistory.setRoleType(webActionDb.getFeature());
					deviceDbHistory.setTxnId(webActionDb.getTxnId());
					deviceDbHistory.setUserId(1L);

					stockDetailsOperationRepository.save(deviceDbHistory);
				}
			}
			
			consignmentMgmt.setConsignmentStatus(ConsignmentStatus.STOLEN.getCode());
			consignmentRepository.save(consignmentMgmt);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return Boolean.FALSE;		
		}
	
		return Boolean.TRUE;
	}
}