package com.ceir.BlackListProcess.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceir.BlackListProcess.process.BlackListProcess;

@Service
public class BlackListService implements Runnable{

	@Autowired
	BlackListProcess blackListProcess;

	private final Logger log =LoggerFactory.getLogger(getClass());


	@Override
	public void run() {

		while(true) {
			log.info("inside blacklist service");
			blackListProcess.blackListProcess();
			log.info("exit from blacklist service");
			try {
				Thread.sleep(3600000);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}