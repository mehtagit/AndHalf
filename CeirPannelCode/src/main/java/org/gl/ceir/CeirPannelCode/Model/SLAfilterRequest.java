package org.gl.ceir.CeirPannelCode.Model;

public class SLAfilterRequest {
	private String usertype,searchString;
	private Integer featureId,feature, pageNo, pageSize;
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public String getSearchString() {
		return searchString;
	}
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	public Integer getFeatureId() {
		return featureId;
	}
	public void setFeatureId(Integer featureId) {
		this.featureId = featureId;
	}
	public Integer getFeature() {
		return feature;
	}
	public void setFeature(Integer feature) {
		this.feature = feature;
	}
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SLAfilterRequest [usertype=");
		builder.append(usertype);
		builder.append(", searchString=");
		builder.append(searchString);
		builder.append(", featureId=");
		builder.append(featureId);
		builder.append(", feature=");
		builder.append(feature);
		builder.append(", pageNo=");
		builder.append(pageNo);
		builder.append(", pageSize=");
		builder.append(pageSize);
		builder.append("]");
		return builder.toString();
	}
	
	
}
