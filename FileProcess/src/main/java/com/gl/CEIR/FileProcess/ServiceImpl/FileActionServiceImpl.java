package com.gl.CEIR.FileProcess.ServiceImpl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.CEIR.FileProcess.Repository.WebActionDbRepository;
import com.gl.CEIR.FileProcess.model.WebActionDb;


@Service
public class FileActionServiceImpl {

	private Logger log = LoggerFactory.getLogger(getClass());



	@Autowired
	WebActionDbRepository webActionDbRepository;

	public WebActionDb getFileActionDetails() {

		try {

			return 	webActionDbRepository.findFirstByState(0);

		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Found="+e);
			return null;
		}
	}






}
