package com.gl.CEIR.GreyListProcessing.Beans;

public class ConfigurationDetails {


	private String listType;
	private int time;
	private String unit;
	private String dumptype;
	
	
	public String getDumptype() {
		return dumptype;
	}
	public void setDumptype(String dumptype) {
		this.dumptype = dumptype;
	}
	public String getListType() {
		return listType;
	}
	public void setListType(String listType) {
		this.listType = listType;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	@Override
	public String toString() {
		return "ConfigurationDetails [listType=" + listType + ", time=" + time + ", unit=" + unit + ", dumptype="
				+ dumptype + "]";
	}





}
