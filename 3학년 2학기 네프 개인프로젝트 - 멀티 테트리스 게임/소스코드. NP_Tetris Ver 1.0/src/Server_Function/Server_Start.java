package Server_Function;

import java.net.ServerSocket;
import java.net.Socket;

// 게임 전체를 컨트롤 하는 서버
public class Server_Start {

	private ServerSocket server; // 서버 소켓
	private S_RoomManager roomManager; // 메시지 방송자

	public Server_Start() {
		this.roomManager = new S_RoomManager(); // 방 관리자를 만든다.
		roomManager.addRoom(new S_Room(0, "대기실", "0", "", 0));// 대기실 방을 만든다.
	}

	// 서버를 실행한다. [얘는 계속 S_ClientThread를 받는 역할만 한다]
	public void startServer() {
		try {
			server = new ServerSocket(5000);
			System.out.println("서버소켓[PortNum = 5000]이 생성되었습니다.");
			while (true) {
				// 클라이언트와 연결된 스레드를 얻는다.
				System.out.println("클라이언트를 기다립니다.");
				Socket socket = server.accept(); // 주 소켓
				Socket socket2 = server.accept(); // 클라이언트쪽 메소드용 임시 소켓

				// 스레드를 만들고 실행시킨다.
				S_ClientThread client_Thread = new S_ClientThread(socket, socket2, roomManager);
				client_Thread.start();
			}
		} catch (Exception e) {
			System.out.println(e);
			roomManager.serverDown();
		}
	}

	public static void main(String[] args) {
		Server_Start server = new Server_Start();
		server.startServer();
	}
}