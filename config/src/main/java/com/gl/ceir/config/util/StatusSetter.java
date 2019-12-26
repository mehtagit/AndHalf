package com.gl.ceir.config.util;

import java.util.List;

import org.springframework.stereotype.Component;

import com.gl.ceir.config.model.RegularizeDeviceDb;

@Component
public final class StatusSetter {

	public List<RegularizeDeviceDb> setStatus(List<RegularizeDeviceDb> target, Integer state) {
		try {
			
			target.forEach(o -> o.setState(state));
			return target;
			
		}catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
}