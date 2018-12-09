package Client_Function;

public class C_Room {
	private int roomNumber;
	private String roomName;
	private int roomLimitEnterCount; // 방 제한입장인원수

	public C_Room(int roomNumber, String roomName, int roomLimitEnterCount) {
		this.roomNumber = roomNumber;
		this.roomName = roomName;
		this.roomLimitEnterCount = roomLimitEnterCount;
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
	
	public int getRoomLimitEnterCount() {
		return roomLimitEnterCount;
	}

	public void setRoomLimitEnterCount(int roomLimitEnterCount) {
		this.roomLimitEnterCount = roomLimitEnterCount;
	}
}
