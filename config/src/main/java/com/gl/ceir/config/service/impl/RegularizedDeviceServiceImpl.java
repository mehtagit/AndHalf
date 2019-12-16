package com.gl.ceir.config.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gl.ceir.config.exceptions.ResourceServicesException;
import com.gl.ceir.config.model.CustomRegistrationDB;
import com.gl.ceir.config.model.DeviceDb;
import com.gl.ceir.config.model.GenricResponse;
import com.gl.ceir.config.model.UserCustomDb;
import com.gl.ceir.config.model.UserCustomHistoryDb;
import com.gl.ceir.config.model.constants.EndUserStatus;
import com.gl.ceir.config.repository.ConsignmentRepository;
import com.gl.ceir.config.repository.CustomDetailsRepository;
import com.gl.ceir.config.repository.CustomRegisterationDbRepository;
import com.gl.ceir.config.repository.StokeDetailsRepository;
import com.gl.ceir.config.repository.UserCustomDbRepository;
import com.gl.ceir.config.repository.UserCustomHistoryDbRepository;

@Service
public class CustomServiceImpl {


	private static final Logger logger = LogManager.getLogger(CustomServiceImpl.class);

	@Autowired
	ConsignmentRepository consignmentRepository;

	@Autowired
	StokeDetailsRepository stokeDetailsRepository;

	@Autowired
	CustomDetailsRepository customDetailsRepository;

	@Autowired
	CustomRegisterationDbRepository customRegisterationDbRepository;

	@Autowired
	UserCustomDbRepository userCustomDbRepository;

	@Autowired
	UserCustomHistoryDbRepository userCustomHistoryDbRepository;

	public List<UserCustomDb> getCustomDetails(UserCustomDb userCustomDb){
		try {
			List<UserCustomDb>	userCustomDetails = userCustomDbRepository.getByNid(userCustomDb.getNid());

			List<DeviceDb> deviceDb = stokeDetailsRepository.getByEndUserUserId(userCustomDb.getNid());
			
			if(deviceDb != null) {
				UserCustomDb deviceDbFetchDetails =new UserCustomDb();

				for(int i= 0; i<deviceDb.size(); i++) {

					if(i == 0) {
						deviceDbFetchDetails.setCountry(deviceDb.get(i).getEndUserCountry());
						// deviceDbFetchDetails.setDeviceType(deviceDb.get(i).getDeviceType());
						deviceDbFetchDetails.setMultiSimStatus(deviceDb.get(i).getMultipleSimStatus());
						deviceDbFetchDetails.setNid(deviceDb.get(i).getEndUserUserId());
						deviceDbFetchDetails.setTaxPaidStatus(deviceDb.get(i).getEndUserDeviceStatus());
						deviceDbFetchDetails.setTxnId(deviceDb.get(i).getEndUserTxnId());
						deviceDbFetchDetails.setFirstImei(Long.parseLong(deviceDb.get(i).getImeiEsnMeid()));
					}
					if(i == 1) {
						deviceDbFetchDetails.setSecondImei(Long.parseLong(deviceDb.get(i).getImeiEsnMeid()));
					}
					if(i == 2) {
						deviceDbFetchDetails.setThirdImei(Long.parseLong(deviceDb.get(i).getImeiEsnMeid()));
					}
					if(i == 3) {
						deviceDbFetchDetails.setFourthImei(Long.parseLong(deviceDb.get(i).getImeiEsnMeid()));
					}

					if(i %4 == 0) {
						userCustomDetails.add(deviceDbFetchDetails);
					}
				}
			}

			return userCustomDetails;

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException("Custom Service", e.getMessage());
		}
	}


	public GenricResponse updateTaxPaidStatus( UserCustomDb userCustomDb) {
		try {

			UserCustomDb userCustomDbDetails = userCustomDbRepository.getByDeviceSerialNumber(userCustomDb.getDeviceSerialNumber());

			if(userCustomDbDetails != null) {
				userCustomDbDetails.setTaxPaidStatus(EndUserStatus.PAID.getCode());
				userCustomDbRepository.save(userCustomDbDetails);
				return new GenricResponse(0, "Update Successfully.",userCustomDbDetails.getDeviceSerialNumber());

			}else {

				return  new GenricResponse(4,"TxnId Does Not exist.", userCustomDbDetails.getDeviceSerialNumber());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException("Custom Service", e.getMessage());}
	}


	@Transactional
	public GenricResponse saveRegisterInfo(CustomRegistrationDB customRegistrationDB) {
		try {

			CustomRegistrationDB customRegistration = customRegisterationDbRepository.save(customRegistrationDB);

			return new GenricResponse(0, "User register sucessfully", customRegistrationDB.getUserCustomDb().get(0).getTxnId());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException("Custom Service", e.getMessage());
		}
	}


	public UserCustomDb viewStatus(UserCustomDb UserCustomDb) {
		try {

			UserCustomDb  userDetails = userCustomDbRepository.getByDeviceSerialNumber(UserCustomDb.getDeviceSerialNumber());
			if(userDetails != null) {
				return userDetails;
			}else {

				UserCustomDb deviceDbFetchDetails = new  UserCustomDb();
				List<DeviceDb> deviceDb = stokeDetailsRepository.getByDeviceNumber(UserCustomDb.getDeviceSerialNumber());

				for(int i=0;i < deviceDb.size();i++) {


					if(i == 0) {
						deviceDbFetchDetails.setFirstImei(Long.parseLong(deviceDb.get(i).getImeiEsnMeid()));
						deviceDbFetchDetails.setMultiSimStatus(deviceDb.get(i).getMultipleSimStatus());
						deviceDbFetchDetails.setNid(deviceDb.get(i).getEndUserUserId());
						deviceDbFetchDetails.setTaxPaidStatus(deviceDb.get(i).getEndUserDeviceStatus());
						deviceDbFetchDetails.setTxnId(deviceDb.get(i).getEndUserTxnId());
						deviceDbFetchDetails.setCountry(deviceDb.get(i).getEndUserCountry());
						// deviceDbFetchDetails.setDeviceType(deviceDb.get(i).getDeviceType());
					}
					if(i ==1) {
						deviceDbFetchDetails.setSecondImei(Long.parseLong(deviceDb.get(i).getImeiEsnMeid()));
					}
					if(i ==2) {
						deviceDbFetchDetails.setThirdImei(Long.parseLong(deviceDb.get(i).getImeiEsnMeid()));
					}
					if(i ==3) {
						deviceDbFetchDetails.setFourthImei(Long.parseLong(deviceDb.get(i).getImeiEsnMeid()));
					}

				}

				return deviceDbFetchDetails;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException("Custom Service", e.getMessage());
		}

	}


	public GenricResponse deleteCustomInfo(UserCustomDb userCustomDb) {
		try {
			UserCustomDb customDb = userCustomDbRepository.getByDeviceSerialNumber(userCustomDb.getDeviceSerialNumber());
			if(customDb  != null) {
				userCustomDbRepository.deleteById(customDb.getId());

				UserCustomHistoryDb userCustomHistoryDb = new UserCustomHistoryDb();
				userCustomHistoryDb.setCountry(customDb.getCountry());
				userCustomHistoryDb.setDeviceSerialNumber(customDb.getDeviceSerialNumber());
				// userCustomHistoryDb.setDeviceType(customDb.getDeviceType());
				userCustomHistoryDb.setFirstImei(customDb.getFirstImei());
				userCustomHistoryDb.setFourthImei(customDb.getFourthImei());
				userCustomHistoryDb.setMultiSimStatus(customDb.getMultiSimStatus());
				userCustomHistoryDb.setNid(customDb.getNid());
				userCustomHistoryDb.setSecondImei(customDb.getSecondImei());
				// userCustomHistoryDb.setStatus(customDb.getStatus());
				userCustomHistoryDb.setTaxPaidStatus(customDb.getTaxPaidStatus());
				userCustomHistoryDb.setThirdImei(customDb.getThirdImei());
				userCustomHistoryDb.setTxnId(customDb.getTxnId());

				userCustomHistoryDbRepository.save(userCustomHistoryDb);

				return new GenricResponse(0, "Txn delete sucessfully", userCustomDb.getDeviceSerialNumber());
			}else {

				return new GenricResponse(4, "This txn not exist", userCustomDb.getDeviceSerialNumber());	
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResourceServicesException("Custom Service", e.getMessage());
		}
	}
	
}
