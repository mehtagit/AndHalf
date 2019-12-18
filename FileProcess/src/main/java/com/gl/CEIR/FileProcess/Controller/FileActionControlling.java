package com.gl.CEIR.FileProcess.Controller;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gl.CEIR.FileProcess.ServiceImpl.FileActionServiceImpl;
import com.gl.CEIR.FileProcess.conf.AppConfig;
import com.gl.CEIR.FileProcess.service.WebActionFactory;
import com.gl.ceir.config.model.WebActionDb;

@Service
public class FileActionControlling implements Runnable {

	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	@Qualifier("config")
	AppConfig appConfig;
	
	@Autowired
	FileActionServiceImpl fileActionServiceImpl;
	
	@Autowired
	@Qualifier("WebActionFactoryImpl")
	WebActionFactory webActionFactory;

	@Override
	public void run() {

		// while(Boolean.TRUE) {
			
			try {
				WebActionDb webActionDb	= fileActionServiceImpl.getFileActionDetails();

				log.info("Web action Details Fetch = " + webActionDb);

				if(Objects.nonNull(webActionDb)) {
					webActionFactory.create(webActionDb).process(webActionDb);
				}
				
				// Thread.sleep(1000/appConfig.tps);

			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
		// }
	}
}
