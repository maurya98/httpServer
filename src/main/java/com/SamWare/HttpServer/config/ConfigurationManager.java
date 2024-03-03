package com.SamWare.HttpServer.config;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.SamWare.HttpServer.util.Json;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;

public class ConfigurationManager {
	private static ConfigurationManager myConfigurationManager;
	private static Configuration myCurrentConfiguration;

	private ConfigurationManager() {

	}

	public static ConfigurationManager getInstance() {
		if (myConfigurationManager == null) {
			myConfigurationManager = new ConfigurationManager();
		}
		return myConfigurationManager;
	}

	/*
	 * use to load configuration file by the path provided
	 */
	public void loadConfigurationFile(String filePath) {
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(filePath);
		} catch (FileNotFoundException e) {
			throw new HttpConfigurationException(e);
		}
		StringBuffer sb = new StringBuffer();
		int i;
		try {
			while ((i = fileReader.read()) != -1) {
				sb.append((char) i);
			}
		} catch (IOException e) {
			throw new HttpConfigurationException(e);
		}
		JsonNode conf;
		try {
			conf = Json.parse(sb.toString());
		} catch (IOException e) {
			throw new HttpConfigurationException("Error parsing the configuration File..");
		} 
		try {
			myCurrentConfiguration = Json.fromJson(conf, Configuration.class);
		} catch (JsonProcessingException e) {
			throw new HttpConfigurationException("Error parsing the configuration file, internal",e);
		}
	}

	/*
	 * Return the current loaded Configuration
	 */
	public Configuration getCurrentConfiguration() {
		if (myConfigurationManager == null) {
			throw new HttpConfigurationException("No Current Configuration Set..");
		}
		return myCurrentConfiguration;
	}
}
