package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws IOException {

		// create ServerSocket instance
		ServerSocket serverSocket = new ServerSocket();

		// ServerSocket bind
		serverSocket.bind(new InetSocketAddress("192.168.35.135", 10001)); // ip, port

		System.out.println("=== SERVER START ===");
		System.out.println("[Waiting ... for a connection ...]");
		// System.out.println("... ... ... ... ...");
		System.out.println();

		// =========== loopArea ===========

		while (true) {
			Socket socket = serverSocket.accept();
			System.out.println("[The client has been connected]");

			// 출장 --> 세팅 + 메세지 주고받기
			Thread thread = new ServerThread(socket);
			thread.start();

			// 탈출조건 - 생략
		}

		/*
		 ***** Thread run() *
		// message InputStream
		// InputStream is = socket.getInputStream();
		// InputStreamReader isr = new InputStreamReader(is);
		BufferedReader bfr = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));

		// message OutputStream
		// OutputStream os = socket.getOutputStream();
		// OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
		BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));

		while (true) {
			// get message
			String msg = bfr.readLine();
			if (msg == null) {
				System.out.println("Client out");
				break;
			}
			System.out.println("Message from client: " + msg);

			// send message
			bfw.write(msg);
			bfw.newLine();
			bfw.flush();
		}
		 * Thread run() *****


		System.out.println("=== SERVER END ===");

		bfr.close();
		bfw.close();
		socket.close();
		serverSocket.close();
		 */

	}
}
