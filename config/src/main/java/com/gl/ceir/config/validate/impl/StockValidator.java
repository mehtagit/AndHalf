package com.gl.ceir.config.validate.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gl.ceir.config.exceptions.RequestInvalidException;
import com.gl.ceir.config.model.StockMgmt;
import com.gl.ceir.config.validate.BaseValidator;

public class StockValidator extends BaseValidator<StockMgmt>{
	private static final Logger logger = LogManager.getLogger(StockValidator.class);

	String target = "Stock_mgmt";
	
	@Override
	public boolean validateRegister(StockMgmt t) throws RequestInvalidException {
		RequestInvalidException exception = null;
		String action = "Register";
		
		return Boolean.TRUE;
	}

	@Override
	public boolean validateEdit(StockMgmt t) throws RequestInvalidException {

		return Boolean.TRUE;
	}

	@Override
	public boolean validateViewById(Object t) throws RequestInvalidException {
		
		return Boolean.TRUE;
	}

	@Override
	public boolean validateDelete(Object t) throws RequestInvalidException {
		
		return Boolean.TRUE;
	}

	@Override
	public boolean validateFilter(Object t) throws RequestInvalidException {
		return Boolean.TRUE;
	}

	@Override
	public boolean validateAcceptReject(Object t) throws RequestInvalidException {
		return Boolean.TRUE;
	}
}
