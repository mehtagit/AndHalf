package com.ceir.CeirCode.model;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class StakeholderFeature {
	@Id       
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private Date createdOn;
	private Date modifiedOn;
    private String category;
    private String name;
    private String logo;
    private String link;
    
    @JsonIgnore
    @OneToMany(mappedBy ="stakeholderFeature",cascade=CascadeType.ALL,fetch = FetchType.LAZY)
    private List<UserToStakehoderfeatureMapping> UserTofeatureMapping;
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public List<UserToStakehoderfeatureMapping> getUserTofeatureMapping() {
		return UserTofeatureMapping;
	}
	public void setUserTofeatureMapping(List<UserToStakehoderfeatureMapping> userTofeatureMapping) {
		UserTofeatureMapping = userTofeatureMapping;
	}
	
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	@Override
	public String toString() {
		return "StakeholderFeature [id=" + id + ", createdOn=" + createdOn + ", modifiedOn=" + modifiedOn
				+ ", category=" + category + ", name=" + name + ", logo=" + logo + ", link=" + link + "]";
	}
	
	
	
}
