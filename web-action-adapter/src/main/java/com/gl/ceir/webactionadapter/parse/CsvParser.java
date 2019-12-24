package com.gl.ceir.webactionadapter.parse;

public interface CsvParser<T> {
	
	public T parse(String content);
}
