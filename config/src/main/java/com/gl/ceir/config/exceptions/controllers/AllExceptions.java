package com.gl.ceir.config.exceptions.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.gl.ceir.config.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class AllExceptions {
	@ExceptionHandler(value = ResourceNotFoundException.class)
	public ResponseEntity<Object> exception(ResourceNotFoundException exception) {
		return new ResponseEntity<>(exception.getResourceName() + " not found", HttpStatus.NOT_FOUND);
	}
}
