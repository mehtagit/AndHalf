package org.gl.ceir.pagination.model;

import org.springframework.stereotype.Component;

@Component
public class UserManagementContent {

	private Integer id;
	private Integer usertypeId;
	private String usertypeName;
	private String createdOn;
	private String modifiedOn;
	private Integer status;
	private String statusInterp;
	private Integer period;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUsertypeId() {
		return usertypeId;
	}
	public void setUsertypeId(Integer usertypeId) {
		this.usertypeId = usertypeId;
	}
	public String getUsertypeName() {
		return usertypeName;
	}
	public void setUsertypeName(String usertypeName) {
		this.usertypeName = usertypeName;
	}
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	public String getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(String modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getStatusInterp() {
		return statusInterp;
	}
	public void setStatusInterp(String statusInterp) {
		this.statusInterp = statusInterp;
	}
	public Integer getPeriod() {
		return period;
	}
	public void setPeriod(Integer period) {
		this.period = period;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserManagementContent [id=");
		builder.append(id);
		builder.append(", usertypeId=");
		builder.append(usertypeId);
		builder.append(", usertypeName=");
		builder.append(usertypeName);
		builder.append(", createdOn=");
		builder.append(createdOn);
		builder.append(", modifiedOn=");
		builder.append(modifiedOn);
		builder.append(", status=");
		builder.append(status);
		builder.append(", statusInterp=");
		builder.append(statusInterp);
		builder.append(", period=");
		builder.append(period);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
}
