package com.gl.CEIR.CDRPicker.beans;

import java.util.List;

public class ImeiHashRecord {
	private int multipleImsiCount ;
	private int multipleMsisdnCount;
	private List<HashDetails> dataList;
	public int getMultipleImsiCount() {
		return multipleImsiCount;
	}
	public void setMultipleImsiCount(int multipleImsiCount) {
		this.multipleImsiCount = multipleImsiCount;
	}
	public int getMultipleMsisdnCount() {
		return multipleMsisdnCount;
	}
	public void setMultipleMsisdnCount(int multipleMsisdnCount) {
		this.multipleMsisdnCount = multipleMsisdnCount;
	}
	public List<HashDetails> getDataList() {
		return dataList;
	}
	public void setDataList(List<HashDetails> dataList) {
		this.dataList = dataList;
	}
	
}
