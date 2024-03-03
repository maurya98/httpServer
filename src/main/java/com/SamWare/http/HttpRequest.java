package com.SamWare.http;

public class HttpRequest extends HttpMessage {
	
	private HttpMethod method;
	private String requestType;
	private String httpVersion;
	
	public HttpRequest() {
		// TODO Auto-generated constructor stub
	}

	public HttpMethod getMethod() {
		return method;
	}

	void setMethod(HttpMethod method) {
		this.method = method;
	}
	
}
