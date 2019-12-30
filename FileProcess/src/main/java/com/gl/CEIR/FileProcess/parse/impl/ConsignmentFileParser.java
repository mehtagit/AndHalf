package com.gl.CEIR.FileProcess.parse.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gl.CEIR.FileProcess.model.constants.Separator;
import com.gl.CEIR.FileProcess.model.entity.DeviceDb;
import com.gl.CEIR.FileProcess.parse.AbstractCsvParser;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ConsignmentFileParser extends AbstractCsvParser<DeviceDb> {

	private Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public DeviceDb parse(String content) {
		if (skipFirstLine) {
			skipFirstLine = false;
			return null;
		}
		
		String data[] = content.split(Separator.COMMA);
		log.info("Content size = " + data.length);

		DeviceDb device = new DeviceDb();
		try {
			device.setDeviceType(data[0]);
			device.setDeviceIdType(data[1]);
			device.setMultipleSimStatus(data[2]);
			device.setSnOfDevice(data[3]);
			device.setImeiEsnMeid(data[4]);
			device.setDeviceLaunchDate(LocalDateTime.parse(data[5], DateTimeFormatter.ofPattern("DDMMYYYY")));
			device.setDeviceStatus(data[6]);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		return device;
	}
}
