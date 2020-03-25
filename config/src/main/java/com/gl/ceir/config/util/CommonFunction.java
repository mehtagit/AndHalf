package com.gl.ceir.config.util;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gl.ceir.config.model.RegularizeDeviceDb;
import com.gl.ceir.config.repository.RegularizedDeviceDbRepository;

@Component
public class CommonFunction {
	
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

}
