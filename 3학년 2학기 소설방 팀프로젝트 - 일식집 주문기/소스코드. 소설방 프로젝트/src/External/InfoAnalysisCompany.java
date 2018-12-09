package External;

import java.net.ServerSocket;
import java.net.Socket;

public class InfoAnalysisCompany {

	private ServerSocket server; // 서버 소켓

	// 서버를 실행한다. [얘는 계속 S_ClientThread를 받는 역할만 한다]
	public void startServer() {
		try {
			server = new ServerSocket(6000);
			System.out.println("정보분석회사 서버소켓[PortNum = 6000]이 생성되었습니다.");
			while (true) {
				// 클라이언트와 연결된 스레드를 얻는다.
				Socket socket = server.accept(); // 주 소켓

				// 스레드를 만들고 실행시킨다.
				InfoAnalysisServerThread serverThread = new InfoAnalysisServerThread(socket);
				serverThread.start();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void main(String[] args) {
		InfoAnalysisCompany server = new InfoAnalysisCompany();
		server.startServer();
	}
}