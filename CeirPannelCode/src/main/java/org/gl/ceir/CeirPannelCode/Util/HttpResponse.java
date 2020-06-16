package org.gl.ceir.CeirPannelCode.Util;

public class HttpResponse {
	private String response;
	private Integer statusCode;
	private Object data;
	private String tag;

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	@Override
	public String toString() {
		return "HttpResponse [response=" + response + ", statusCode=" + statusCode + ", data=" + data + ", tag=" + tag
				+ "]";
	}

	public HttpResponse(String response, Integer statusCode, String tag) {
		super();
		this.response = response;
		this.statusCode = statusCode;
		this.tag = tag;
	}

	public HttpResponse() {
		super();
	}

}
