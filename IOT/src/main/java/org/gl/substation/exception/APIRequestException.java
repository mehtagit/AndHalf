package org.gl.substation.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@AllArgsConstructor @Getter @Setter
public class APIRequestException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private String resourceName;

	private String message;

	public APIRequestException (String message,Throwable cause) {
		super(message,cause);	
	}
}
