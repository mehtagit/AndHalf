package com.gl.CEIR.FileProcess.ServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ceir.fileprocess.model.constants.WebActionStatus;
import com.gl.ceir.fileprocess.model.entity.WebActionDb;
import com.gl.ceir.fileprocess.repository.WebActionDbRepository;

@Service
public class FileActionServiceImpl {

	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	WebActionDbRepository webActionDbRepository;

	public WebActionDb getFileActionDetails() {

		try {
			// TODO Read the oldest
			return 	webActionDbRepository.findFirstByState(WebActionStatus.INIT.getCode());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return null;
		}
	}
}
