package com.gl.ceir.config.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Action {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Enumerated(EnumType.STRING)
	private ActionNames name;

	@OneToMany(mappedBy = "action")
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
