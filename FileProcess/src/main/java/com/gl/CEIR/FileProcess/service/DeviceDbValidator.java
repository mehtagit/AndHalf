package com.gl.CEIR.FileProcess.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gl.CEIR.FileProcess.exception.NullArgumentsNotAllowedException;
import com.gl.CEIR.FileProcess.model.entity.DeviceDb;
import com.gl.CEIR.FileProcess.model.entity.ErrorCodes;
import com.gl.CEIR.FileProcess.validate.Validator;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DeviceDbValidator {

	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	Validator validatorImpl;

	public Object validate(DeviceDb deviceDb) {
		try {
			
			// DEVICE_TYPE
			if(!validatorImpl.caseInsensitiveContains(Validator.deviceTypes, deviceDb.getDeviceType())) {
				return new ErrorCodes("001");
			}			

			// DEVICE_ID_TYPE
			if(!validatorImpl.caseInsensitiveContains(Validator.deviceIdTypes, deviceDb.getDeviceIdType())) {
				return new ErrorCodes("002");
			}
			
			// IMEI/ESN/MEID
			if(!validatorImpl.isEmpty(deviceDb.getImeiEsnMeid())) {
				return new ErrorCodes("003");
			}
			
			if("IMEI".equalsIgnoreCase(deviceDb.getImeiEsnMeid())) {

				// Length Check
				if(validatorImpl.matchLength(deviceDb.getImeiEsnMeid(), 15)) {

				}else if(!validatorImpl.matchLength(deviceDb.getImeiEsnMeid(), 16)){
					return new ErrorCodes("004");
				}
				
				// Only numeric is allowed.
				if(!validatorImpl.isNumeric(deviceDb.getImeiEsnMeid())) {
					return new ErrorCodes("005");
				}


			}else if("ESN".equalsIgnoreCase(deviceDb.getImeiEsnMeid())){
				if(!validatorImpl.matchLength(deviceDb.getImeiEsnMeid(), 8)) {
					return new ErrorCodes("006");
				}
				
				// Only numeric is allowed.
				if(!validatorImpl.isAlphaNumeric(deviceDb.getImeiEsnMeid())) {
					return new ErrorCodes("007");
				}
			}else if("MEID".equalsIgnoreCase(deviceDb.getImeiEsnMeid())){
				if(!validatorImpl.matchLength(deviceDb.getImeiEsnMeid(), 14)) {
					return new ErrorCodes("008");
				}
				
				// Only numeric is allowed.
				if(!validatorImpl.isAlphaNumeric(deviceDb.getImeiEsnMeid())) {
					return new ErrorCodes("009");
				}
			}

			// MULTIPLE SIM STATUS
			if(!validatorImpl.caseInsensitiveContains(Validator.yesNos, deviceDb.getMultipleSimStatus())) {
				return new ErrorCodes("010");
			}

			// Multi Sim status is empty -> 011
			
			// DEVICE SERIAL NO
			if(!validatorImpl.lengthRange(deviceDb.getSnOfDevice(), 5, 15)) {
				return new ErrorCodes("012");
			}
			
			if(!validatorImpl.isAlphaNumeric(deviceDb.getSnOfDevice())) {
				return new ErrorCodes("013");
			}
			
			// DEVICE STATUS
			if(!validatorImpl.caseInsensitiveContains(Validator.deviceStatus, deviceDb.getDeviceStatus())) {
				return new ErrorCodes("014");
			}
			
			// LAUNCH DATE
			if(!validatorImpl.isEmpty(deviceDb.getDeviceLaunchDate().toString())) {
				return new ErrorCodes("015");
			}
			
		} catch (NullArgumentsNotAllowedException e) {
			log.error(e.getMessage(), e);
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}
	
}
