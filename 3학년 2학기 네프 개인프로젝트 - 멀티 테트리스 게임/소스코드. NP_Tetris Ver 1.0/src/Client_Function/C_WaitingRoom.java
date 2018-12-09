package Client_Function;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JOptionPane;

import Client_GUI_Form.GUI_JoinRoomJFrame;
import Client_GUI_Form.GUI_MultiTetrisJFrame;
import Client_GUI_Form.GUI_QuickMachJFrame;
import Client_GUI_Form.GUI_WaitingJFrame;
import Tetris.testMulti;

public class C_WaitingRoom {

	private Socket socket; // 주 소켓
	private Socket socket2; // 임시 소켓

	private DataInputStream reader; // 소켓의 입력 스트림
	private DataInputStream reader2; // 소켓의 입력 스트림
	private DataOutputStream writer; // 소켓의 출력 스트림

	public C_GameRoom c_GameRoom;
	public C_TetrisRoom c_TetrisRoom;
	
	private GUI_WaitingJFrame waitingJFrame;
	private GUI_JoinRoomJFrame joinRoomJFrame;
	private GUI_QuickMachJFrame quickMachJFrame;
	private GUI_MultiTetrisJFrame multiTetrisJFrame;
	private boolean isQuickMachWaiting;

	private C_Client c;
	private Vector<String> pList; // 대기실의 사용자 명단을 보여주는 리스트
	private Vector<C_Room> rList; // 현재 활성화되어 있는 방 목록을 보여주는 리스트
	
	private boolean isRuuning;

	// 생성자
	public C_WaitingRoom(Socket socket, Socket socket2) {
		this.socket = socket;
		this.socket2 = socket2;

		try {
			reader = new DataInputStream(socket.getInputStream());
			reader2 = new DataInputStream(socket2.getInputStream());
			writer = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		isQuickMachWaiting = false;
		this.c = new C_Client();
		this.pList = new Vector<String>();
		this.rList = new Vector<C_Room>();

		c_GameRoom = new C_GameRoom(socket, socket2, c);
		c_TetrisRoom = new C_TetrisRoom(socket, socket2, c);
	}

	// 로그인 성공후, 대기실화면부터 다른 게임방에 들어가기 전까지의 흐름 제어
	public boolean controlStart(String id) {
		isRuuning = true;
		
		c.setUserName(id); // 지금 사용자의 이름은 넘겨받은 id값임.
		try { // 서버쪽에 클라이언트의 이름과, 클라이언트가 대기실에 입장했다고 알린다.
			writer.writeUTF("[NAME]/" + c.getUserName());
			writer.writeUTF("[JOIN_ROOM]/0/0");
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("대기실 화면으로 넘어간  이후의  흐름제어");
		// 수신 메시지와 파싱 메시지 처리하는 변수 선언
		String msg = null;
		String[] rmsg;

		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		while (isRuuning) {
			try {
				System.out.println(c.getUserName() + " | C_WaitingRoom While 대기중");
				msg = reader.readUTF();
				System.out.println(c.getUserName() + " | C_WaitingRoom While : 서버로 부터 받은 값 : " + msg);
				rmsg = msg.split("/"); // '/' 구분자를 기준으로 메시지를 문자열 배열로 파싱

				// 채팅[메시지] : msg =[MSG]/msg
				if (rmsg[0].equals("[MSG]")) {
					recvTalk(rmsg[1]);
				}

				// 방[대기실 등] 입장함 : msg
				// =[JOIN_ROOM]/roomNumber/roomName/roomOwnerName
				else if (rmsg[0].equals("[JOIN_ROOM]")) { // 방 입장에 성공함
					c.setroomNumber(Integer.parseInt(rmsg[1])); // 방 번호 지정
					c.setRoomName(rmsg[2]);
					if (c.getroomNumber() != 0) // 대기실이 아닐경우,
						// 이후는 c_GameRoom에서 제어함
						c_GameRoom.controlStart(rmsg[3]); 
				}

				// 게임방 생성됨 : msg = [CREATE_ROOM]/roomNumber/roomName
				else if (rmsg[0].equals("[CREATE_ROOM]")) { // 새로운 방이 만들어짐을 받음
					// 방 목록에 방을 추가
					rList.add(new C_Room(Integer.parseInt(rmsg[1]), rmsg[2], 5));
					if (joinRoomJFrame != null)
						roomsInfo();// 방목록을 갱신한다.
				}

				// 접속자 목록 : msg = [PLAYERS]/userName1 /t userName2 ...
				else if (rmsg[0].equals("[PLAYERS]")) { // 방에 있는 사용자 명단
					if (!rmsg[1].equals(" ")) {
						// 띄어쓰기 공백 ' '을 없앤후, nameList를 갱신한다.
						resetNameList(rmsg[1].substring(1, rmsg[1].length()));
					}
				}

				// 방 목록 : msg = [ROOMS]/roomName1 /t roomName2 ...
				else if (rmsg[0].equals("[ROOMS]")) { 
					// 띄어쓰기 공백 ' '을 없앤후, roomList를 갱신한다.
					if (joinRoomJFrame != null)
						roomList(rmsg[1].substring(1, rmsg[1].length()));
				}
				
				// 빠른대전 : msg = [QUICK_MATCH]/Start or Waiting
				//                             Start/nameList... 
				else if (rmsg[0].equals("[QUICK_MATCH]")) { 
					if (rmsg[1].equals("Start")) {
						// 바로 게임을 시작한다. 게심 시작 성공
						
						// 기존에 대기하고 있었다면 해당 화면은 내린다]
						if(isQuickMachWaiting){
							quickMachJFrame.dispose();
							
							// 자신과 연결하고 있는 서버쓰래드에게 S_Tetris로 넘어가라고 지시한다.
							writer.writeUTF("[QUICK_MATCH]/Start");
						}
						// 이름 리스트 받은걸 쪼개서 받는다. 그리고 그걸 넘긴다.
						String[] nameList = nameList(rmsg[2]);
						multiTetrisJFrame = new GUI_MultiTetrisJFrame(waitingJFrame, c_TetrisRoom, nameList);
						System.out.println("멀티 테트리스 화면 이동");
						// 이후는 C_TetrisRoom.controlStart()에서 다시 통제함.
						c_TetrisRoom.controlStart();
						
					}else if (rmsg[1].equals("Waiting")) {
						// 대기창을 띄운다.
						quickMachJFrame = new GUI_QuickMachJFrame(waitingJFrame, this);
						isQuickMachWaiting = true;
					}
				}

				// 입장 : msg = [ENTER]/userName
				else if (rmsg[0].equals("[ENTER]")) {
					pList.add(rmsg[1]);
					playersInfo();
					waitingJFrame.totalChat_jTextArea.append(String.format("[%s]님이 입장하였습니다.\n", rmsg[1]));
				}

				// 퇴장 : msg = [EXIT]/userName
				else if (rmsg[0].equals("[EXIT]")) {
					pList.remove(rmsg[1]); // 리스트에서 제거
					playersInfo(); // 인원수를 다시 계산하여 보여준다.
					waitingJFrame.totalChat_jTextArea.append(String.format("[%s]님이 다른방으로 입장하였습니다.\n", rmsg[1]));
				}

				// 연결 끊김 : msg = [DISCONNECT]/userName
				else if (rmsg[0].equals("[DISCONNECT]")) { // 손님 접속 종료
					pList.remove(rmsg[1]);
					playersInfo();
					waitingJFrame.totalChat_jTextArea.append(String.format("[%s]님의 접속을 종료했습니다.\n", rmsg[1]));
				}

				// 서버(강제) 종료 : msg = [QUIT]
				else if (rmsg[0].equals("[QUIT]")) { // 서버 다운
					JOptionPane.showMessageDialog(null, "알수 없는 이유로 서버가 다운되었습니다.", "서버 다운", JOptionPane.WARNING_MESSAGE);
					System.out.println("알수 없는 이유로 서버가 다운되었습니다.");
					isRuuning = false;
				}

			} catch (Exception e) {
				e.printStackTrace();
				// 사용자가 접속을 끊겼음을 서버에게 알린다.
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
		System.out.println(c.getUserName() + " | C_WaitingRoom.controlStart() 빠져나옴");
		return isRuuning;
	}

	// 대기실로 버튼을 누르면, WaitingJFrame생성자가 호출될 때 호출된다.
	public void enterWatingRoom(GUI_WaitingJFrame waitingJFrame) {
		this.waitingJFrame = waitingJFrame;
	}

	// 채팅 메시지를 입력했을 때, 서버로 메시지를 보낸다.
	public void sendTalk() {
		String msg = waitingJFrame.inputChat_jTextField.getText();

		// 메세지를 적당히 자른 후에 서버로 보낸다.
		if (msg.length() == 0)
			return;
		if (msg.length() >= 30)
			msg = msg.substring(0, 30);
		try { // msg = [MSG]/message
			writer.writeUTF(String.format("[MSG]/[%s] : %s", c.getUserName(), msg));
		} catch (Exception e) {
			e.printStackTrace();
		}
		waitingJFrame.inputChat_jTextField.setText("");
	}

	// 메시지를 받는다.
	public void recvTalk(String msg) {
		waitingJFrame.totalChat_jTextArea.append(msg + "\n");
	}

	// 사용자 리스트에서 사용자들을 추출하여 pList에 추가한다.
	private void resetNameList(String msg) {
		pList.removeAll(pList);
		StringTokenizer st = new StringTokenizer(msg, "\t");
		while (st.hasMoreElements())
			pList.add(st.nextToken());
		playersInfo();
	}
	
	// 사용자 리스트에서 사용자들을 추출하여 nameList에 추가한다.
	private String[] nameList(String msg) {
		String[] nameList = new String[5];
		int i=0;
		StringTokenizer st = new StringTokenizer(msg, "\t");
		while (st.hasMoreElements()){
			nameList[i] = st.nextToken();
			i++;
		}
		for(int j=i;j<5;j++){
			nameList[j]="";
		}
		
		String tempStr;
		for(int j=0;j<5;j++){
			if(nameList[j].equals(c.getUserName())){
				tempStr = nameList[0];
				nameList[0] = nameList[j];
				nameList[j] = tempStr;
				break;
			}
		}
		return nameList;
	}

	// 대기실 방에 있는 접속자의 수를 보여준다.
	private void playersInfo() {
		int count = pList.size();
		waitingJFrame.accListNum_jLabel.setText("" + count);
		waitingJFrame.accList_jTextArea.setText("");
		for (int i = 0; i < count; i++) {
			waitingJFrame.accList_jTextArea.append(pList.get(i) + "\n");
		}
	}

	// 방 리스트에서 방이름들을 추출하여 pList에 추가한다.
	private void roomList(String msg) {
		System.out.println("roomList:" + msg);
		String[] rmsg = null;
		rList.removeAll(rList);
		StringTokenizer st = new StringTokenizer(msg, "\t");
		while (st.hasMoreElements()) {
			rmsg = st.nextToken().split(",");
			rList.add(new C_Room(Integer.parseInt(rmsg[0]), rmsg[1], 5));
		}
		roomsInfo();
	}

	// 현재 생성되어 있는 방 목록을 보여준다.
	public void roomsInfo() {
		System.out.println("roomsInfo()");
		joinRoomJFrame.roomList_jList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
    		
		String[] strings = { };
		int count = rList.size();
		if(count!=0){
			strings = new String[count];
		}
		
		for (int i = 0; i < count; i++) {
			strings[i] = rList.get(i).getRoomName();
		}
		joinRoomJFrame.roomList_jList.setListData(strings);
	}

	///////////////////////////////////////////////////////////////////

	// '방 생성'에서 '생성'하기 버튼을 눌렀을 때, 방동됨
	public void createRoom(String roomName, String roomPwd) {
		// msg = [CREATE_ROOM]/roomName/roomPwd/userName
		try {
			writer.writeUTF(String.format("[CREATE_ROOM]/%s/%s/%s", roomName, roomPwd, c.getUserName()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	///////////////////////////////////////////////////////////////////

	// '방 참여'에서 들어갔을 때,
	public void enterJoinRoom(GUI_JoinRoomJFrame joinRoomJFrame) {
		this.joinRoomJFrame = joinRoomJFrame;
		roomsInfo();
		try {
			writer.writeUTF("[ROOMS]");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean JoinRoom(int roomIndex, String roomPwd) {
		boolean isJoinRoom = false;
		String msg; // 수신 메시지와 파싱 메시지 처리하는 변수 선언
		String[] rmsg;
		int roomNumber = rList.get(roomIndex).getroomNumber();
		System.out.println("방 입장");
		try {
			// 1. 새 아이디와 비밀번호를 받는다.
			System.out.println("입력한 방 번호 값 : " + roomNumber);
			System.out.println("입력한 방 비밀번호 값 : " + roomPwd);

			// 2. 서버로 보낸다. // msg = [JOIN_ROOM]/roomNumber/roomPwd
			writer.writeUTF(String.format("[JOIN_ROOM]/%d/%s", roomNumber, roomPwd));

			// 3. 방입장 성공여부를 받는다.
			msg = reader2.readUTF();
			rmsg = msg.split("/"); // '/' 구분자를 기준으로 메시지를 문자열 배열로 파싱
			if (rmsg[0].equals("[JOIN_ROOM]")) {
				if (rmsg[1].equals("SUCCESS")) {
					System.out.println("방입장이 성공적으로 됨");
					isJoinRoom = true;
				} else if (rmsg[1].equals("FAILURE")) {
					if (rmsg[2].equals("FULL")) {
						JOptionPane.showMessageDialog(null, "이미 해당 방이 가득차 있습니다.", "방 입장 불가",
								JOptionPane.WARNING_MESSAGE);
						System.out.println("방이 차서 입장할 수 없습니다.");
					} else if (rmsg[2].equals("WRONGPWD")) { // 들어가려는 방의 비밀번호가
																// 틀렸으면,
						JOptionPane.showMessageDialog(null, "해당 방의 비밀번호가 틀렸습니다.", "방 입장 불가",
								JOptionPane.WARNING_MESSAGE);
						System.out.println("비밀번호가 틀려서 입장할 수 없습니다.");
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return isJoinRoom;
	}

	// 빠른대전 요청.
	public void quickMatch() {
		try {
			writer.writeUTF("[QUICK_MATCH]/Request");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// 빠른대전 취소요청.
	public void quickMatchCancle() {
		try {
			writer.writeUTF("[QUICK_MATCH]/Cancle");
		} catch (IOException e) {
			e.printStackTrace();
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
