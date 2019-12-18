package com.gl.CEIR.FileProcess.parse;

public abstract class AbstractCsvParser<T> implements CsvParser<T>{
	protected boolean skipFirstLine = true;
}
