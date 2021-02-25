package org.gl.substation.exception;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
	@ExceptionHandler(value= {APIRequestException.class})
	public ResponseEntity<?> exceptionHandler(APIRequestException e){
		System.out.println(e.getMessage());
		// 1. create payload
		HttpStatus status = HttpStatus.EXPECTATION_FAILED;
		CustomExceptionModel customExceptionModel = new CustomExceptionModel(
				e.getMessage(),
				status,
				ZonedDateTime.now(ZoneId.of("Z"))
				);
		// 2.return response

		return new ResponseEntity<>(customExceptionModel,status);

	}
}
