package org.gl.ceir.CeirPannelCode.config.Service.Impl;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import org.gl.ceir.CeirPannelCode.config.Service.TicketIdGenerator;

@Service
public class TicketIdGeneratorImpl implements TicketIdGenerator {
	private AtomicInteger ticketIdPrefix = new AtomicInteger(1);

	public synchronized String getTicketId() {
		if (ticketIdPrefix.get() >= 1000)
			ticketIdPrefix.set(1);
		return "" + ticketIdPrefix.get() + System.currentTimeMillis();
	}

}
