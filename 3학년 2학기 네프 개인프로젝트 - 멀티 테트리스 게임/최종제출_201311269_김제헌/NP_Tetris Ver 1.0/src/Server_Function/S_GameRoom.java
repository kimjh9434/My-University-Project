package Server_Function;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class S_GameRoom {

	private Socket socket; // 소켓
	private Socket socket2; // 소켓
	private S_RoomManager roomManager; // 메시지 방송자

	private DataInputStream reader; // 소켓의 입력 스트림
	private DataOutputStream writer; // 소켓의 출력 스트림
	private DataOutputStream writer2; // 소켓의 출력 스트림

	private S_Client c;

	private S_TetrisRoom s_TetrisRoom;

	// 생성자
	S_GameRoom(Socket socket, Socket socket2, S_RoomManager roomManager, S_Client c) {
		this.socket = socket;
		this.socket2 = socket2;
		this.roomManager = roomManager;

		try {
			reader = new DataInputStream(socket.getInputStream());
			writer = new DataOutputStream(socket.getOutputStream());
			writer2 = new DataOutputStream(socket2.getOutputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		this.c = c;
		s_TetrisRoom = new S_TetrisRoom(socket, socket2, roomManager, c);
	}

	// 게임방으로 넘어온 이후의 흐름 제어 서버
	public boolean controlStart() {
		boolean isRuuning = true;
		// 수신 메시지와 파싱 메시지 처리하는 변수 선언
		String msg;
		String[] rmsg;

		System.out.println("게임방으로 넘어간  이후의  흐름제어");
		while (isRuuning && c.isInGameRoom()) {
			try {
				System.out.println(c.getUserName() + " | S_GameRoom While 대기중");
				msg = reader.readUTF();
				System.out.println(c.getUserName() + " | S_GameRoom While : 클라이언트로 부터 받은 값 : " + msg);
				rmsg = msg.split("/"); // '/' 구분자를 기준으로 메시지를 문자열 배열로 파싱

				// 대화 메시지를 방에 전송한다.
				// 채팅[메시지] : msg = [MSG]/messages
				if (rmsg[0].equals("[MSG]")) {
					// 해당 방의 모든 사용자들에게 메시지를 전송한다.
					roomManager.sendToRoomMSG(c.getRoomNumber(), msg);
				}

				// 방 나가기 : msg = [EXIT_ROOM]/userName
				else if (rmsg[0].equals("[EXIT_ROOM]")) {
					// 해당 사용자가 roomNumber 게임방에서 나간다.
					roomManager.exitGameRoom(c);
					// 루프를 빠져나온다. 이후는 S_WaitingRoom.controlStart()에서 다시 통제함.
					isRuuning = false;
				}

				// 레디 : msg = [READY]/index/Ready or Not ready
				else if (rmsg[0].equals("[READY]")) { // 해당 클라이언트가 게임을 준비함.
					// => 해당 클라이언트 레디의 true/false 변환
					if (rmsg[2].equals("Ready")) {
						c.setReady(true);

					} else if (rmsg[2].equals("Not ready")) {
						c.setReady(false);
					}
					System.out.println(c.getUserName() + "," + c.isReady());
					// => 방의 클라이언트들에게 해당 클라이언트의 레디 값이 변경되었음을 알림.
					// msg = [READY]/userName/Ready or Not ready
					roomManager.sendToRoomOtherMSG(c, msg);
				}

				// 자리(열고 닫기) : msg = [PLACE]/index/Open or Close 
				// [이 메시지는 방장과 연결된 서버 쓰레드밖에 못받는다] index번째 자리가 열리고, 닫힘을 의미함.
				else if (rmsg[0].equals("[PLACE]")) { // 해당 클라이언트가 게임을 준비함.
					// => 만약 Open일 경우, 제한인원이 증가하고, Close이면 제한인원이 감소함
					if (rmsg[2].equals("Open")) {
						roomManager.getRoom(c.getRoomNumber()).plusRoomLimitEnterCount();
					} else if (rmsg[2].equals("Close")) {
						roomManager.getRoom(c.getRoomNumber()).minusRoomLimitEnterCount();
					}
					// => 방의 클라이언트들에게 해당 자리 값이 변경되었음을 알림.  
					// msg = [PLACE]/index/Open or Close
					roomManager.sendToRoomOtherMSG(c, msg);
				}
				
				// 강퇴 : msg = [FIRED]/userName [이 메시지는 방장과 연결된 서버 쓰레드밖에 못받는다]
				// userName인 클라이언트가 강퇴 당함을 의미.
				else if (rmsg[0].equals("[FIRED]")) {
					// 강퇴를 처리한다.
					roomManager.firedUser(c.getRoomNumber(), rmsg[1]);
				}

				// 게임 시작 : msg = [GAME_START] [이 메시지는 방장과 연결된 서버 쓰레드밖에 못받는다]
				else if (rmsg[0].equals("[GAME_START]")) {
					// 게임 시작을 검사한다.
					// 1. 플에이어가 2명이상인지 검사한다.
					if (roomManager.getRoom(c.getRoomNumber()).getcList().size() != 1) {
						// 2. 모든 플레이어들이 레디를 박았는지 검사한다.
//						if (roomManager.getRoom(c.getRoomNumber()).isReadyAll()) {
						if (true) {
							// 이 두 조건을 모두 충족 시킬경우,
							System.out.println("2명 이상과, 모두 레디를 함");
							roomManager.tetrisStart(c);
							
							writer2.writeUTF("[GAME_START]/SUCCESS");

							// 이후는 S_TetrisRoom.controlStart()에서 통제함.
							isRuuning = s_TetrisRoom.controlStart();
						} else {
							// 모든 인원이 레디를 하지 않음을 전한다.
							System.out.println("모든 인원이 레디를 하지 않음");
							writer2.writeUTF("[GAME_START]/FAILURE/NOT_READY_ALL");
						}
					} else {
						// 혼자서는 게임을 시작할수 없음을 전한다.
						System.out.println("혼자서는 게임을 시작할수 없음");
						writer2.writeUTF("[GAME_START]/FAILURE/NOT_START_ALONE");
					}
				}
				
				// 게임 시작  : 방장 외의 클라이언트 쓰레드와 연결된 서버 쓰레드들이 받는다.
				else if (rmsg[0].equals("[START]")) {
					// 이후는 S_TetrisRoom.controlStart()에서 통제함.
					isRuuning = s_TetrisRoom.controlStart();
				}

				// (강제)접속종료 : 클라이언트의 연결이 끊어진 경우
				else if (rmsg[0].equals("[QUIT]")) {
					roomManager.disconnectServer(c);
					isRuuning = false;
				}

			} catch (Exception e) {

				isRuuning = false;
			}
		}
		System.out.println(c.getUserName() + " | S_GameRoom.controlStart() 빠져나옴");
		return isRuuning;
	}
}