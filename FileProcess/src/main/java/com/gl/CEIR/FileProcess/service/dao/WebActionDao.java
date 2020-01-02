package com.gl.CEIR.FileProcess.service.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gl.CEIR.FileProcess.model.constants.WebActionStatus;
import com.gl.CEIR.FileProcess.model.entity.WebActionDb;
import com.gl.CEIR.FileProcess.repository.WebActionDbRepository;

@Component
public class WebActionDao {

	@Autowired
	WebActionDbRepository webActionDbRepository;
	
	public WebActionDb setProcessing(WebActionDb webActionDb) {
		// Set WebAction state as Processing(1).
		webActionDb.setState(WebActionStatus.PROCESSING.getCode());
		return webActionDbRepository.save(webActionDb);
	}

}
