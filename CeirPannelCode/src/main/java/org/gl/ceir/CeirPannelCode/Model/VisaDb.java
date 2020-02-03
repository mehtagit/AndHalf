package org.gl.ceir.CeirPannelCode.Model;

import java.util.HashMap;
import java.util.Map;

public class VisaDb {
	

private String createdOn;
private String entryDateInCountry;
private String modifiedOn;
private String visaExpiryDate;
private String visaNumber,visaFileName;
private Integer visaType;
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

public String getCreatedOn() {
return createdOn;
}

public void setCreatedOn(String createdOn) {
this.createdOn = createdOn;
}

public String getEntryDateInCountry() {
return entryDateInCountry;
}

public void setEntryDateInCountry(String entryDateInCountry) {
this.entryDateInCountry = entryDateInCountry;
}

public String getModifiedOn() {
return modifiedOn;
}

public void setModifiedOn(String modifiedOn) {
this.modifiedOn = modifiedOn;
}

public String getVisaExpiryDate() {
return visaExpiryDate;
}

public void setVisaExpiryDate(String visaExpiryDate) {
this.visaExpiryDate = visaExpiryDate;
}

public String getVisaNumber() {
return visaNumber;
}

public void setVisaNumber(String visaNumber) {
this.visaNumber = visaNumber;
}

public Integer getVisaType() {
return visaType;
}

public void setVisaType(Integer visaType) {
this.visaType = visaType;
}

public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}



public String getVisaFileName() {
	return visaFileName;
}

public void setVisaFileName(String visaFileName) {
	this.visaFileName = visaFileName;
}

public void setAdditionalProperties(Map<String, Object> additionalProperties) {
	this.additionalProperties = additionalProperties;
}

@Override
public String toString() {
	return "VisaDb [createdOn=" + createdOn + ", entryDateInCountry=" + entryDateInCountry + ", modifiedOn="
			+ modifiedOn + ", visaExpiryDate=" + visaExpiryDate + ", visaNumber=" + visaNumber + ", visaFileName="
			+ visaFileName + ", visaType=" + visaType + ", additionalProperties=" + additionalProperties + "]";
}



}
