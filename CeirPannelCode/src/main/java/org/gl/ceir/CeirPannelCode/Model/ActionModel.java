package org.gl.ceir.CeirPannelCode.Model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class ActionModel {
	private Integer state;
	private Integer stateId;
	private String view;
	private Object downloadErrorFile;
	private Object edit;
	private String downloadFile;
	private Object delete;
	private String approve;
	private String reject;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getStateId() {
		return stateId;
	}
	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}
	public String getView() {
		return view;
	}
	public void setView(String view) {
		this.view = view;
	}
	public Object getDownloadErrorFile() {
		return downloadErrorFile;
	}
	public void setDownloadErrorFile(Object downloadErrorFile) {
		this.downloadErrorFile = downloadErrorFile;
	}
	public Object getEdit() {
		return edit;
	}
	public void setEdit(Object edit) {
		this.edit = edit;
	}
	public String getDownloadFile() {
		return downloadFile;
	}
	public void setDownloadFile(String downloadFile) {
		this.downloadFile = downloadFile;
	}
	public Object getDelete() {
		return delete;
	}
	public void setDelete(Object delete) {
		this.delete = delete;
	}
	public String getApprove() {
		return approve;
	}
	public void setApprove(String approve) {
		this.approve = approve;
	}
	public String getReject() {
		return reject;
	}
	public void setReject(String reject) {
		this.reject = reject;
	}
	public Map<String, Object> getAdditionalProperties() {
		return additionalProperties;
	}
	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ActionModel [state=");
		builder.append(state);
		builder.append(", stateId=");
		builder.append(stateId);
		builder.append(", view=");
		builder.append(view);
		builder.append(", downloadErrorFile=");
		builder.append(downloadErrorFile);
		builder.append(", edit=");
		builder.append(edit);
		builder.append(", downloadFile=");
		builder.append(downloadFile);
		builder.append(", delete=");
		builder.append(delete);
		builder.append(", approve=");
		builder.append(approve);
		builder.append(", reject=");
		builder.append(reject);
		builder.append(", additionalProperties=");
		builder.append(additionalProperties);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
