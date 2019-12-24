package com.gl.ceir.webactionadapter.utility;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class HttpResponse {

	private String response;
	private Integer errorCode;
	
	private Map<String, String> responseHeaders;

	
	public String getResponse() {
		return response;
	}
	public Integer getErrorCode() {
		return errorCode;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	
	public Map<String, String> getResponseHeaders() {
		return responseHeaders;
	}
	public void setResponseHeaders(Map<String, String> responseHeaders) {
		this.responseHeaders = responseHeaders;
	}
	
	@Override
	public String toString() {
		final int maxLen = 10;
		StringBuilder builder = new StringBuilder();
		builder.append("HttpResponse [response=");
		builder.append(response);
		builder.append(", errorCode=");
		builder.append(errorCode);
		builder.append(", responseHeaders=");
		builder.append(responseHeaders != null ? toString(responseHeaders.entrySet(), maxLen) : null);
		builder.append("]");
		return builder.toString();
	}
	
	private String toString(Collection<?> collection, int maxLen) {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		int i = 0;
		for (Iterator<?> iterator = collection.iterator(); iterator.hasNext() && i < maxLen; i++) {
			if (i > 0)
				builder.append(", ");
			builder.append(iterator.next());
		}
		builder.append("]");
		return builder.toString();
	}
}
