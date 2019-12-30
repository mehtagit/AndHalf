package com.gl.CEIR.FileProcess.Utility;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gl.CEIR.FileProcess.model.entity.DeviceDb;

@Component
public class Validation {

	@Autowired
	Util util;

	public String headerValidator(String filePath, String devicetype,String deviceIDtype,String multipleSIMStatus,String sNofDevice,String iMEIESNMEID,String devicelaunchdate, String deviceStatus,String moveFIlePath ) {
		try {

			if(!"devicetype".equalsIgnoreCase(devicetype) || !"deviceIDtype".equalsIgnoreCase(deviceIDtype) || !"deviceIDtype".equalsIgnoreCase(deviceIDtype) ||
					!"multipleSIMStatus".equalsIgnoreCase(multipleSIMStatus) || !"S/NofDevice".equalsIgnoreCase(sNofDevice) || "IMEI/ESN/MEID".equalsIgnoreCase(iMEIESNMEID) ||
					"Devicelaunchdate".equalsIgnoreCase(devicelaunchdate) || !"DeviceStatus".equalsIgnoreCase(deviceStatus)) {

				return "FAIL";
			}
			return "success";

		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	public String alfanumricValidator(String filePath,String deviceIDtype ,String iMEIESNMEID,String moveFIlePath) {
		try {

			if("IMEI".equalsIgnoreCase(deviceIDtype)) {
				boolean  result = alfaNumricChecck(iMEIESNMEID);
				if(result) {
					return "fail";
				}
			}

			return "sucess";

		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	public static boolean alfaNumricChecck(String value) {

		boolean b1 = value.matches(".*[a-zA-Z].*");

		return b1;
	}

	public String lengthValidator(String filePath,String deviceIDtype ,String iMEIESNMEID,String moveFIlePath) {
		try {

			if("IMEI".equalsIgnoreCase(deviceIDtype)){
				if(iMEIESNMEID.length() >16 || iMEIESNMEID.length() <14) {
					return "fail";
				}
			}else if("ESN".equalsIgnoreCase(deviceIDtype)) {

				if(8 != iMEIESNMEID.length()) {

					return "fail";
				}				
			}else {
				if(14 != iMEIESNMEID.length() ) {

					return "fail";
				}
			}

			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	public String greyListCheck(String device) {

		//greydb fetch
		String result ="";
		if(result == null) {
			return "fail";
		}
		return "success";		
	}


	public String deviceDbCheck(String deviceId) {

		//deviceDb check
		String result=" ";

		if(result == null) {
			return "fail";
		}
		return "success";
	}

	public String activeDbCheck(String device) {
		//activeDb check
		String result =" ";
		if(result == null) {

			return "fail";
		}
		return "success";

	}

	public boolean deviceExistValidator(List<DeviceDb> deviceDb) {

		for(DeviceDb deivceDetails : deviceDb ) {

			if(deivceDetails.getRetalierTxnId() != null || deivceDetails.getDistributerTxnId() != null) {

				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;		

	}
}
