package com.gl.ceir.config.model;

import java.util.Date;

public class FilterRequest {

    
    
    private String category;
    private String reportName;
    private String EmailId;
    private String Action;
    private String flag;
    	private String userType;
    	private String userName;
        
         private Date createdOn;
         private Date modifiedOn;

	private Integer featureId;
	private Integer userTypeId;
	private String searchString;

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

        
        
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getEmailId() {
        return EmailId;
    }

    public void setEmailId(String EmailId) {
        this.EmailId = EmailId;
    }

    public String getAction() {
        return Action;
    }

    public void setAction(String Action) {
        this.Action = Action;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getFeatureId() {
        return featureId;
    }

    public void setFeatureId(Integer featureId) {
        this.featureId = featureId;
    }

    public Integer getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(Integer userTypeId) {
        this.userTypeId = userTypeId;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    @Override
    public String toString() {
        return "FilterRequest{" + "category=" + category + ", reportName=" + reportName + ", EmailId=" + EmailId + ", Action=" + Action + ", flag=" + flag + ", userType=" + userType + ", userName=" + userName + ", featureId=" + featureId + ", userTypeId=" + userTypeId + ", searchString=" + searchString + '}';
    }
        
        
        
    
    
}
