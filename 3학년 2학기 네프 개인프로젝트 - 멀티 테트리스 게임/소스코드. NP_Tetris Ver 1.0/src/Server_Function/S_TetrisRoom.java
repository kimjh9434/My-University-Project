package Server_Function;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class S_TetrisRoom {

	private Socket socket; // 소켓
	private Socket socket2; // 소켓
	private S_RoomManager roomManager; // 메시지 방송자

	private DataInputStream reader; // 소켓의 입력 스트림
	private DataOutputStream writer; // 소켓의 출력 스트림

	private S_Client c;// 현재 서버 쓰래드와 1:1로 통신하고 있는 클라이언트의 상태값

	// 생성자
	S_TetrisRoom(Socket socket, Socket socket2, S_RoomManager roomManager, S_Client c) {
		this.socket = socket;
		this.socket2 = socket2;
		this.roomManager = roomManager;

		try {
			reader = new DataInputStream(socket.getInputStream());
			writer = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		this.c = c;
	}

	// 테트리스 게임이 시작한 이후의 흐름 제어 서버
	public boolean controlStart() {
		boolean isRuuning = true;
		// 수신 메시지와 파싱 메시지 처리하는 변수 선언
		String msg;
		String[] rmsg;

		System.out.println("테트리스 게임이 시작한  이후의 흐름 제어");
		while (isRuuning) {
			try {
				System.out.println(c.getUserName() + " | S_TetrisRoom While 대기중");
				msg = reader.readUTF();
				System.out.println(c.getUserName() + " | S_TetrisRoom While : 클라이언트로 부터 받은 값 : " + msg);
				rmsg = msg.split("/");// '/' 구분자를 기준으로 메시지를 문자열 배열로 파싱

				// 테트리스 명령어 : msg = [TETRIS_CMD]/userName/keyCode
				if (rmsg[0].equals("[TETRIS_CMD]")) {
					// 본인을 제외한, 그 방의 나머지 사람들에게 그대로 전송한다.
					roomManager.sendToRoomOtherMSG(c, msg);
				}

				// 게임오버[대기] & 게임 종료 : 해당 클라이언트가 게임오버 당함
				// msg = [GAME_OVER]/userName
				else if (rmsg[0].equals("[GAME_OVER]")) {
					roomManager.gameOver(c, rmsg[1]);
				}
				
				// 게임종료 : msg = [GAME_END]. 
				else if (rmsg[0].equals("[GAME_END]")) {
					//반복문을 빠져나가면 됨.
					isRuuning = false;
				}
				
				// 방 나가기 : msg = [EXIT_ROOM]/userName
				else if (rmsg[0].equals("[EXIT_ROOM]")) {
					roomManager.exitTetrisRoom(c);
					// 루프를 빠져나온다. 이후는 S_WaitingRoom.controlStart()에서 다시 통제함.
					isRuuning = false;
				}

				// (강제)접속종료 : 클라이언트의 연결이 끊어진 경우
				else if (rmsg[0].equals("[QUIT]")) {
					// 방의  사람들에게 메시지를 알려준다.
					// sendToRoom [GAME_OVER]/userName
					roomManager.disconnectServer(c);
					isRuuning = false;
				}

			} catch (Exception e) {
				System.out.println("S_TetrisRoom While 에러");
				System.out.println(e);
				
				roomManager.disconnectServer(c);
				isRuuning = false;
			}
		}
		System.out.println(c.getUserName() + " | S_TetrisRoom.controlStart() 빠져나옴");
		return isRuuning;
	}
}
