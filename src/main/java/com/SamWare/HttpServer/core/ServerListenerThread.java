package com.SamWare.HttpServer.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerListenerThread extends Thread {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(ServerListenerThread.class);
	
	private int port;
	private String webroot;
	private ServerSocket serverSocket;
	
	public ServerListenerThread(int port, String webroot) throws IOException {
		this.port = port;
		this.webroot = webroot;
		this.serverSocket=new ServerSocket(this.port);
	}

	@Override
	public void run() {
		try {
			
			while(!serverSocket.isClosed() && serverSocket.isBound()) {
			Socket socket= serverSocket.accept();
			
			LOGGER.info("Connection Accepted: " + socket.getInetAddress());
			
			HttpConnectionWorkerThread connectionWorkerThread = new HttpConnectionWorkerThread(socket);
			connectionWorkerThread.start();
			}
			//serverSocket.close();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			if (serverSocket!=null) {
				try {
					serverSocket.close();
				}catch(Exception e){
					LOGGER.error("Error occured while closing the server socket: " + e.getMessage());
				}
			}
		}
	}
}
