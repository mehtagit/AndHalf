package com.ceir.CeirCode.model;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
@Entity
public class Feature {
	private static long serialVersionUID = 1L;
	@Id  
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private Date modifiedOn;
	private Date createdOn;
	private Integer pid=0;  
	private String logo;
	private String featureName;
	private String link; 
	  
	/*
	 * @OneToMany(mappedBy = "feature", cascade = CascadeType.ALL,fetch =
	 * FetchType.LAZY) private List<user_feature_action_mapping>
	 * user_feature_action_mapping;
	 * 
	 * 
	 * @OneToMany(mappedBy = "feature", cascade = CascadeType.ALL,fetch =
	 * FetchType.LAZY) private List<user_to_feature_mapping> userFeatureMapping;
	 * 
	 * public List<user_to_feature_mapping> getUserFeatureMapping() { return
	 * userFeatureMapping; }
	 */
	/*
	 * public void setUserFeatureMapping(List<user_to_feature_mapping>
	 * userFeatureMapping) { this.userFeatureMapping = userFeatureMapping; }
	 */
	public Date getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public long getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getFeatureName() {
		return featureName;
	}
	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	@Override
	public String toString() {
		return "feature [id=" + id + ", modifiedOn=" + modifiedOn + ", createdOn=" + createdOn + ", pid=" + pid
				+ ", logo=" + logo + ", featureName=" + featureName + ", link=" + link + "]";
	}


}
