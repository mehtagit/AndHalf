package com.gl.CEIR.FileProcess.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.CEIR.FileProcess.Repository.ConsignmentRepository;
import com.gl.CEIR.FileProcess.Repository.StockDetailsOperationRepository;
import com.gl.CEIR.FileProcess.Repository.StokeDetailsRepository;
import com.gl.CEIR.FileProcess.Repository.WebActionDbRepository;
import com.gl.CEIR.FileProcess.Utility.Validation;
import com.gl.CEIR.FileProcess.model.ConsignmentMgmt;
import com.gl.CEIR.FileProcess.model.DeviceDb;
import com.gl.CEIR.FileProcess.model.DeviceDbHistory;
import com.gl.CEIR.FileProcess.model.WebActionDb;


@Service
public class ConsignmentDeleteServiceImpl {


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

	public boolean deleteProcess(WebActionDb webActionDb) {
		try {

			
			DeviceDbHistory deviceDbHistory = new DeviceDbHistory();

			webActionDb.setState(1);

			webActionDbRepository.save(webActionDb);

			ConsignmentMgmt consignmentMgmt = consignmentRepository.getByTxnId(webActionDb.getTxnId());


			List<DeviceDb> deviceList =stokeDetailsRepository.getByImporterTxnId(webActionDb.getTxnId());

			boolean result = validation.deivceExistValidator(deviceList);

			if(result == false) {

				stokeDetailsRepository.deleteByTxnId(webActionDb.getTxnId());

				deviceDbHistory.setFileName(consignmentMgmt.getFileName());
				deviceDbHistory.setFilePath("");
				deviceDbHistory.setTxnId(webActionDb.getTxnId());

				stockDetailsOperationRepository.save(deviceDbHistory);

			}else {

				for(DeviceDb deivceDetails : deviceList) {

					deviceDbHistory.setDeviceAction(deivceDetails.getDeviceAction());
					deviceDbHistory.setDeviceId(deivceDetails.getDeviceId());
					deviceDbHistory.setDeviceLaunchDate(deivceDetails.getDeviceLaunchDate());
					deviceDbHistory.setDeviceNumber(deivceDetails.getDeviceNumber());
					deviceDbHistory.setDeviceStatus(deivceDetails.getDeviceStatus());
					deviceDbHistory.setImeiEsnEeid(deivceDetails.getImeiEsnMeid());
					deviceDbHistory.setMultipleSimStatus(deivceDetails.getMultipleSimStatus());
					deviceDbHistory.setOperation(1);
					deviceDbHistory.setRoleType(webActionDb.getFeature());
					deviceDbHistory.setTxnId(webActionDb.getTxnId());
					deviceDbHistory.setUserId(consignmentMgmt.getUserId());

					stockDetailsOperationRepository.save(deviceDbHistory);
				}

			}
			consignmentMgmt.setConsignmentStatus(10);

			consignmentRepository.save(consignmentMgmt);


		} catch (Exception e) {
			e.printStackTrace();
			return false;		

		}
		return true;


	}


}
