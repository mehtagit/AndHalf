package com.functionapps.util;

public class Util {

	public static String defaultDate(boolean isOracle) {
		if(isOracle)
			return "sysdate";
		else
			return "now()";
	}
}
