package com.gl.ceir.config.service.impl;

import org.springframework.stereotype.Service;

import com.gl.ceir.config.service.TicketIdGenerator;

@Service
public class TicketIdGeneratorImpl implements TicketIdGenerator {

	@Override
	public String getTicketId() {
		return "" + System.currentTimeMillis();
	}

}
