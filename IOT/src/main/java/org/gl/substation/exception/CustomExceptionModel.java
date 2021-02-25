package org.gl.substation.exception;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class CustomExceptionModel {

	private  String message;
	private  HttpStatus status;
	private  ZonedDateTime time;
	

	public CustomExceptionModel(String message, HttpStatus status, ZonedDateTime time) {
		super();
		this.message = message;
		this.status = status;
		this.time = time;
	}
}
