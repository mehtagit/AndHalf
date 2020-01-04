package com.gl.CEIR.FileProcess.parse;

public abstract class AbstractCsvParser implements CsvParser{
	protected final String DATE_FORMAT = "DDMMYYYY";
	protected boolean skipFirstLine = true;
}
