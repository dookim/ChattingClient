package com.zoco.chatclient.core;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.zoco.chatclient.common.User;

/** 
 * 
 */
public class Client2 {

	
	private ClientThread clientThread;
	static User user = new User("doo871128@gmail.com", "hufs", "facebook","1");

	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		Client2 client = new Client2();
		client.start("14.49.36.193", 8500);
		Thread.sleep(500);

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int i = 1;

		while (true) {
			String line = reader.readLine();

			if (line.equals("quit"))
				break;
			try {
				client.clientThread.sendMessage("2" ,1, i++, line);
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}

		}

		client.stop();
		System.out.println("BYE");
	}

	/**
	 * start client
	 * 
	 * @param host
	 * @param port
	 */
	public void start(String host, int port) {		

		if (clientThread == null || !clientThread.isAlive()) {
			
			clientThread = ClientThread.getInstance(host, port, user);
			clientThread.start();
		}
	}

	/**
	 * stop client
	 * 주석달고 thread pool
	 */
	public void stop() {
		
		clientThread.quitConnection();
		if (clientThread != null && clientThread.isAlive()) {
			clientThread.interrupt();
		}

	}


	
}
