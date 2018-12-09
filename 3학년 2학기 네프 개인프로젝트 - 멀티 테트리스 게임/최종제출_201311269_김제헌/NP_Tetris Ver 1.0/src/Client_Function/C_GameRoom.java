package Client_Function;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JOptionPane;

import Client_GUI_Form.GUI_GameRoomJFrame;
import Client_GUI_Form.GUI_MultiTetrisJFrame___;
import Client_GUI_Form.GUI_WaitingJFrame;

public class C_GameRoom {

	private Socket socket; // 주 소켓
	private Socket socket2; // 임시 소켓

	private DataInputStream reader; // 소켓의 입력 스트림
	private DataInputStream reader2; // 소켓의 입력 스트림
	private DataOutputStream writer; // 소켓의 출력 스트림

	public C_TetrisRoom c_TetrisRoom;

	private C_Client c;
	private Vector<String> pList; // 대기실의 사용자 명단을 보여주는 리스트
	private Vector<C_Room> rList; // 현재 활성화되어 있는 방 목록을 보여주는 리스트

	private GUI_GameRoomJFrame gameRoomJFrame;

	private String roomOwnerName; // 방장 이름

	public C_GameRoom(Socket socket, Socket socket2, C_Client c) {
		this.socket = socket;
		this.socket2 = socket2;

		try {
			reader = new DataInputStream(socket.getInputStream());
			reader2 = new DataInputStream(socket2.getInputStream());
			writer = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.c = c;
		this.pList = new Vector<String>();
		this.rList = new Vector<C_Room>();

		c_TetrisRoom = new C_TetrisRoom(socket, socket2, c);

		roomOwnerName = null;
	}

	public boolean controlStart(String roomOwnerName) {
		boolean isRuuning = true;
		this.roomOwnerName = roomOwnerName;
		System.out.println(c.getUserName() + " | 게임방 화면으로 넘어간  이후의  흐름제어");
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
				System.out.println(c.getUserName() + " | C_GameRoom While 대기중");
				msg = reader.readUTF();
				System.out.println(c.getUserName() + " | C_GameRoom While : 서버로 부터 받은 값 : " + msg);
				rmsg = msg.split("/"); // '/' 구분자를 기준으로 메시지를 문자열 배열로 파싱

				// 채팅[메시지] : msg =[MSG]/msg
				if (rmsg[0].equals("[MSG]")) {
					recvTalk(rmsg[1]);
				}

				// 방 나가기 : msg = [EXIT_ROOM]
				else if (rmsg[0].equals("[EXIT_ROOM]")) { // 게임방을 나감.
					// 클라이언트의 대기실 방 번호0을 지정하고,
					c.setroomNumber(0); // 방 번호 지정
					// 클라이언트의 방 이름[대기실]을 지정한 후,
					c.setRoomName("대기실");
					// - 이후, 게임방 제어 C_WaitingRoom.controlStart() 에서 통제함.
					// [사실상 반복문을 빠져나오면 됨]
					isRuuning = false;
				}

				// 레디 : msg = [READY]/index/Ready or Not ready
				else if (rmsg[0].equals("[READY]")) {
					// 해당 index 위치의 값을 Ready or Not ready로 적절하게 바꿔준다.
					gameRoomJFrame.changeReady(Integer.parseInt(rmsg[1]), rmsg[2]);
				}

				// 자리(열고 닫기) : msg = [PLACE]/index/Open or Close
				else if (rmsg[0].equals("[PLACE]")) {
					// 해당 index 위치의 값을 Open or Close로 적절하게 바꿔준다.
					gameRoomJFrame.changePlace(Integer.parseInt(rmsg[1]), rmsg[2]);
				}

				// 강퇴 : msg = [FIRED]/userName
				else if (rmsg[0].equals("[FIRED]")) {
					// => 본인이 강퇴되었는지 확인한다. [본인 이름 == userName]
					if (rmsg[1].equals(c.getUserName())) {// if 본인이 강퇴되었다면,
						// 본인이 방장에 의해 강퇴되었다고 알린다.
						JOptionPane.showMessageDialog(null, "방장에 의해 강퇴당했습니다.", "방 강제 퇴실", JOptionPane.WARNING_MESSAGE);
						System.out.println("너님 강퇴");

						// 클라이언트의 대기실 방 번호0을 지정하고,
						c.setroomNumber(0); // 방 번호 지정
						// 클라이언트의 방 이름[대기실]을 지정한 후,
						c.setRoomName("대기실");

						// 본인과 연결된 서버쪽으로 무의미한 메시지를 하나 날린다.
						writer.writeUTF("깔깔깔깔 강퇴당함 하하하하");
						// 루프를 빠져나온다.
						// 이후, 게임방 제어 C_WaitingRoom.controlStart()에서 통제함.
						gameRoomJFrame.Back_jButtonActionPerformed(null);
						isRuuning = false;
					} else {// else 타인이 강퇴되었다면,
							// 타인이 방장에 의해 강퇴되었다고 알린다.
						gameRoomJFrame.roomChat_jTextArea.append(String.format("[%s]님이 강퇴당하였습니다.\n", rmsg[1]));
					}
				}

				// 게임 시작 : msg = [GAME_START]
				else if (rmsg[0].equals("[GAME_START]")) { // 게임이 시작함
					// 만약 자신이 방장이 아닐경우, 
					if (!c.getUserName().equals(roomOwnerName)) {
						// 자신의 서버 쓰레드에게도 알려준다.
						writer.writeUTF("[START]");
						
						// 자신의 GUI도 게임화면으로 바꾼다.
						gameRoomJFrame.GameStart();
					}

					// 이후는 C_TetrisRoom.controlStart()에서 다시 통제함.
					isRuuning = c_TetrisRoom.controlStart();
				}

				// 접속자 목록 : [PLAYERS]/....... : 게임방의 접속자 userName 목록을 의미함.
				else if (rmsg[0].equals("[PLAYERS]")) { // 방에 있는 사용자 명단
					if (!rmsg[1].equals(" ")) {
						// 띄어쓰기 공백 ' '을 없앤후, nameList를 갱신한다.
						nameList(rmsg[1].substring(1, rmsg[1].length()));
					}
				}

				// 입장 : msg = [ENTER]/userName
				else if (rmsg[0].equals("[ENTER]")) { // 손님 입장
					pList.add(rmsg[1]);
					enterGameRoom(rmsg[1], 2);
					playersInfo();
					gameRoomJFrame.roomChat_jTextArea.append(String.format("[%s]님이 입장하였습니다.\n", rmsg[1]));
				}

				// 퇴장 : msg = [EXIT]/userName
				else if (rmsg[0].equals("[EXIT]")) { // 손님 퇴장
					pList.remove(rmsg[1]);
					exitGameRoom(rmsg[1]);
					playersInfo();
					gameRoomJFrame.roomChat_jTextArea.append(String.format("[%s]님이 대기실로 나갔습니다.\n", rmsg[1]));
				}

				// 방 터짐 : msg = [ROOM_DESTROYED]
				else if (rmsg[0].equals("[ROOM_DESTROYED]")) {
					// 방장이 나가서 방이 터졌음을 알린다.
					JOptionPane.showMessageDialog(null, "방장이 방을 나갔습니다.", "방 폭발", JOptionPane.WARNING_MESSAGE);
					System.out.println("방 빵터짐");

					// 클라이언트의 대기실 방 번호0을 지정하고,
					c.setroomNumber(0); // 방 번호 지정
					// 클라이언트의 방 이름[대기실]을 지정한 후,
					c.setRoomName("대기실");
					// 본인과 연결된 서버쪽으로 무의미한 메시지를 하나 날린다.
					writer.writeUTF("깔깔깔깔 방터짐 하하하하");
					// 루프를 빠져나온다.
					// 이후, 게임방 제어 C_WaitingRoom.controlStart()에서 통제함.
					gameRoomJFrame.Back_jButtonActionPerformed(null);
					isRuuning = false;
				}

				// 연결 끊김 : msg = [DISCONNECT]/userName
				else if (rmsg[0].equals("[DISCONNECT]")) { // 손님 접속 종료
					exitGameRoom(rmsg[1]);
					pList.remove(rmsg[1]);
					playersInfo();
					gameRoomJFrame.roomChat_jTextArea.append(String.format("[%s]님의 접속이 끊겼습니다.\n", rmsg[1]));
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
		System.out.println(c.getUserName() + " | C_GameRoom.controlStart() 빠져나옴");
		return isRuuning;
	}

	// 대기실로 버튼을 누르면, GameRoomJFrame생성자가 호출될 때 호출된다.
	public void enterGameRoom(GUI_GameRoomJFrame gameRoomJFrame) {
		this.gameRoomJFrame = gameRoomJFrame;
		gameRoomJFrame.roomName_jTextField.setText(c.getRoomName());

		// 만약 방장이 아니면 시작버튼을 안보이게 한다. + 강퇴할수 없게 비활성화 시킨다.
		if (!roomOwnerName.equals(c.getUserName())) {
			gameRoomJFrame.GameStart_jButton.setEnabled(false);
			gameRoomJFrame.GameStart_jButton.setVisible(false);

			// 강퇴는 방장만 조작가능
			gameRoomJFrame.payer1_jComboBox.setEnabled(false);
			gameRoomJFrame.payer2_jComboBox.setEnabled(false);
			gameRoomJFrame.payer3_jComboBox.setEnabled(false);
			gameRoomJFrame.payer4_jComboBox.setEnabled(false);
			gameRoomJFrame.payer5_jComboBox.setEnabled(false);
		}

		// 레디 버튼을 모두 안보이게 한다. + 비활성화 시킨다.
		gameRoomJFrame.payer1Ready_jComboBox.setVisible(false);
		gameRoomJFrame.payer1Ready_jComboBox.setEnabled(false);
		gameRoomJFrame.payer2Ready_jComboBox.setVisible(false);
		gameRoomJFrame.payer2Ready_jComboBox.setEnabled(false);
		gameRoomJFrame.payer3Ready_jComboBox.setVisible(false);
		gameRoomJFrame.payer3Ready_jComboBox.setEnabled(false);
		gameRoomJFrame.payer4Ready_jComboBox.setVisible(false);
		gameRoomJFrame.payer4Ready_jComboBox.setEnabled(false);
		gameRoomJFrame.payer5Ready_jComboBox.setVisible(false);
		gameRoomJFrame.payer5Ready_jComboBox.setEnabled(false);

		gameRoomJFrame.roomChat_jTextArea.append(String.format("[%s]님이 입장하였습니다.\n", c.getUserName()));
	}

	public void enterGameRoom(String userName, int type) {
		// 레디는 당사자만 조작가능 & 레디는 당사자가 들어올때만 보임
		// JComboBox가 빈곳이 있으면 들어간다. + 레디 창을 보여주게 한다. + 활성화 시킨다.
		gameRoomJFrame.state = true;
		if (type == 1) {// type == 1이면, 본인이 입장하는 경우이다.
			if (gameRoomJFrame.payer1_jComboBox.getSelectedItem().equals("Open")) {
				gameRoomJFrame.payer1_jComboBox.addItem(userName);
				gameRoomJFrame.payer1_combo = userName;
				gameRoomJFrame.payer1_jComboBox.removeItem("Open");
				gameRoomJFrame.payer1Ready_jComboBox.setVisible(true);
				gameRoomJFrame.payer1Ready_jComboBox.setEnabled(true);
			} else if (gameRoomJFrame.payer2_jComboBox.getSelectedItem().equals("Open")) {
				gameRoomJFrame.payer2_jComboBox.addItem(userName);
				gameRoomJFrame.payer2_combo = userName;
				gameRoomJFrame.payer2_jComboBox.removeItem("Open");
				gameRoomJFrame.payer2_jComboBox.setSelectedIndex(1);
				gameRoomJFrame.payer2Ready_jComboBox.setVisible(true);
				gameRoomJFrame.payer2Ready_jComboBox.setEnabled(true);
			} else if (gameRoomJFrame.payer3_jComboBox.getSelectedItem().equals("Open")) {
				gameRoomJFrame.payer3_jComboBox.addItem(userName);
				gameRoomJFrame.payer3_combo = userName;
				gameRoomJFrame.payer3_jComboBox.removeItem("Open");
				gameRoomJFrame.payer3_jComboBox.setSelectedIndex(1);
				gameRoomJFrame.payer3Ready_jComboBox.setVisible(true);
				gameRoomJFrame.payer3Ready_jComboBox.setEnabled(true);
			} else if (gameRoomJFrame.payer4_jComboBox.getSelectedItem().equals("Open")) {
				gameRoomJFrame.payer4_jComboBox.addItem(userName);
				gameRoomJFrame.payer4_combo = userName;
				gameRoomJFrame.payer4_jComboBox.removeItem("Open");
				gameRoomJFrame.payer4_jComboBox.setSelectedIndex(1);
				gameRoomJFrame.payer4Ready_jComboBox.setVisible(true);
				gameRoomJFrame.payer4Ready_jComboBox.setEnabled(true);
			} else if (gameRoomJFrame.payer5_jComboBox.getSelectedItem().equals("Open")) {
				gameRoomJFrame.payer5_jComboBox.addItem(userName);
				gameRoomJFrame.payer5_combo = userName;
				gameRoomJFrame.payer5_jComboBox.removeItem("Open");
				gameRoomJFrame.payer5_jComboBox.setSelectedIndex(1);
				gameRoomJFrame.payer5Ready_jComboBox.setVisible(true);
				gameRoomJFrame.payer5Ready_jComboBox.setEnabled(true);
			}
		} else if (type == 2) {// type == 2이면, 남이 입장하는 경우이다.
			if (gameRoomJFrame.payer1_jComboBox.getSelectedItem().equals("Open")) {
				gameRoomJFrame.payer1_jComboBox.addItem(userName);
				gameRoomJFrame.payer1_combo = userName;
				gameRoomJFrame.payer1_jComboBox.removeItem("Open");
				gameRoomJFrame.payer1Ready_jComboBox.setVisible(true);
			} else if (gameRoomJFrame.payer2_jComboBox.getSelectedItem().equals("Open")) {
				gameRoomJFrame.payer2_jComboBox.addItem(userName);
				gameRoomJFrame.payer2_combo = userName;
				gameRoomJFrame.payer2_jComboBox.removeItem("Open");
				gameRoomJFrame.payer2_jComboBox.setSelectedIndex(1);
				gameRoomJFrame.payer2Ready_jComboBox.setVisible(true);
			} else if (gameRoomJFrame.payer3_jComboBox.getSelectedItem().equals("Open")) {
				gameRoomJFrame.payer3_jComboBox.addItem(userName);
				gameRoomJFrame.payer3_combo = userName;
				gameRoomJFrame.payer3_jComboBox.removeItem("Open");
				gameRoomJFrame.payer3_jComboBox.setSelectedIndex(1);
				gameRoomJFrame.payer3Ready_jComboBox.setVisible(true);
			} else if (gameRoomJFrame.payer4_jComboBox.getSelectedItem().equals("Open")) {
				gameRoomJFrame.payer4_jComboBox.addItem(userName);
				gameRoomJFrame.payer4_combo = userName;
				gameRoomJFrame.payer4_jComboBox.removeItem("Open");
				gameRoomJFrame.payer4_jComboBox.setSelectedIndex(1);
				gameRoomJFrame.payer4Ready_jComboBox.setVisible(true);
			} else if (gameRoomJFrame.payer5_jComboBox.getSelectedItem().equals("Open")) {
				gameRoomJFrame.payer5_jComboBox.addItem(userName);
				gameRoomJFrame.payer5_combo = userName;
				gameRoomJFrame.payer5_jComboBox.removeItem("Open");
				gameRoomJFrame.payer5_jComboBox.setSelectedIndex(1);
				gameRoomJFrame.payer5Ready_jComboBox.setVisible(true);
			}
		}
		gameRoomJFrame.state = false;
	}

	// 게임방에서 나가려고 할때, 서버로 메시지를 보낸다.
	public void exitGameRoom() {
		try {
			// msg = [EXIT_ROOM]/userName
			writer.writeUTF("[EXIT_ROOM]/" + c.getUserName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		exitGameRoom(c.getUserName());
	}

	public void exitGameRoom(String userName) {
		// 해당 이름의 JComboBox를 찾아서 Open으로 만든다. + 레디창을 안보이게 한다.
		gameRoomJFrame.state = true;
		if (gameRoomJFrame.payer1_jComboBox.getSelectedItem().equals(userName)) {
			gameRoomJFrame.payer1_jComboBox.addItem("Open");
			gameRoomJFrame.payer1_combo = "Open";
			gameRoomJFrame.payer1_jComboBox.removeItem(userName);
			if (gameRoomJFrame.payer1Ready_jComboBox.getSelectedItem().toString().equals("Ready")) {
				gameRoomJFrame.payer1Ready_jComboBox.setSelectedItem("Not ready");
				gameRoomJFrame.payer1Ready_combo = "Not ready";
			}
			gameRoomJFrame.payer1Ready_jComboBox.setVisible(false);
			gameRoomJFrame.payer1Ready_jComboBox.setEnabled(false);
		} else if (gameRoomJFrame.payer2_jComboBox.getSelectedItem().equals(userName)) {
			gameRoomJFrame.payer2_jComboBox.addItem("Open");
			gameRoomJFrame.payer2_combo = "Open";
			gameRoomJFrame.payer2_jComboBox.removeItem(userName);
			gameRoomJFrame.payer2_jComboBox.setSelectedIndex(1);
			if (gameRoomJFrame.payer2Ready_jComboBox.getSelectedItem().toString().equals("Ready")) {
				gameRoomJFrame.payer2Ready_jComboBox.setSelectedItem("Not ready");
				gameRoomJFrame.payer2Ready_combo = "Not ready";
			}
			gameRoomJFrame.payer2Ready_jComboBox.setVisible(false);
			gameRoomJFrame.payer2Ready_jComboBox.setEnabled(false);
		} else if (gameRoomJFrame.payer3_jComboBox.getSelectedItem().equals(userName)) {
			gameRoomJFrame.payer3_jComboBox.addItem("Open");
			gameRoomJFrame.payer3_combo = "Open";
			gameRoomJFrame.payer3_jComboBox.removeItem(userName);
			gameRoomJFrame.payer3_jComboBox.setSelectedIndex(1);
			if (gameRoomJFrame.payer3Ready_jComboBox.getSelectedItem().toString().equals("Ready")) {
				gameRoomJFrame.payer3Ready_jComboBox.setSelectedItem("Not ready");
				gameRoomJFrame.payer3Ready_combo = "Not ready";
			}
			gameRoomJFrame.payer3Ready_jComboBox.setVisible(false);
			gameRoomJFrame.payer3Ready_jComboBox.setEnabled(false);
		} else if (gameRoomJFrame.payer4_jComboBox.getSelectedItem().equals(userName)) {
			gameRoomJFrame.payer4_jComboBox.addItem("Open");
			gameRoomJFrame.payer4_combo = "Open";
			gameRoomJFrame.payer4_jComboBox.removeItem(userName);
			gameRoomJFrame.payer4_jComboBox.setSelectedIndex(1);
			if (gameRoomJFrame.payer4Ready_jComboBox.getSelectedItem().toString().equals("Ready")) {
				gameRoomJFrame.payer4Ready_jComboBox.setSelectedItem("Not ready");
				gameRoomJFrame.payer4Ready_combo = "Not ready";
			}
			gameRoomJFrame.payer4Ready_jComboBox.setVisible(false);
			gameRoomJFrame.payer4Ready_jComboBox.setEnabled(false);
		} else if (gameRoomJFrame.payer5_jComboBox.getSelectedItem().equals(userName)) {
			gameRoomJFrame.payer5_jComboBox.addItem("Open");
			gameRoomJFrame.payer5_combo = "Open";
			gameRoomJFrame.payer5_jComboBox.removeItem(userName);
			gameRoomJFrame.payer5_jComboBox.setSelectedIndex(1);
			if (gameRoomJFrame.payer5Ready_jComboBox.getSelectedItem().toString().equals("Ready")) {
				gameRoomJFrame.payer5Ready_jComboBox.setSelectedItem("Not ready");
				gameRoomJFrame.payer5Ready_combo = "Not ready";
			}
			gameRoomJFrame.payer5Ready_jComboBox.setVisible(false);
			gameRoomJFrame.payer5Ready_jComboBox.setEnabled(false);
		}
		gameRoomJFrame.state = false;
	}

	// 사용자 리스트에서 사용자들을 추출하여 pList에 추가한다.
	private void nameList(String msg) {
		pList.removeAll(pList);
		StringTokenizer st = new StringTokenizer(msg, "\t");
		while (st.hasMoreElements())
			pList.add(st.nextToken());
		playersInfo();
		playersInfoComboBox();
	}

	// 게잉방에 있는 접속자의 수와 목록을 보여준다.
	private void playersInfo() {
		int count = pList.size();
		gameRoomJFrame.accListNum_jLabel.setText("" + count);
		gameRoomJFrame.accList_jTextArea.setText("");
		for (int i = 0; i < count; i++) {
			gameRoomJFrame.accList_jTextArea.append(pList.get(i) + "\n");
		}
	}

	// + 콥보박스도 싹 갱신해버린다.
	// 대기실 방에 있는 접속자의 수를 보여준다.
	private void playersInfoComboBox() {
		int count = pList.size();
		String name = null;
		for (int i = 0; i < count; i++) {
			name = pList.get(i);
			if (i == count - 1) {
				enterGameRoom(name, 1);
			} else {
				enterGameRoom(name, 2);
			}
		}
	}
	
	public String[] getPlayerList(){
		String[] nameList = new String[5];
		
		int count = pList.size();
		int i;
		int index=0;
		for (i = 1; i < count; i++) {
			if(c.getUserName().equals(pList.get(i))){
				index = i;
			}else{
				nameList[i] = pList.get(i);
			}
		}
		nameList[0] = pList.get(index);
		nameList[index] = pList.get(0);
		
		for (; i < 5; i++) {
			nameList[i] = "";
		}
		
		return nameList;
	}

	// 채팅 메시지를 입력했을 때, 서버로 메시지를 보낸다.
	public void sendTalk() {
		String msg = gameRoomJFrame.inputChat_jTextField.getText();

		// 메세지를 적당히 자른 후에 서버로 보낸다.
		if (msg.length() == 0)
			return;
		if (msg.length() >= 30)
			msg = msg.substring(0, 30);
		try {
			writer.writeUTF(String.format("[MSG]/[%s] : %s", c.getUserName(), msg));
		} catch (Exception e) {
			e.printStackTrace();
		}
		gameRoomJFrame.inputChat_jTextField.setText("");
	}

	// 메시지를 받는다.
	public void recvTalk(String msg) {
		gameRoomJFrame.roomChat_jTextArea.append(msg + "\n");
	}

	public boolean gameStart() {
		boolean isGameStart = false;
		String msg; // 수신 메시지와 파싱 메시지 처리하는 변수 선언
		String[] rmsg;
		System.out.println("게임시작");
		try {
			// 1. 서버로 보낸다. // msg = [GAME_START]
			writer.writeUTF("[GAME_START]");

			// 3. 게임시작 성공여부를 받는다.
			// writer2 [GAME_START]/SUCCESS or FAILURE
			// [GAME_START]/FAILURE/NOT_START_ALONE or NOT_READY_ALL
			msg = reader2.readUTF();
			rmsg = msg.split("/"); // '/' 구분자를 기준으로 메시지를 문자열 배열로 파싱
			if (rmsg[0].equals("[GAME_START]")) {
				if (rmsg[1].equals("SUCCESS")) {
					System.out.println("게임시작이 성공적으로 됨");
					isGameStart = true;
				} else if (rmsg[1].equals("FAILURE")) {
					if (rmsg[2].equals("NOT_START_ALONE")) {
						JOptionPane.showMessageDialog(null, "혼자서는 게임을 시작할 수 없습니다.", "게임시작 실패",
								JOptionPane.WARNING_MESSAGE);
						System.out.println("게임시작 실패. 혼자서는 게임을 시작할 수 없음");
					} else if (rmsg[2].equals("NOT_READY_ALL")) {
						JOptionPane.showMessageDialog(null, "참가자 전원이 아직 레디를 하지 않았습니다.", "게임시작 실패",
								JOptionPane.WARNING_MESSAGE);
						System.out.println("게임시작 실패. 참가자 전원이 레디를 하지 않음");
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return isGameStart;
	}

	public void ready(int index, String isReady) {
		try {
			// msg = [READY]/index/Ready or Not ready
			System.out.println(c.getUserName() + " : ready : "+ isReady);
			writer.writeUTF(String.format("[잉여잉여함]"));
			writer.writeUTF(String.format("[READY]/%d/%s", index, isReady));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void place(int index, String isOpen) {
		try {
			// msg = [PLACE]/index/Open or Close
			writer.writeUTF(String.format("[PLACE]/%d/%s", index, isOpen));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void fire(String userName) {
		try {
			// msg = [FIRED]/userName
			writer.writeUTF("[FIRED]/" + userName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
