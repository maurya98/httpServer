package com.SamWare.HttpServer.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpConnectionWorkerThread extends Thread {

	private final static Logger LOGGER = LoggerFactory.getLogger(HttpConnectionWorkerThread.class);
	private Socket socket;

	public HttpConnectionWorkerThread(Socket socket) {
		super();
		this.socket = socket;
	}

	@Override
	public void run() {

		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			inputStream = socket.getInputStream();
			outputStream = socket.getOutputStream();
			
//			Testing HTTP Request
//			int _byte ;
//			while((_byte = inputStream.read())>=0) {
//				System.out.print((char) _byte);
//			}
			
			String html = "<html><head><title>Http Server</title></head><body><h1>This is a simple response page</h1></body></html>";
			final String CRLF = "\n\r";
			String response = "HTTP\1.1 200 OK" + CRLF + "Content-Length: " + html.getBytes().length + CRLF + CRLF
					+ html + CRLF + CRLF;
			// System.out.println(response.getBytes());
			outputStream.write(response.getBytes());

			LOGGER.info("Processing finished..");
		} catch (IOException e) {
			LOGGER.error("Problem in connection.."+ e.getMessage());
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (outputStream != null) {
				try {
					outputStream.flush();
					outputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (socket != null) {
					try {
						socket.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}
	}

}
