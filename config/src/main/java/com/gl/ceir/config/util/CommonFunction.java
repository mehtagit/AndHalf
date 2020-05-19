package com.gl.ceir.config.util;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gl.ceir.config.controller.RegularizedDeviceController;
import com.gl.ceir.config.model.RegularizeDeviceDb;
import com.gl.ceir.config.repository.RegularizedDeviceDbRepository;

@Component
public class CommonFunction {
	
	private static final Logger logger = LogManager.getLogger(RegularizedDeviceController.class);
	@Autowired
	RegularizedDeviceDbRepository regularizedDeviceDbRepository;
	
	
	public Boolean checkAllImeiOfRegularizedDevice(RegularizeDeviceDb regularizeDeviceDb) {
		if(Objects.nonNull(regularizeDeviceDb.getFirstImei()) && Objects.isNull(regularizedDeviceDbRepository.getByImei(regularizeDeviceDb.getFirstImei()))) {
			return Boolean.FALSE;
		}
		if(Objects.nonNull(regularizeDeviceDb.getSecondImei()) && Objects.isNull(regularizedDeviceDbRepository.getByImei(regularizeDeviceDb.getSecondImei()))) {
			return Boolean.FALSE;
		}
		if(Objects.nonNull(regularizeDeviceDb.getThirdImei()) && Objects.isNull(regularizedDeviceDbRepository.getByImei(regularizeDeviceDb.getThirdImei()))) {
			return Boolean.FALSE;
		}
		if(Objects.nonNull(regularizeDeviceDb.getFourthImei()) && Objects.isNull(regularizedDeviceDbRepository.getByImei(regularizeDeviceDb.getFourthImei()))) {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}
	
	public Boolean hasDuplicateImeiInRequest(List<RegularizeDeviceDb> regularizeDeviceDbs) {
		logger.info("regularized device list check for duplicate imei"+regularizeDeviceDbs);
		HashSet<String> set = new HashSet<>();
		
		for(RegularizeDeviceDb device : regularizeDeviceDbs) {
			if(device.getFirstImei()!=null && !device.getFirstImei().isEmpty()) {
				if(!set.add(device.getFirstImei())) {
					return Boolean.TRUE;
				}
			}
			if(device.getSecondImei()!=null && !device.getSecondImei().isEmpty()) {
				if(!set.add(device.getSecondImei())) {
					return Boolean.TRUE;
				}
			}
			if(device.getThirdImei()!=null && !device.getThirdImei().isEmpty()) {
				if(!set.add(device.getThirdImei())) {
					return Boolean.TRUE;
				}
			}
			if(device.getFourthImei()!=null && !device.getFourthImei().isEmpty()) {
				if(!set.add(device.getFourthImei())) {
					return Boolean.TRUE;
				}
			}
		}
		return Boolean.FALSE;
	}
	
	public int getFeatureIdByTxnId(String txnId) {
		if(Objects.isNull(txnId)) {
			return 0;
		}else {
			if(txnId.startsWith("C")) {
				return 3;
			}else if(txnId.startsWith("S")){
				return 4;
			}
			else {
				return 0;
			}
		}
	}
}