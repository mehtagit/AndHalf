package com.gl.ceir.config.exceptions.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.gl.ceir.config.exceptions.ActionParametersNotFoundException;

@ControllerAdvice
public class AllExceptions {
	@ExceptionHandler(value = ActionParametersNotFoundException.class)
	public ResponseEntity<Object> exception(ActionParametersNotFoundException exception) {
		return new ResponseEntity<>("ActionParameters not found", HttpStatus.NOT_FOUND);
	}
}
