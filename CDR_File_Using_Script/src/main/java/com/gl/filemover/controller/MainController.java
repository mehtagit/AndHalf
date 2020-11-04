package com.gl.filemover.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainController {

	private final Logger logger = LogManager.getLogger(MainController.class);

	@Autowired
	FileMoveOnCurrentMachine fileTransfer;

	@Autowired
	SocketAliveUitlity socketAliveUitlity;

	@Autowired
	DeleteFileCP2 deleteFileCP2;
	public void processMethod(String processName,String operator,String source) {
		// TODO Auto-generated method stub
		switch (processName) {
		case "CP1": fileTransfer.cp1(operator,source);break;

		case "CP2":socketAliveUitlity.cp2(operator,source);break;

		case "CP3":deleteFileCP2.cp3(operator,source);break;

		default : logger.info("Method doesn't exist");break;
		}
	}

}
