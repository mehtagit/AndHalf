package com.gl.CEIR.FileProcess.Utility;

import java.io.File;
import java.io.FileWriter;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.gl.CEIR.FileProcess.model.constants.Separator;
import com.gl.ceir.config.model.DeviceDb;

@Component
public class Util {

	private Logger log = LoggerFactory.getLogger(getClass());

	File tmpDir;

	public void writeInFile(String fileName, String header, String record, String nameFileLocation) {

		try {
			tmpDir = new File(fileName);
			FileWriter fw = new FileWriter(fileName, Boolean.TRUE);

			if(tmpDir.exists()) {
				tmpDir.renameTo(new File(nameFileLocation));
				tmpDir.delete();
			}else {
				fw.append(header);
			}

			fw.append(Separator.NEW_LINE);
			fw.append(record);
			fw.close();

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	public DeviceDb parseDevice(String content) {

		String data[] = content.split(Separator.COMMA);
		log.info("Content size = " + data.length);

		DeviceDb device = new DeviceDb();
		try {
			device.setDeviceType(data[0]);
			device.setDeviceIdType(data[1]);
			device.setMultipleSimStatus(data[2]);
			device.setSnOfDevice(data[3]);
			device.setImeiEsnMeid(data[4]);
			// TODO Read date form file.
			device.setDeviceLaunchDate(new Date());
			device.setDeviceStatus(data[6]);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		return device;
	}
}
