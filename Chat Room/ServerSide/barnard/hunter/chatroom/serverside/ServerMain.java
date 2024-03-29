package barnard.hunter.chatroom.serverside;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class ServerMain {
	public static void main(String[] args) {
		int port = 4444;
		try {
			ServerSocket serverSocket = new ServerSocket(port);
			while (true) {
				System.out.println("About to accept client connection...");
				Socket clientSocket = serverSocket.accept();
				System.out.println("Accepted connection from " + clientSocket);
				Thread t = new Thread() {
					@Override
					public void run() {
						try {
							handleClientSocket(clientSocket);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				};
				t.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void handleClientSocket(Socket clientSocket) throws IOException {
		OutputStream outputStream = clientSocket.getOutputStream();
		for (int i = 0; i < 10; i++) {
			outputStream.write(("Time now is " + new Date() + "\n").getBytes());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		clientSocket.close();
	}
}
