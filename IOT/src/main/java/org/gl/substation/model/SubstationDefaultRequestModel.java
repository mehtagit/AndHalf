package org.gl.substation.model;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter @Setter
public class SubstationDefaultRequestModel {
private String unitId;
private String searchString;
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


}
