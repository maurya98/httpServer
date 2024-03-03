package com.SamWare.HttpServer;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.SamWare.HttpServer.config.Configuration;
import com.SamWare.HttpServer.config.ConfigurationManager;
import com.SamWare.HttpServer.core.ServerListenerThread;

public class HttpServer {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(HttpServer.class);
	
	public static void main(String[] args) {
		
		LOGGER.info("Starting Server...");
		
		ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
		Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();
		
		LOGGER.info("Using Port: "+ conf.getPort());
		LOGGER.info("Using Webroot: "+ conf.getWebroot());
		try {
			ServerListenerThread serverListenerThread = new ServerListenerThread(conf.getPort(), conf.getWebroot());
			serverListenerThread.start();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

}
