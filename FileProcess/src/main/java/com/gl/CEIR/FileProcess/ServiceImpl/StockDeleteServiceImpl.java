package com.gl.CEIR.FileProcess.ServiceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gl.CEIR.FileProcess.Utility.Validation;
import com.gl.CEIR.FileProcess.model.constants.StockStatus;
import com.gl.CEIR.FileProcess.model.constants.WebActionStatus;
import com.gl.CEIR.FileProcess.model.entity.DeviceDb;
import com.gl.CEIR.FileProcess.model.entity.DeviceDbHistory;
import com.gl.CEIR.FileProcess.model.entity.StockMgmt;
import com.gl.CEIR.FileProcess.model.entity.WebActionDb;
import com.gl.CEIR.FileProcess.repository.ConsignmentRepository;
import com.gl.CEIR.FileProcess.repository.StockDetailsOperationRepository;
import com.gl.CEIR.FileProcess.repository.StockManagementRepository;
import com.gl.CEIR.FileProcess.repository.StokeDetailsRepository;
import com.gl.CEIR.FileProcess.repository.WebActionDbRepository;
import com.gl.CEIR.FileProcess.service.WebActionService;

@Service
public class StockDeleteServiceImpl implements WebActionService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	WebActionDbRepository webActionDbRepository;

	@Autowired
	ConsignmentRepository consignmentRepository;

	@Autowired
	StokeDetailsRepository stokeDetailsRepository;

	@Autowired
	Validation validation;

	@Autowired
	StockDetailsOperationRepository stockDetailsOperationRepository;

	@Autowired
	StockManagementRepository stockManagementRepository;
	
	
	@Override
	@Transactional
	public boolean process(WebActionDb webActionDb) {

		try {
			DeviceDbHistory deviceDbHistory = new DeviceDbHistory();

			webActionDb.setState(WebActionStatus.PROCESSING.getCode());

			webActionDbRepository.save(webActionDb);

			StockMgmt stockMgmt = stockManagementRepository.getByTxnId(webActionDb.getTxnId());

			List<DeviceDb> deviceList = stokeDetailsRepository.getByImporterTxnId(webActionDb.getTxnId());

			boolean result = validation.deviceExistValidator(deviceList);

			if(result) {
				// stokeDetailsRepository.deleteByTxnId(webActionDb.getTxnId());
				deviceDbHistory.setFileName(stockMgmt.getFileName());
				deviceDbHistory.setFilePath("");
				deviceDbHistory.setTxnId(webActionDb.getTxnId());

				stockDetailsOperationRepository.save(deviceDbHistory);

			}else {

				for(DeviceDb deivceDetails : deviceList) {

					deviceDbHistory.setDeviceAction(deivceDetails.getDeviceAction());
					deviceDbHistory.setDeviceIdType(deivceDetails.getDeviceIdType());
					deviceDbHistory.setDeviceLaunchDate(deivceDetails.getDeviceLaunchDate());
					deviceDbHistory.setSnOfDevice(deivceDetails.getSnOfDevice());
					deviceDbHistory.setDeviceStatus(deivceDetails.getDeviceStatus());
					deviceDbHistory.setImeiEsnEeid(deivceDetails.getImeiEsnMeid());
					deviceDbHistory.setMultipleSimStatus(deivceDetails.getMultipleSimStatus());
					deviceDbHistory.setOperation(1);
					deviceDbHistory.setRoleType(webActionDb.getFeature());
					deviceDbHistory.setTxnId(webActionDb.getTxnId());
					deviceDbHistory.setUserId(1L);

					stockDetailsOperationRepository.save(deviceDbHistory);
				}
			}
			
			stockMgmt.setStockStatus(StockStatus.STOLEN.getCode());
			stockManagementRepository.save(stockMgmt);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return Boolean.FALSE;		
		}
	
		return Boolean.TRUE;
	}
}
