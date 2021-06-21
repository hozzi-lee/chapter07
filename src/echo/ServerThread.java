package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ServerThread extends Thread { // 출장관련(독립적인 프로세서) 부모쪽에 구현되어 있다

	// field
	private Socket socket;

	// constructors
	public ServerThread(Socket socket) {
		this.socket = socket;
	}

	// method
	// 출장가서 해야 할 일 --> 메세지 주고받기
	public void run() {

		try {
			// 메세지 받기
			BufferedReader bfr = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			// 메세지 보내기
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
		} catch (IOException e) {
			System.out.println("에러발생");
		}
	}

}
