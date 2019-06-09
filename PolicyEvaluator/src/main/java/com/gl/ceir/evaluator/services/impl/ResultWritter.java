package com.gl.ceir.evaluator.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gl.ceir.config.system.request.Request;
import com.gl.ceir.evaluator.services.Chain;
import com.gl.ceir.evaluator.services.InputRepository;

public class ResultWritter implements Chain {

	private Chain nextInChain;

	@Override
	public void setNext(Chain nextInChain) {
		this.nextInChain = nextInChain;
	}

	@Override
	public void process(Request requests) {

	}

}
