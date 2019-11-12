package com.gl.CEIR.FileProcess.Utility;

import java.io.File;
import java.io.FileWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.gl.CEIR.FileProcess.model.DeviceDb;



@Component
public class Util {

	private Logger log = LoggerFactory.getLogger(getClass());

	public	void writeInFile(String fileName, String header, String record,String nameFIleLocation) {
		try {

			File tmpDir = new File(fileName);
			boolean exists = tmpDir.exists();

			if(true == exists) {

				tmpDir.renameTo(new File(nameFIleLocation));

				tmpDir.delete();
			}

			FileWriter fw = new FileWriter(fileName, true);

			if (!exists)
				fw.append(header);
			fw.append("\n");
			fw.append(record);
			fw.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	public DeviceDb parseDevice(String content) {

		String data[] = content.split(",");
		log.info("Content size ="+data.length);

		DeviceDb device = new DeviceDb();
		try {
			device.setDeviceType(data[0]);
			device.setDeviceType(data[1]);
			device.setMultipleSimStatus(data[2]);
			device.setDeviceNumber(data[3]);
			device.setImeiEsnMeid(data[4]);
			device.setDeviceLaunchDate(data[5]);
			device.setDeviceStatus(data[6]);
		} catch (Exception e) {
			log.info("Exception in parse the data");
			e.printStackTrace();
		}
		return device;
	}







}
