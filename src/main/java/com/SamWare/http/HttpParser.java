package com.SamWare.http;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpParser {
	private final static Logger LOGGER = LoggerFactory.getLogger(HttpParser.class);
	
	public HttpRequest parseHttpRequest(InputStream inputStream) {
		InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.US_ASCII);
		
		HttpRequest request = new HttpRequest();
		
		parseResquesLine(reader, request);
		parseHeaders(reader,request);
		parseBody(reader,request);
		
		return request;
	}

	private void parseBody(InputStreamReader reader, HttpRequest request) {
		// TODO Auto-generated method stub
		
	}

	private void parseHeaders(InputStreamReader reader, HttpRequest request) {
		// TODO Auto-generated method stub
		
	}

	private void parseResquesLine(InputStreamReader reader, HttpRequest request) {
		// TODO Auto-generated method stub
		
	}
}
