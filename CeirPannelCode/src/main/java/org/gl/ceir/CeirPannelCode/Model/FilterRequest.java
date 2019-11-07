package org.gl.ceir.CeirPannelCode.Model;

public class FilterRequest {

	
	private int consignmentStatus;
	private String endDate;
	private String  startDate;
	private String taxPaidStatus;
	private  String userId;
	public int getConsignmentStatus() {
		return consignmentStatus;
	}
	public void setConsignmentStatus(int consignmentStatus) {
		this.consignmentStatus = consignmentStatus;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getTaxPaidStatus() {
		return taxPaidStatus;
	}
	public void setTaxPaidStatus(String taxPaidStatus) {
		this.taxPaidStatus = taxPaidStatus;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "FilterRequest [consignmentStatus=" + consignmentStatus + ", endDate=" + endDate + ", startDate="
				+ startDate + ", taxPaidStatus=" + taxPaidStatus + ", userId=" + userId + "]";
	}
	
	
	
}
