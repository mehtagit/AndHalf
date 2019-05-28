package com.gl.ceir.config.exceptions.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.gl.ceir.config.exceptions.ResourceNotFoundException;
import com.gl.ceir.config.exceptions.ResourceServicesException;
import com.gl.ceir.config.model.ApiResponse;

@ControllerAdvice
public class AllExceptions {

	@ExceptionHandler(value = ResourceNotFoundException.class)
	public ResponseEntity<Object> exception(ResourceNotFoundException exception) {
		ApiResponse apiResponse = new ApiResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage());
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	@ExceptionHandler(value = ResourceServicesException.class)
	public ResponseEntity<Object> exception(ResourceServicesException exception) {
		ApiResponse apiResponse = new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"Please Try after some time");
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}
}
