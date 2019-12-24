package com.gl.ceir.webactionadapter.parse;

public abstract class AbstractCsvParser<T> implements CsvParser<T>{
	protected boolean skipFirstLine = true;
}
