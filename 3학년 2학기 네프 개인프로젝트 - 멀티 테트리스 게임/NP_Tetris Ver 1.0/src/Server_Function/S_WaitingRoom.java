package Server_Function;

import java.awt.print.Printable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class S_WaitingRoom {

	private Socket socket; // 소켓
	private Socket socket2; // 임시 소켓
	private S_RoomManager roomManager; // 메시지 방송자

	private DataInputStream reader; // 소켓의 입력 스트림
	private DataOutputStream writer; // 소켓의 출력 스트림
	private DataOutputStream writer2; // 소켓의 출력 스트림

	private S_Client c;// 현재 서버 쓰래드와 1:1로 통신하고 있는 클라이언트의 상태값

	private S_GameRoom s_GameRoom;
	private S_TetrisRoom s_TetrisRoom;

	// 생성자
	S_WaitingRoom(Socket socket, Socket socket2, S_RoomManager roomManager) {
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

		c = new S_Client(socket, -1, ""); // socket, roomNumber, userName
		this.roomManager.addClient(c); // 접속에 성공한 클라이언트를 RoomManager에 추가한다!!!
		System.out.println("대기실 접속자 수: " + roomManager.getClientCountInRoom(0));

		s_GameRoom = new S_GameRoom(socket, socket2, roomManager, c);
		s_TetrisRoom = new S_TetrisRoom(socket, socket2, roomManager, c);
	}

	// 로그인 성공후, 대기실화면부터 게임제어 서버
	public boolean controlStart() {
		boolean isRuuning = true;
		// 대기화면으로 넘어간 이후의 흐름제어
		System.out.println("대기실 화면으로 넘어간  이후의  흐름제어");
		// 수신 메시지와 파싱 메시지 처리하는 변수 선언
		String msg;
		String[] rmsg;

		while (isRuuning) {
			try {
				System.out.println(c.getUserName() + " | S_WatingRoom While 대기중");
				msg = reader.readUTF();
				System.out.println(c.getUserName() + " | S_WatingRoom While : 클라이언트로 부터 받은 값 : " + msg);
				rmsg = msg.split("/"); // '/' 구분자를 기준으로 메시지를 문자열 배열로 파싱

				// 클라이언트 이름 : msg = [NAME]/userName
				if (rmsg[0].equals("[NAME]")) {
					c.setUserName(rmsg[1]); // userName을 정한다.
				}

				// 방[대기실 등] 입장 : msg =[JOIN_ROOM]/roomNumber/roomPwd
				else if (rmsg[0].equals("[JOIN_ROOM]")) {
					System.out.println("[JOIN_ROOM]");
					int roomNumber = Integer.parseInt(rmsg[1]);

					if (!roomManager.isFull(roomNumber)) { // 방이 찬 상태가 아니면
						System.out.println("방이 꽉차지 않음");
						if (roomManager.isEnter(roomNumber, rmsg[2])) {
							System.out.println("방 입장에 성공함");
							if (roomNumber == 0) {// 맨처음 대기실에 입장하는 상황이라면
								// 대기실에 입장하라고 한다.
								roomManager.enterWaitingRoom(c);
							} else {
								// 게임방에 입장하는 상황이라면
								// 게임방에 입장하라고 한다.
								roomManager.enterGameRoom(roomNumber, c);
								// 사용자에게 방에 성공적으로 입장하였음을 알린다.
								writer2.writeUTF("[JOIN_ROOM]/SUCCESS");
								// 이후는 S_GameRoom.controlStart()에서 통제함.
								s_GameRoom.controlStart();
							}
						} else {
							// 사용자에게 비밀번호가 틀렸음을 방이 찼음을 알린다.
							System.out.println("비밀번호가 틀림");
							writer2.writeUTF("[JOIN_ROOM]/FAILURE/WRONGPWD");
						}
					} else {
						// 사용자에 방이 찼음을 알린다.
						System.out.println("방이 꽉참");
						writer2.writeUTF("[JOIN_ROOM]/FAILURE/FULL");
					}
				}

				// 방 생성 : msg = [CREATE_ROOM]/roomName/roomPwd
				else if (rmsg[0].equals("[CREATE_ROOM]")) {
					System.out.println("[CREATE_ROOM] : " + rmsg.toString());
					// 새로운 방을 만들고 거기에 들어간다.
					roomManager.createNewRoomAndEnter(c, rmsg[1], rmsg[2]);

					// 이후는 GameRoomServer.controlStart()에서 통제함.
					s_GameRoom.controlStart();
				}

				// 채팅[메시지] : msg = [MSG]/messages
				else if (rmsg[0].equals("[MSG]")) {
					// 해당 방의 모든 사용자들에게 메시지를 전송한다.
					roomManager.sendToRoomMSG(c.getRoomNumber(), msg);
				}

				// 방 목록 요청 : msg = [ROOMS]
				else if (rmsg[0].equals("[ROOMS]")) {
					// 대기실을 제외한 활성화된 모든 방의 목록을 보내준다.
					roomManager.sendToRoomMSG(0, roomManager.getRoomNames());
				}

				// 빠른 대전 : msg = [QUICK_MATCH]/Request or Cancle or Start
				else if (rmsg[0].equals("[QUICK_MATCH]")) {
					if (rmsg[1].equals("Request")) {
						if(roomManager.addClient_QuickMatch_waitingCList(c)){
							// 5명이 모두 꽉찼다면, 멀티 테트리스를 시작한다.
							// 이후는 S_TetrisRoom.controlStart()에서 통제함.
							s_TetrisRoom.controlStart();
						}
					} else if (rmsg[1].equals("Cancle")) {
						roomManager.removeClient_QuickMatch_waitingCList(c);
					} else if(rmsg[1].equals("Start")) {
						// 5명이 모두 꽉찼다면, 멀티 테트리스를 시작한다.
						// 이후는 S_TetrisRoom.controlStart()에서 통제함.
						s_TetrisRoom.controlStart();
					}
				}

				// (강제)접속종료 : 클라이언트가 대기실에서 나가겠다는 메시지이면,
				else if (msg.equals("[QUIT]")) {
					roomManager.exitRoom(c, 0);
					isRuuning = false;
				}
			} catch (Exception e) {
				System.out.println("S_WatingRoom While 에러");
				System.out.println(e);
				roomManager.exitRoom(c, 0);
				isRuuning = false;
			}
		}
		System.out.println(c.getUserName() + " | S_WaitingRoom.controlStart() 빠져나옴");
		return isRuuning;
	}
}
