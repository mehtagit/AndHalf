package com.gl.ceir.config.exceptions.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.gl.ceir.config.exceptions.ResourceNotFoundException;
import com.gl.ceir.config.exceptions.ResourceServicesException;
import com.gl.ceir.config.service.impl.MobileOperatorServiceImpl;

import sun.security.ec.ECDHKeyAgreement;

@ControllerAdvice
public class AllExceptions {
	private static final Logger logger = LogManager.getLogger(AllExceptions.class);

	@ExceptionHandler(value = ResourceNotFoundException.class)
	public ResponseEntity<Object> exception(ResourceNotFoundException exception) {
		return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = ResourceServicesException.class)
	public ResponseEntity<Object> exception(ResourceServicesException exception) {
		logger.info("Exception " + exception);
		return new ResponseEntity<>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
