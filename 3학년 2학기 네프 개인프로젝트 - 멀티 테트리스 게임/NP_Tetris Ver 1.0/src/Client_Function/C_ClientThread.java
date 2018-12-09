package Client_Function;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JOptionPane;

public class C_ClientThread implements Runnable {

	private Socket socket; // 주 소켓
	private Socket socket2; // 클라이언트쪽 메소드용 임시 소켓 [메소드에서 처리하는 것 전용]
	
	private DataInputStream reader; // 소켓의 입력 스트림
	private DataOutputStream writer; // 소켓의 출력 스트림

	public C_Login c_Login;
	public C_WaitingRoom c_WaitingRoom;

	public boolean isRuuning;

	// 그냥 생성자
	public C_ClientThread() {
		isRuuning=false;
	}

	// 서버와의 접속 연결하는 메소드
	public boolean connect() { 
		boolean isConnect = false;
		try {
			System.out.println("서버에 연결을 요청합니다.");
			socket = new Socket("127.0.0.1", 5000);
			socket2 = new Socket("127.0.0.1", 5000);

			System.out.println("---서버 접속 성공--");
			isConnect = true;
			reader = new DataInputStream(socket.getInputStream());
			writer = new DataOutputStream(socket.getOutputStream());
			
			c_Login = new C_Login(socket, socket2);
			c_WaitingRoom = new C_WaitingRoom(socket, socket2);
			
			new Thread(this).start(); // 쓰레드를 실행시킨다.
		} catch (Exception e) {
			System.out.println("\n\n서버 연결 실패..\n");
		}
		return isConnect;
	}

	// 맨처음 서버와 접속된 이후, 로그인 전까지의 흐름제어
	@Override
	public void run() {
		isRuuning = true; // 서버로부터의 메시지

		// 수신 메시지와 파싱 메시지 처리하는 변수 선언
		String msg;
		String[] rmsg;

		while (isRuuning) {
			try {
				msg = reader.readUTF();
				System.out.println("C_ClientThread | 서버로 부터 받은 값 : " + msg);
				rmsg = msg.split("/"); // '/' 구분자를 기준으로 메시지를 문자열 배열로 파싱
				
				//  msg = [LOGIN]/SUCCESS/id
				if (rmsg[0].equals("[LOGIN]")) {
					if (rmsg[1].equals("SUCCESS")) {
						// 이후는 c_WaitingRoom에서 제어함
						isRuuning = c_WaitingRoom.controlStart(rmsg[2]);//id 값을 넘겨준다.
					}
				}else if (rmsg[0].equals("[QUIT]")) { // 서버 다운
					JOptionPane.showMessageDialog(null, "알수 없는 이유로 서버가 다운되었습니다.", "서버 다운", JOptionPane.WARNING_MESSAGE);
					System.out.println("알수 없는 이유로 서버가 종료되었습니다.");
					isRuuning = false;
				}

				// 그냥 돌아가는 중
			} catch (Exception e) {
				System.out.println(e);
				System.out.println("클라이언트 쪽에서 에러가 발생해서, 서버와의 연결이 끊겼습니다.");
				try {
					// 서버쪽에 클라이언트와 접속을 끊으라고 전달함.
					writer.writeUTF("[QUIT]");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				System.out.println("서버와 접속을 종료함");
				isRuuning = false; 
			}
		}
	}

	public void quit() {
		isRuuning = false;
		try {
			writer.writeUTF("[QUIT]");
			System.out.println("서버와 접속을 종료함");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
