package com.gl.CEIR.FileProcess.parse;

public interface CsvParser<T> {
	
	public T parse(String content);
}
