package com.gl.ceir.config.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FieldValidation {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String fieldName;
	
	private String fieldDescription;
	
	private Integer maxLength;
	
	private Integer minLength;
	
	private String type;
	
	private String allowedSpace;//Y if allowed N if not
	
	private String allowedCharacterset;
	
	private String regex;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getFieldDescription() {
		return fieldDescription;
	}
	public void setFieldDescription(String fieldDescription) {
		this.fieldDescription = fieldDescription;
	}
	public Integer getMaxLength() {
		return maxLength;
	}
	public void setMaxLength(Integer maxLength) {
		this.maxLength = maxLength;
	}
	public Integer getMinLength() {
		return minLength;
	}
	public void setMinLength(Integer minLength) {
		this.minLength = minLength;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAllowedSpace() {
		return allowedSpace;
	}
	public void setAllowedSpace(String allowedSpace) {
		this.allowedSpace = allowedSpace;
	}
	public String getAllowedCharacterset() {
		return allowedCharacterset;
	}
	public void setAllowedCharacterset(String allowedCharacterset) {
		this.allowedCharacterset = allowedCharacterset;
	}
	public String getRegex() {
		return regex;
	}
	public void setRegex(String regex) {
		this.regex = regex;
	}
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("FieldValidation [fieldName=");
		stringBuilder.append(fieldName);
		stringBuilder.append(", fieldDescription=");
		stringBuilder.append(fieldDescription);
		stringBuilder.append(", maxLength=");
		stringBuilder.append(maxLength);
		stringBuilder.append(", minLength=");
		stringBuilder.append(minLength);
		stringBuilder.append(", type=");
		stringBuilder.append(type);
		stringBuilder.append(", allowedSpace=");
		stringBuilder.append(allowedSpace);
		stringBuilder.append(", allowedCharacterset=");
		stringBuilder.append(allowedCharacterset);
		stringBuilder.append(", regex=");
		stringBuilder.append(regex);
		stringBuilder.append("]");
		return stringBuilder.toString();
	}
	
	

	

	
	

}
