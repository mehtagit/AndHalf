package com.gl.ceir.converter;

import org.springframework.stereotype.Component;

@Component
public class CurrencyConverter {
	
	public double exchangeRate(double inCurrency, double outCurrency, double amount) {
		return (inCurrency/outCurrency) * amount;
	}

}
