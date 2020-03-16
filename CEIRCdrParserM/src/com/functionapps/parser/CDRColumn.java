package com.functionapps.parser;

import javax.sound.sampled.Port;

public class CDRColumn {
	String columString;
	String graceType;
	String postGraceType;
	public CDRColumn(String columnName, String graceType,String postGraceType) {
		// TODO Auto-generated constructor stub
		this.columString = columnName;
		this.graceType = graceType;
		this.postGraceType = postGraceType;
	}

}
