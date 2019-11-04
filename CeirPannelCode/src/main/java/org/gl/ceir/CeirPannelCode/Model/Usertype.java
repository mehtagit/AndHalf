package org.gl.ceir.CeirPannelCode.Model;
import java.util.Date;
public class Usertype { 
	private Integer id;
	private String usertypeName;
	private Date createdOn;
	private Date modifiedOn;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsertypeName() {
		return usertypeName;
	}
	public void setUsertypeName(String usertypeName) {
		this.usertypeName = usertypeName;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public Date getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	@Override
	public String toString() {
		return "Usertype [id=" + id + ", usertypeName=" + usertypeName + ", createdOn=" + createdOn + ", modifiedOn="
				+ modifiedOn + "]";
	}
}