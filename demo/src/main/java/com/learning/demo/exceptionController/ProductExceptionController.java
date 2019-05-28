package com.learning.demo.exceptionController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.learning.demo.exceptions.ProductNotfoundException;
import com.learning.demo.exceptions.WrongProductfoundException;
/**
 * The @ControllerAdvice is an annotation, to handle the exceptions globally.
 * @author amehta
 *
 */
//@ControllerAdvice
public class ProductExceptionController {
	@ExceptionHandler(value = ProductNotfoundException.class)
	public ResponseEntity<Object> exception(ProductNotfoundException exception) {
		return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = WrongProductfoundException.class)
	public ResponseEntity<Object> exception(WrongProductfoundException exception) {
		return new ResponseEntity<>("Wrong Product found", HttpStatus.NOT_FOUND);
	}
}
