package com.learning.demo.resource;

import java.util.Set;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import io.swagger.annotations.ApiModel;

@ApiModel
@Entity
public class DocumentsByImeiMsisdn {

	@EmbeddedId
	private ImeiMsisdnIdentity ImeiMsisdnIdentity;

	@OneToMany
	private Set<Documents> documents;

	public ImeiMsisdnIdentity getImeiMsisdnIdentity() {
		return ImeiMsisdnIdentity;
	}

	public void setImeiMsisdnIdentity(ImeiMsisdnIdentity imeiMsisdnIdentity) {
		ImeiMsisdnIdentity = imeiMsisdnIdentity;
	}

	public Set<Documents> getDocuments() {
		return documents;
	}

	public void setDocuments(Set<Documents> documents) {
		this.documents = documents;
	}

}
