package Server_Function;

import java.util.Vector;

//게임 내의 방을 관리하는 클래스
public class S_Room {
	private Vector<S_Client> cList; // 해당 방에 속한 클라이언트들 리스트

	private int roomNumber; // 방 번호
	private String roomName;// 방 이름
	private String roomPwd; // 방 비밀번호
	private String roomOwnerName; // 방장 이름
	private int roomLimitEnterCount; // 방 제한입장인원수
	private boolean isRoomGameStart;

	// 새로운 방의 생성자. 방 번호, 방 이름, 방 비밀번호, 방장 이름, 방의 제한 인원 수를 받는다.
	S_Room(int roomNumber, String roomName, String roomPwd, String roomOwnerName, int roomLimitEnterCount) {
		this.cList = new Vector<S_Client>();
		this.roomNumber = roomNumber;	
		this.roomName = roomName;
		this.roomPwd = roomPwd;
		this.roomOwnerName = roomOwnerName;
		this.roomLimitEnterCount = roomLimitEnterCount;
		this.isRoomGameStart = false;
	}

	// i번째 스레드와 연결된 클라이언트에게 메시지를 전송한다.
	void sendTo(int i, String msg) {
		cList.get(i).sendTo(msg);
	}

	// 해당 방에 msg를 전송한다.
	void sendToRoom(String msg) {
		int size = cList.size();
		for (int i = 0; i < size; i++)
			sendTo(i, msg);
	}

	// 해당 방에 있는 c와는 다른 사용자들에게 msg를 전달한다.
	void sendToRoomOthers(S_Client c, String msg) {
		int size = cList.size();
		for (int i = 0; i < size; i++) {
			if (cList.get(i) != c) {
				sendTo(i, msg);
			}
		}
	}
	
	public S_Client getClient(String cliName) { // 해당 방에서 이름이 cliName인 클라이언트를 반환한다.
		S_Client client = null;
		int clientSize = cList.size();// 전체 클라이언트를 다 뒤진다.
		for (int i = 0; i < clientSize; i++) {
			if (((S_Client) cList.get(i)).getUserName().equals(cliName)) {
				client = (S_Client) cList.get(i);
				break;
			}
		}
		return client;
	}

	// 게임을 시작할 준비가 되었는가를 반환한다.
	// roomNumber 방안에 들어온 사용자 모두 준비된 상태이면 true를 반환한다.
	synchronized boolean isReadyAll() {
		System.out.println("isReadyAll()");
		boolean isReadyAll = false;
		int readyCount = 0;
		int size = cList.size();
		for (int i = 0; i < size; i++){
			System.out.println(String.format("cList.get(%d).isReady() = %b", i, cList.get(i).isReady()));
			if (cList.get(i).isReady()){
				readyCount++;
			}
		}
		System.out.println("readyCount : "+readyCount);
		if (readyCount == size)
			isReadyAll = true;
		return isReadyAll;
	}
	
	// 모두 게임오버가 되었는가를 반환한다.
	// roomNumber 방안에 들어온 사용자 모두 게임오버가 되었으면, true를 반환한다.
	synchronized boolean isGameOverAll() {
		System.out.println("isGameOverAll()");
		boolean isGameOverAll = false;
		int gameOverCount = 0;
		int size = cList.size();
		for (int i = 0; i < size; i++){
			System.out.println("cList.get(i).getUserName() : " + cList.get(i).getUserName());
			System.out.println(String.format("cList.get(%d).isGameOver() = %b", i, cList.get(i).isGameOver()));
			if (cList.get(i).isGameOver()){
				gameOverCount++;
			}
		}
		System.out.println("gameOverCount : "+gameOverCount);
		if (gameOverCount == size)
			isGameOverAll = true;
		return isGameOverAll;
	}

	// roomNumber방에 있는 사용자들의 이름을 반환한다.
	String getNamesInRoom() {
		StringBuffer sb = new StringBuffer("[PLAYERS]/ ");
		int size = cList.size();
		for (int i = 0; i < size; i++)
			sb.append(cList.get(i).getUserName() + "\t");
		return sb.toString();
	}

	// 해당 방에 c가 있는지 확인한다.
	public boolean isInRoom(S_Client c) {
		boolean isInRoom = false;
		int size = cList.size();
		for (int i = 0; i < size; i++) {
			if (cList.get(i) == c) {
				isInRoom = true;
				break;
			}
		}
		return isInRoom;
	}

	///////////////////////////////////////////////////////////////
	// 여기서부터는 단순한 추가 및 제거, Get, Set 메소드들
	public void addClient(S_Client c) { // 방에 클라이언트를 추가한다.
		if (!isInRoom(c)) {
			cList.add(c);
		}
	}

	public void removeClient(S_Client c) { // 방에서 클라이언트를 제거한다.
	if (isInRoom(c)) {
			cList.remove(c);
		}
	}

	public int getClientCountInRoom() { // 방안에 있는 클라이언트들의 수
		return cList.size();
	}

	public Vector<S_Client> getcList() {
		return cList;
	}

	public void setcList(Vector<S_Client> cList) {
		this.cList = cList;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getRoomOwnerName() {
		return roomOwnerName;
	}

	public void setRoomOwnerName(String roomOwnerName) {
		this.roomOwnerName = roomOwnerName;
	}

	public int getroomNumber() {
		return roomNumber;
	}

	public void setroomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRoomPwd() {
		return roomPwd;
	}

	public void setRoomPwd(String roomPwd) {
		this.roomPwd = roomPwd;
	}
	
	public boolean isRoomGameStart() {
		return isRoomGameStart;
	}

	public void setRoomGameStart(boolean isRoomGameStart) {
		this.isRoomGameStart = isRoomGameStart;
	}

	public int getRoomLimitEnterCount() {
		return roomLimitEnterCount;
	}
	
	public void setRoomLimitEnterCount(int roomLimitEnterCount) {
		this.roomLimitEnterCount = roomLimitEnterCount;
	}

	public void plusRoomLimitEnterCount() {
		this.roomLimitEnterCount++;
	}
	
	public void minusRoomLimitEnterCount() {
		this.roomLimitEnterCount--;
	}
}