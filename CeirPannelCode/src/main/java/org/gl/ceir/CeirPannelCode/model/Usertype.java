package org.gl.ceir.CeirPannelCode.model;

public class Usertype {
private String usertype;
private Integer id;
public String getUsertype() {
	return usertype;
}
public void setUsertype(String usertype) {
	this.usertype = usertype;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id; 
}
@Override
public String toString() {
	return "usertype [usertype=" + usertype + ", id=" + id + "]";
}
}
