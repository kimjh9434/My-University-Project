package Server_Function;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public // 클라이언트와 통신하는 스레드 클래스
class S_ClientThread extends Thread {
	private Socket socket; // 주 소켓
	private Socket socket2; // 임시 소켓
	private S_RoomManager roomManager; // 방 관리자 겸 메시지 방송자

	private DataInputStream reader;  // 소켓의 입력 스트림
	private DataOutputStream writer; // 소켓의 출력 스트림
	
	private S_Login s_Login;
	private S_WaitingRoom s_WaitingRoom;
	public boolean isRuuning;
	
	S_ClientThread(Socket socket, Socket socket2, S_RoomManager roomManager) { // 생성자
		System.out.println("클라이언트가 서버에 접속했습니다.");
		this.socket = socket;
		this.socket2 = socket2;
		this.roomManager = roomManager;
		
		try {
			reader = new DataInputStream(socket.getInputStream());
			writer = new DataOutputStream(socket2.getOutputStream());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		s_Login = new S_Login(socket, socket2, roomManager); // 로그인 및 회원가입을 제어함
		s_WaitingRoom = new S_WaitingRoom(socket, socket2, roomManager); // 대기화면으로 넘어간 이후, 흐름제어
		isRuuning = true;
	}

	public void run() {
		isRuuning = true;
		// 수신 메시지와 파싱 메시지 처리하는 변수 선언
		String msg;
		String[] rmsg;

		while (isRuuning) {
			try {
				System.out.println("S_ClientThread While  대기중");
				msg = reader.readUTF();
				System.out.println("S_ClientThread While: 클라이언트로 부터 받은 값 : " + msg);
				rmsg = msg.split("/"); // '/' 구분자를 기준으로 메시지를 문자열 배열로 파싱
				
				// 로그인 : msg = [LOGIN]/id/pwd
				if (rmsg[0].equals("[LOGIN]")) { 
					if(s_Login.login(rmsg[1], rmsg[2])){// 로그인에 성공했을 경우,
						// 이후는 S_WaitingRoom.controlStart()에서 통제함.
						isRuuning = s_WaitingRoom.controlStart();
					}
				} 
				
				// 회원가입 : msg = [JOIN]/id/pwd
				else if (rmsg[0].equals("[JOIN]")) { 
					s_Login.join(rmsg[1], rmsg[2]);	
				} 
				
				// 접속 종료
				else if (rmsg[0].equals("[QUIT]")) { 
					isRuuning = false;
				}
			} catch (Exception e) {
				isRuuning = false;
			}
		}
		// 최종적으로, 정상적이던 에러가 발생했던, 클라이언트로부터 종료 메시지를 받으면, 클라이언트로부터 연락을 끊는다.
		if (isRuuning == false) {
			try {
//				if (reader != null)
//					reader.close();
//				if (writer != null)
//					writer.close();
//				if (socket != null)
//					socket.close();
//				if (socket2 != null)
//					socket2.close();
				reader = null;
				writer = null;
				socket = null;
				socket2 = null;
			} catch (Exception e) {
				System.out.println("접속 끊김");
				e.printStackTrace();
			}
		}

	}
}