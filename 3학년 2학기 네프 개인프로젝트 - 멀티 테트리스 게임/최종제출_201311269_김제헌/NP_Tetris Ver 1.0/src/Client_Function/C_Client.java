package Client_Function;

public class C_Client {
	private int roomNumber;       // 사용자가 방 번호
	private String userName;      // 사용자 이름
	private String roomName;      // 방 이름
	
	public C_Client(){
		this.roomNumber = -1;
		this.userName = "";
		this.roomName = "";
	}
	
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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
}
