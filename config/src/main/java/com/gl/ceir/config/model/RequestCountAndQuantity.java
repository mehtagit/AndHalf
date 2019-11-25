package com.gl.ceir.config.model;

public class RequestCountAndQuantity {
	private long count;
	private long quantity;
	public RequestCountAndQuantity( long count) {
		this.count = count;
	}
	public RequestCountAndQuantity( long count, long quantity) {
		this.count = count;
		this.quantity = quantity;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
}
