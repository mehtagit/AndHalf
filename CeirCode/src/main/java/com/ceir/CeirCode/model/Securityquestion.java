package com.ceir.CeirCode.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class Securityquestion {
	private static long serialVersionUID = 1L;
	@Id    
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String question;
	private Integer category;
	
	@JsonIgnore
	@OneToMany(mappedBy = "securityQuestion",  fetch = FetchType.LAZY)
	private List<UserSecurityquestion> userSecurityquestion;


	public List<UserSecurityquestion> getUserSecurityquestion() {
		return userSecurityquestion;
	}
	public void setUserSecurityquestion(List<UserSecurityquestion> userSecurityquestion) {
		this.userSecurityquestion = userSecurityquestion;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "Securityquestion [id=" + id + ", question=" + question + ", category=" + category + "]";
	}
	public Securityquestion(long id, String question) {
		super();
		this.id = id;
		this.question = question;
	}
	public Securityquestion() {
		super();
	}
	

	
}
