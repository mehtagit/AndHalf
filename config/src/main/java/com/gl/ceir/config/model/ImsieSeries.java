package com.gl.ceir.config.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ImsieSeries {
	@Id
	private int id;
	@ManyToOne
	private MobileOperator mobileOperator;
	long startRange;
	long endRange;

	public ImsieSeries() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public MobileOperator getMobileOperator() {
		return mobileOperator;
	}

	public void setMobileOperator(MobileOperator mobileOperator) {
		this.mobileOperator = mobileOperator;
	}

	public long getStartRange() {
		return startRange;
	}

	public void setStartRange(long startRange) {
		this.startRange = startRange;
	}

	public long getEndRange() {
		return endRange;
	}

	public void setEndRange(long endRange) {
		this.endRange = endRange;
	}

}
