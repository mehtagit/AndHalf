package org.gl.ceir.CeirPannelCode.Model;

public class CountApprovedData {
	
	private Integer allowed,current;

	public Integer getAllowed() {
		return allowed;
	}

	public void setAllowed(Integer allowed) {
		this.allowed = allowed;
	}

	public Integer getCurrent() {
		return current;
	}

	public void setCurrent(Integer current) {
		this.current = current;
	}

	@Override
	public String toString() {
		return "CountApprovedData [allowed=" + allowed + ", current=" + current + "]";
	}

	
}
