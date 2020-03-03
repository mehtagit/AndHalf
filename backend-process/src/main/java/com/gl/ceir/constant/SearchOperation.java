package com.gl.ceir.constant;

public enum SearchOperation {
	EQUALITY, NEGATION, GREATER_THAN, LESS_THAN, LIKE;

	public static final String[] SIMPLE_OPERATION_SET = { ":", "!", ">", "<", "~" };

	public static SearchOperation getSimpleOperation(final char input)
	{
		switch (input) {
		case ':': return EQUALITY;
		case '!': return NEGATION;
		case '>': return GREATER_THAN;
		case '<': return LESS_THAN;
		case '~': return LIKE;
		default: return null;
		}
	}
}
