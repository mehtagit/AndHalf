package com.gl.ceir.config.model.constants;

public enum RuleOperator {
	EQUAL_TO("EQUAL_TO"), GREATER_THEN("GREATER_THEN"), LESS_THEN("LESS_THEN"), BETWEEN(
			"BETWEEN"), GRETER_THEN_EQUALS_TO("GRETER_THEN_EQUALS_TO"), LESS_THEN_EQUALS_TO(
					"LESS_THEN_EQUALS_TO"), NOT_EQUALS("NOT_EQUALS"), IN("IN"), NOT_IN("NOT_IN");
	private String name;

	RuleOperator(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
