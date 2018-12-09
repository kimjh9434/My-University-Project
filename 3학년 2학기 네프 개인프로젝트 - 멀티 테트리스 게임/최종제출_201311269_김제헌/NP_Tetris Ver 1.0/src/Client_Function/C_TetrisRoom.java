package Client_Function;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JOptionPane;

import Client_GUI_Form.GUI_MultiTetrisJFrame;
import Client_GUI_Form.GUI_MultiTetrisJFrame___;

public class C_TetrisRoom {

	private Socket socket; // 주 소켓
	private Socket socket2; // 임시 소켓

	private DataInputStream reader; // 소켓의 입력 스트림
	private DataInputStream reader2; // 소켓의 입력 스트림
	private DataOutputStream writer; // 소켓의 출력 스트림

	private C_Client c;

	private GUI_MultiTetrisJFrame multiTetrisJFrame;

	public C_TetrisRoom(Socket socket, Socket socket2, C_Client c) {
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
	}

	public boolean controlStart() {
		boolean isRuuning = true;
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		System.out.println("테트리스 게임 시작  이후의  흐름제어");
		// 수신 메시지와 파싱 메시지 처리하는 변수 선언
		String msg = null;
		String[] rmsg;

		while (isRuuning) {
			try {
				System.out.println(c.getUserName() + " | C_TetrisRoom While 대기중");
				msg = reader.readUTF();
				System.out.println(c.getUserName() + " | C_TetrisRoom While : 서버로 부터 받은 값 : " + msg);
				rmsg = msg.split("/"); // '/' 구분자를 기준으로 메시지를 문자열 배열로 파싱

				// 테트리스 명령어 : msg = [TETRIS_CMD]/userName/keyCode
				if (rmsg[0].equals("[TETRIS_CMD]")) { // 게임이 시작함
					// => 해당 userName에 따라 keyCode 내용을 반영한다.
					multiTetrisJFrame.tetrisPanel.keyPressed(rmsg[1], Integer.parseInt(rmsg[2]));
				}

				// 게임오버[대기] : msg = [GAME_OVER]/userName
				else if (rmsg[0].equals("[GAME_OVER]")) { // 해당 클라이언트가 게임오버 되었음을 의미함.
					// => 해당 클라이언트가 게임오버되었다고 표시함. [패널을 바꾸던, 텍스트로 표시하던 둘다던]
					// multiTetrisJFrame.tetrisPanel.otherGameOver(rmsg[1]); // 이건 알아서 바뀜
					multiTetrisJFrame.tetrisChat_jTextArea.append(rmsg[1] + " 님이 게임오버됐습니다.\n");
				}

				// 게임 종료 : msg = [GAME_END]/winnerName : 게임이 종료되었음을 의미함, 승자의 이름도 받음
				else if (rmsg[0].equals("[GAME_END]")) {
					// => 해당 클라이언트가 이겼다고 표시함. 누가 이겼다, 당신은 N등이다 등을 표시 이건 보류
					JOptionPane.showMessageDialog(null, rmsg[1] + " 님이 이겼습니다." , "게임 종료", JOptionPane.WARNING_MESSAGE);
					multiTetrisJFrame.tetrisChat_jTextArea.append(rmsg[1] + " 님이 이겼습니다.\n");
				}

				// msg = [EXIT_ROOM]
				else if (rmsg[0].equals("[EXIT_ROOM]")) { // 게임방을 나감.
					// 클라이언트의 대기실 방 번호0을 지정하고,
					c.setroomNumber(0); // 방 번호 지정
					// 클라이언트의 방 이름[대기실]을 지정한 후,
					c.setRoomName("대기실");
					// - 이후, 게임방 제어 C_WaitingRoom.controlStart() 에서 통제함. [사실상 반복문을 빠져나오면 됨]
					isRuuning = false;
				}
				
				// 게임 포기 : msg = [GIVE_UP]/userName 
				else if (rmsg[0].equals("[GIVE_UP]")) {
					// 다른 클라이언트인 userName가 게임을 포기하고 나갔음 의미함.
					// 메시지를 화면에 보여준다.
					multiTetrisJFrame.tetrisChat_jTextArea.append(rmsg[1] + " 님이 게임을 포기하고 나갔습니다.\n");
					multiTetrisJFrame.tetrisChat_jTextArea.append(rmsg[1] + " 님을 게임오버처리합니다.\n");
					// 해당 클라이언트를 게임오버 처리해주고 멈춰줘야함..
					multiTetrisJFrame.tetrisPanel.otherGameOver(rmsg[1]);
				}
				
				// 방 퇴장 : msg = [EXIT]/userName. 
				else if (rmsg[0].equals("[EXIT]")) { 
					// 다른 클라이언트인 userName가 게임방에서 나갔음을 의미함.
					// 메시지를 화면에 보여준다.
					multiTetrisJFrame.tetrisChat_jTextArea.append(rmsg[1] + " 님이 방을 나갔습니다.\n");
				}
				
				// 접속 종료 : msg = [DISCONNECT]/userName
				else if (rmsg[0].equals("[DISCONNECT]")) { // 손님 접속 종료
					// pList.remove(rmsg[1]);
					// playersInfo();
					multiTetrisJFrame.tetrisPanel.otherGameOver(rmsg[1]);
					multiTetrisJFrame.tetrisChat_jTextArea.append(String.format("[%s]님의 접속이 끊겼습니다.\n", rmsg[1]));
				}

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
		System.out.println(c.getUserName() + " | C_TetrisRoom.controlStart() 빠져나옴");
		return isRuuning;
	}

	public void enterTetrisRoom(GUI_MultiTetrisJFrame multiTetrisJFrame) {
		this.multiTetrisJFrame = multiTetrisJFrame;
	}

	// 해당 유저가 테트리스 명령어를 보냈을 때,
	public void tetrisCMD(int keyCode) {
		try {
			// msg = [TETRIS_CMD]/userName/keyCode
			writer.writeUTF(String.format("[TETRIS_CMD]/%s/%d", c.getUserName(), keyCode));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 해당 유저가 게임오버를 당했을 때,
	public void tetrisGameOver() {
		try {
			// msg = [GAME_OVER]/userName
			writer.writeUTF(String.format("[GAME_OVER]/%s", c.getUserName()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 게임방에서 나가려고 할때, 서버로 메시지를 보낸다.
	public void exitRoom() {
		try {
			// msg = [EXIT_ROOM]/userName
			writer.writeUTF("[EXIT_ROOM]/" + c.getUserName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
