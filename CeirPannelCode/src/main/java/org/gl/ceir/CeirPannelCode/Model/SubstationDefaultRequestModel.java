package org.gl.ceir.CeirPannelCode.Model;

import org.springframework.stereotype.Component;

@Component
public class SubstationDefaultRequestModel {
	private String unitId;
	private String searchString;
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	public String getSearchString() {
		return searchString;
	}
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SubstationDefaultRequestModel [unitId=");
		builder.append(unitId);
		builder.append(", searchString=");
		builder.append(searchString);
		builder.append("]");
		return builder.toString();
	}
}
