package com.SamWare.http;

public enum HttpStatusCode {

	CLIENT_ERROR_400_BAD_REQUEST(400,"Bad Request"),
	CLIENT_ERROR_405_METHOD_NOT_ALLOWED(401,"Method Not Allowed"),
	CLIENT_ERROR_414_URI_TOO_LONG(414,"URI too Long");
	
	public final int STATUS_CODE;
	public final String MESSAGE;
	
	private HttpStatusCode(int STATUS_CODE, String MESSAGE) {
		this.STATUS_CODE = STATUS_CODE;
		this.MESSAGE = MESSAGE;
	}
	
}
