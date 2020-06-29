package com.gl.ceir.config.validate.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gl.ceir.config.exceptions.RequestInvalidException;
import com.gl.ceir.config.model.StockMgmt;
import com.gl.ceir.config.service.impl.ConsignmentServiceImpl;
import com.gl.ceir.config.validate.Validator;

public class StockValidator implements Validator<StockMgmt>{
	private static final Logger logger = LogManager.getLogger(StockValidator.class);

	String target = "Stock_mgmt";
	
	@Override
	public boolean validateRegister(StockMgmt t) throws RequestInvalidException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validateEdit(StockMgmt t) throws RequestInvalidException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validateViewById(Object t) throws RequestInvalidException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validateDelete(Object t) throws RequestInvalidException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validateFilter(Object t) throws RequestInvalidException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validateAcceptReject(Object t) throws RequestInvalidException {
		// TODO Auto-generated method stub
		return false;
	}


}
