package com.gl.ceir.config.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Action {
	@Id
	@GeneratedValue
	private Long id;
	private ActionNames name;
	@OneToMany
	private Set<ActionParameters> actionParameters;

	public Action() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ActionNames getName() {
		return name;
	}

	public void setName(ActionNames name) {
		this.name = name;
	}

	public Set<ActionParameters> getActionParameters() {
		return actionParameters;
	}

	public void setActionParameters(Set<ActionParameters> actionParameters) {
		this.actionParameters = actionParameters;
	}

}
