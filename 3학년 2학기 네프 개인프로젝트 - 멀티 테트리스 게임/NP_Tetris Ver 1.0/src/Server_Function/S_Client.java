package Server_Function;

import java.io.DataOutputStream;
import java.net.Socket;

public class S_Client {

	private Socket socket; // 소켓
	private int roomNumber; // 방 번호
	private String userName; // 사용자 이름

	// 게임 준비 여부, true이면 게임을 시작할 준비가 되었음을 의미한다.
	private boolean ready;
	private boolean gameOver;

	// 게임방에 아직도 있는지 여부
	// 대기실에서는 False였다가, 게임방으로 들어가게되면 True로 바뀐다.
	// 하지만, 강퇴를 당했다던지, 방이 터졌을 경우 False로 되며 대기실로 나오게 된다.
	private boolean isInGameRoom;

	public S_Client(Socket socket, int roomNumber, String userName) {
		this.socket = socket;
		this.roomNumber = roomNumber;
		this.userName = userName;
		this.ready = false;
		this.gameOver = false;
		this.isInGameRoom = false;
	}

	// 해당 클라이언트에게 메시지를 전송한다.
	void sendTo(String msg) {
		try {
			new DataOutputStream(socket.getOutputStream()).writeUTF(msg);
		} catch (Exception e) {
		}
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isReady() {
		return ready;
	}

	public void setReady(boolean ready) {
		this.ready = ready;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public boolean isInGameRoom() {
		return isInGameRoom;
	}

	public void setInGameRoom(boolean isInGameRoom) {
		this.isInGameRoom = isInGameRoom;
	}

}
