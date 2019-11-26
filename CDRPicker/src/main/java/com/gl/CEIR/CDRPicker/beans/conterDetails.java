package com.gl.CEIR.CDRPicker.beans;

public class conterDetails {

	private int actualCount;
	private int invaidCount;
	private int duplicateCount;
	private int totoaCount;
	
	public int getActualCount() {
		return actualCount;
	}
	public void setActualCount(int actualCount) {
		this.actualCount = actualCount;
	}
	public int getInvaidCount() {
		return invaidCount;
	}
	public void setInvaidCount(int invaidCount) {
		this.invaidCount = invaidCount;
	}
	public int getDuplicateCount() {
		return duplicateCount;
	}
	public void setDuplicateCount(int duplicateCount) {
		this.duplicateCount = duplicateCount;
	}
	
	public int getTotoaCount() {
		return totoaCount;
	}
	public void setTotoaCount(int totoaCount) {
		this.totoaCount = totoaCount;
	}
	@Override
	public String toString() {
		return "conterDetails [actualCount=" + actualCount + ", invaidCount=" + invaidCount + ", duplicateCount="
				+ duplicateCount + ", totoaCount=" + totoaCount + "]";
	}

	

	
}
