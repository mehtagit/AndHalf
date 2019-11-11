package org.gl.ceir.Class.HeadersTitle;

import java.util.List;

import org.springframework.stereotype.Component;
@Component
public class DatatableResponseModel {


	@Override
	public String toString() {
		return "DatatableResponse [recordsTotal=" + recordsTotal + ", recordsFiltered=" + recordsFiltered + ", data="
				+ data + "]";
	}
	public Integer getRecordsTotal() {
		return recordsTotal;
	}
	public void setRecordsTotal(Integer recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	public Integer getRecordsFiltered() {
		return recordsFiltered;
	}
	public void setRecordsFiltered(Integer recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	public List<List<String>> getData() {
		return data;
	}
	public void setData(List<List<String>> data) {
		this.data = data;
	}
	private Integer recordsTotal;
	//Integer recordsTotal = new Integer(10)     int recordsTotal;  int x= recordsTotal.intValue();
	private Integer recordsFiltered;
	private List<List<String>> data = null;
}
