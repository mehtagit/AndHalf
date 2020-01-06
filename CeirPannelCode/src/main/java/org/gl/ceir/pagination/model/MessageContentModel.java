package org.gl.ceir.pagination.model;

import org.springframework.stereotype.Component;

@Component
public class MessageContentModel {
	
	private Integer id;
	private Object createdOn;
	private Object modifiedOn;
	private String tag;
	private String value;
	private String description;
	private String channel;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Object getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Object createdOn) {
		this.createdOn = createdOn;
	}
	public Object getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(Object modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	@Override
	public String toString() {
		return "MessageContentModel [id=" + id + ", createdOn=" + createdOn + ", modifiedOn=" + modifiedOn + ", tag="
				+ tag + ", value=" + value + ", description=" + description + ", channel=" + channel + "]";
	}
	
	

}
