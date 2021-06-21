package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) throws IOException {

		// create Socket instance
		Socket socket = new Socket();

		System.out.println("=== CLIENT START ===");
		System.out.println("[Request ... a connection to the server ...]");
		// System.out.println("... ... ... ... ...");
		System.out.println();

		socket.connect(new InetSocketAddress("3.36.134.188", 10001));
		System.out.println("[Connected to server]");

		// message OutputStream
		// OutputStream os = socket.getOutputStream();
		// OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
		BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
		// Scanner scan = new Scanner(System.in);
		BufferedReader text = new BufferedReader(new InputStreamReader(System.in));

		// message InputStream
		// InputStream is = socket.getInputStream();
		// InputStreamReader isr = new InputStreamReader(is, "UTF-8");
		BufferedReader bfr = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
		BufferedWriter sysout = new BufferedWriter(new OutputStreamWriter(System.out));

		while (true) {
			// send message
			// keyboard Input
			// String str = scan.nextLine();
			String str = text.readLine();
			if ("/q".equals(str)) {
				System.out.println("Server out");
				break;
			}
			bfw.write("(HOJIN: " + str + ")");
			bfw.newLine();
			bfw.flush();

			// get message
			String reM = bfr.readLine();
			System.out.println("Message from server: " + reM);
		}

		// System.out.println("=== CLIENT END ===");
		sysout.write("=== CLIENT END ===");
		sysout.newLine();
		sysout.flush();

		bfw.close();
		text.close();
		bfr.close();
		// scan.close();
		socket.close();

	}

}
