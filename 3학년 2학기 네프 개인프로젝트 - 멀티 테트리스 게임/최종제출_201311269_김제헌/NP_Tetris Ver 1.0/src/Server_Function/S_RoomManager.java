package Server_Function;

import java.util.Vector;

//게임 내의 방들을 모두 관리하는 클래스 && 메시지를 전달하는 클래스
public class S_RoomManager {

	static private int roomNumberCount;// 방번호는 각 방의 고유 번호로써, 중복되면 안된다.

	private Vector<S_Room> rList; // 전체 방 리스트
	private Vector<S_Client> totalCList; // 접속중인 전체 클라이언트 리스트 [중복 로그인 방지때 사용]
	private Vector<S_Client> quickMatch_waitingCList; // 빠른대전 대기 클라이언트 리스트

	// 단순한 생성자.
	S_RoomManager() {
		roomNumberCount = 0;
		this.rList = new Vector<S_Room>();
		this.totalCList = new Vector<S_Client>();
		this.quickMatch_waitingCList = new Vector<S_Client>();
	}

	// 방을 추가한다.
	public void addRoom(S_Room room) {
		rList.add(room);
		roomNumberCount++; // 증가만 한다.
	}

	// 방을 제거한다.
	public void removeRoom(S_Room room) {
		rList.remove(room);
	}

	// 클라이언트를 추가한다.
	public void addClient(S_Client client) {
		totalCList.add(client);
	}

	// 클라이언트를 제거한다.
	public void removeClient(S_Client client) {
		totalCList.remove(client);
	}

	// 방번호가 roomNumber인 방을 반환한다.
	public S_Room getRoom(int roomNumber) {
		S_Room room = null;
		int roomSize = rList.size();// 전체 방을 다 뒤진다.
		for (int i = 0; i < roomSize; i++) {
			if (((S_Room) rList.get(i)).getroomNumber() == roomNumber) {
				room = (S_Room) rList.get(i);
				break;
			}
		}
		return room;
	}

	///////////////////////////////////////////////////////////

	// 클라이언트의 연결이 종료되었을때, 
	public void disconnectServer(S_Client c) {
		// 사용자를 전체 접속자 목록 및 해당방에서 제외시킨다.
		removeClient(c);
		exitRoom(c);
		// 해당방의 자신을 제외한 다른 사람들에게 본인의 연결이 끊어졌음을 알린다.
		getRoom(c.getRoomNumber()).sendToRoom(String.format("[DISCONNECT]/%s", c.getUserName()));
	}

	///////////////////////////////////////////////////////////

	// 새로운 방을 만들고, 해당 방으로 들어가기
	public void createNewRoomAndEnter(S_Client c, String roomName, String roomPwd) {
		// 우선, 해당 사용자를 대기실에서 나가게 한다.
		getRoom(0).removeClient(c);

		// 기존 대기실의 사람들에게 해당 사용자의 퇴장을 알린다.
		getRoom(0).sendToRoomOthers(c, String.format("[EXIT]/%s", c.getUserName()));

		// 새로운 테트리스 게임방을 만든다. 방번호는 roomNumberCount
		int roomNumber = roomNumberCount;
		// S_Room(roomNumber, roomName, roomPwd, roomOwnerName,
		// roomLimitEnterCount)
		addRoom(new S_Room(roomNumber, roomName, roomPwd, c.getUserName(), 5));

		// 해당 사용자의 방번호를 roomNumber로 설정하고,
		c.setRoomNumber(roomNumber);

		// roomNumber번 게임방에 해당 사용자를 넣고,
		getRoom(roomNumber).addClient(c);
		c.setInGameRoom(true);

		// 사용자에게 방에 입장하였음을 알린다.
		// msg =[JOIN_ROOM]/roomNumber/roomName/roomOwnerName
		c.sendTo(String.format("[JOIN_ROOM]/%d/%s/%s", roomNumber, getRoom(roomNumber).getRoomName(),
				getRoom(roomNumber).getRoomOwnerName()));

		// 사용자에게 roomNumber번 게임방에 있는 사람들의 이름 리스트를 전송한다. [동일하게 본인밖에 없지만...]
		// msg = [PLAYERS]/userName 목록 ....
		c.sendTo(getRoom(roomNumber).getNamesInRoom());

		// 대기실에 있는 다른 사람들에게 새로운 방이 추가되었음을 알린다.
		// msg = [CREATE_ROOM]/roomNumber/roomName
		getRoom(0).sendToRoom(String.format("[CREATE_ROOM]/%d/%s", roomNumber, roomName));
		System.out.println(String.format("[CREATE_ROOM]/%d/%s", roomNumber, roomName));

	}

	// roomNumber 방에 입장한다.
	public void enterRoom(int roomNumber, S_Client c) {
		getRoom(roomNumber).addClient(c);
	}

	// 아예 맨 처음에 대기실로 사용자가 들어가는 경우,
	public void enterWaitingRoom(S_Client c) {
		System.out.println(c.getUserName() + " 대기실 입장  EnterWaitingRoom");
		// 해당 사용자의 방번호를 0으로 설정하고,
		c.setRoomNumber(0);

		// 대기실에 해당 사용자를 넣고,
		getRoom(0).addClient(c);

		// 사용자에게 메시지를 그대로 전송하여 해당 사용자가 방에 입장하였음을 알린다.
		// msg =[JOIN_ROOM]/roomNumber/roomName/roomOwnerName
		c.sendTo(String.format("[JOIN_ROOM]/%d/%s/%s", 0, getRoom(0).getRoomName(), getRoom(0).getRoomOwnerName()));

		// writer2.writeUTF("[JOIN_ROOM]/SUCCESS");

		// 대기실에 있는 다른 사람들에게 해당 사용자의 입장을 알린다. // msg = [ENTER]/userName
		getRoom(0).sendToRoom(String.format("[ENTER]/%s", c.getUserName()));

		// 사용자에게 대기실에 있는 사람들의 이름 리스트를 전송한다.
		// : [PLAYERS]/userName 목록 ....
		c.sendTo(getRoom(0).getNamesInRoom());
	}

	// 아예 대기실에서 게임방으로 클라이언트가 들어가는 경우,
	public void enterGameRoom(int roomNumber, S_Client c) {
		System.out.println(c.getUserName() + ". " + roomNumber + "번 게임방 입장  EnterGameRoom");
		// 우선, 해당 사용자를 대기실에서 나가게 한다.
		getRoom(0).removeClient(c);

		// 기존 대기실의 사람들에게 해당 사용자의 퇴장을 알린다.
		getRoom(0).sendToRoom(String.format("[EXIT]/%s", c.getUserName()));

		// 해당 사용자의 방번호를 roomNumber로 설정하고,
		c.setRoomNumber(roomNumber);

		// roomNumber번 게임방에 해당 사용자를 넣고,
		getRoom(roomNumber).addClient(c);
		c.setInGameRoom(true);

		// 사용자에게 방에 입장하였음을 알린다.
		// msg =[JOIN_ROOM]/roomNumber/roomName/roomOwnerName
		c.sendTo(String.format("[JOIN_ROOM]/%d/%s/%s", roomNumber, getRoom(roomNumber).getRoomName(),
				getRoom(roomNumber).getRoomOwnerName()));
		// writer2.writeUTF("[JOIN_ROOM]/SUCCESS");

		// roomNumber번 게임방에 있는 다른 사람들에게 해당 사용자의 입장을 알린다.
		// msg =[ENTER]/userName
		getRoom(roomNumber).sendToRoomOthers(c, String.format("[ENTER]/%s", c.getUserName()));

		// 사용자에게 roomNumber번 게임방에 있는 사람들의 이름 리스트를 전송한다.
		// : [PLAYERS]/userName 목록 ....
		c.sendTo(getRoom(roomNumber).getNamesInRoom());
	}

	// roomNumber 방에서 나간다.
	public void exitRoom(S_Client c) {
		getRoom(c.getRoomNumber()).removeClient(c);
	}

	// 대기실에서 나간다. [=접속을 종료한다]
	public void exitRoom(S_Client c, int roomNumber) {
		// 해당 사용자를 전체 클라이언트 목록 및 대기실에서 제외시킨다.
		removeClient(c);
		getRoom(roomNumber).removeClient(c);

		// 대기방의 자신을 제외한 다른 사람들에게 본인의 퇴장을 알린다.
		getRoom(roomNumber).sendToRoomOthers(c, String.format("[DISCONNECT]/%s", c.getUserName()));
	}

	// 해당 사용자가 roomNumber 게임방에서 나간다.
	public void exitGameRoom(S_Client c) {
		int roomNumber = c.getRoomNumber();
		// -> if 방장이 아닐 경우,
		if (!getRoom(roomNumber).getRoomOwnerName().equals(c.getUserName())) {
			System.out.println(c.getUserName() + ". " + roomNumber + "번 게임방 퇴장  ExitGameRoom");
			// 우선, 해당 사용자를 게임방에서 나가게 한다.
			getRoom(roomNumber).removeClient(c);
			c.setInGameRoom(false);

			// 기존 게임방의 사람들에게 해당 사용자의 퇴장을 알린다.
			getRoom(roomNumber).sendToRoom(String.format("[EXIT]/%s", c.getUserName()));

			// 해당 사용자의 방번호를 0으로 설정하고,
			c.setRoomNumber(0);

			// 대기실에 해당 사용자를 넣고,
			getRoom(0).addClient(c);

			// 사용자에게 방에서 나가서 대기실로 감을 알린다. [EXIT_ROOM]
			c.sendTo("[EXIT_ROOM]");

			// 대기실에 있는 다른 사람들에게 해당 사용자의 입장을 알린다.
			// msg = [ENTER]/userName
			getRoom(0).sendToRoom(String.format("[ENTER]/%s", c.getUserName()));

			// 사용자에게 대기실에 있는 사람들의 이름 리스트를 전송한다.
			// : [PLAYERS]/userName 목록 ....
			c.sendTo(getRoom(0).getNamesInRoom());
		} else { // -> else 방장일 경우,
			// 방이 터져야 함. 방이 터지기 전에, 
			// 방장에게는 방에서 나갔음을 알리고, 
			c.sendTo("[EXIT_ROOM]");
			
			// 방에 속해있는 나머지 사람들에게 방이 터졌음을 알린다.
			getRoom(roomNumber).sendToRoomOthers(c, "[ROOM_DESTROYED]");

			// 방이 터지기 전에, 해당 방내의 모든 사람들을 방에서 나가야 한다.
			S_Client tempC;
			int size = getRoom(roomNumber).getcList().size();
			for (int i = 0; i < size; i++) {
				// 기존 게임방에 속해있는 각각의 클라이언트들에 대해서 수행한다.
				tempC = getRoom(roomNumber).getcList().get(i);
				// 사용자를 게임방에서 나가게 한다.
				getRoom(roomNumber).removeClient(tempC);
				tempC.setInGameRoom(false);

				// 사용자들의 대기실 방 번호를 0으로 지정한다.
				tempC.setRoomNumber(0);

				// 대기실에 사용자를 넣는다.
				getRoom(0).addClient(tempC);

				// 대기실에 있는 다른 사람들에게 해당 사용자의 입장을 알린다.
				// msg = [ENTER]/userName
				getRoom(0).sendToRoom(String.format("[ENTER]/%s", tempC.getUserName()));

				// 사용자에게 대기실에 있는 사람들의 이름 리스트를 전송한다.
				// : [PLAYERS]/userName 목록 ....
				tempC.sendTo(getRoom(0).getNamesInRoom());
			}
			// 방이 터진다. [없어진다]
			System.out.println("방을 없앤다.");
			removeRoom(getRoom(roomNumber));
		}
	}

	// 해당 사용자가 현재 시작하고 있는 roomNumber인 테트리스 게임방에서 나간다.
	public void exitTetrisRoom(S_Client c) {
		int roomNumber = c.getRoomNumber();
		System.out.println(c.getUserName() + ". " + roomNumber + "번 게임방 퇴장  exitTetrisRoom()");
		// 우선, 해당 사용자를 게임방에서 나가게 한다.
		getRoom(roomNumber).removeClient(c);
		c.setInGameRoom(false);

		if (!c.isGameOver()) {// 해당 클라이언트가 아직 게임오버 당하지 않았을 경우,
			System.out.println("해당 클라이언트가 아직 게임오버 당하지 않았을 경우,");
			// 해당 클라이언트를 게임오버 처리시킨다.
			c.setGameOver(true);

			// ‘게임오버[대기] & 게임 종료’에서 수행한 코드와 유사한 내용을 진행한다.
			// 다만 여기서는 최후의 1인일 경우, 그냥 그대로 방이 터져야 하고,
			// 그렇지 않을 경우, 그대로 통보해준다는 자이점이 있다.
			if (getRoom(roomNumber).isGameOverAll()) {
				// 만약, 해당 방의 모든 클라이언트들이 게임오버 당했다면,
				// 방이 터진다. [없어진다]
				removeRoom(getRoom(roomNumber));// 어차피 남은 사람도 없다.
			} else {
				// 아직 플레이 하고 있는 사람이 있을 경우,
				// 기존 게임방의 사람들에게 해당 사용자의 퇴장[게임을 포기하고 나감]을 알린다.
				getRoom(roomNumber).sendToRoom(String.format("[GIVE_UP]/%s", c.getUserName()));
			}
			c.setGameOver(false);
		} else {// 해당 클라이언트가 이미 게임오버 당했었다면,
			System.out.println("해당 클라이언트가 이미 게임오버 당했었다면,");
			// 게임방에 해당 클라이언트가 나감을 알린다.
			getRoom(roomNumber).sendToRoomOthers(c, "[EXIT]/" + c.getUserName());
			c.setGameOver(false);
		}

		// 해당 사용자의 방번호를 0으로 설정하고,
		c.setRoomNumber(0);

		// 대기실에 해당 사용자를 넣고,
		getRoom(0).addClient(c);

		// 사용자에게 방에서 나가서 대기실로 감을 알린다. [EXIT_ROOM]
		c.sendTo("[EXIT_ROOM]");

		// 대기실에 있는 다른 사람들에게 해당 사용자의 입장을 알린다.
		// msg = [ENTER]/userName
		getRoom(0).sendToRoom(String.format("[ENTER]/%s", c.getUserName()));

		// 사용자에게 대기실에 있는 사람들의 이름 리스트를 전송한다.
		// : [PLAYERS]/userName 목록 ....
		c.sendTo(getRoom(0).getNamesInRoom());
	}

	///////////////////////////////////////////////////////////

	// 해당 유저를 강퇴한다.
	public void firedUser(int roomNumber, String firedUserName) {
		// userName가 있던 방의 사람들에게 userName 클라이언트가 강퇴되었음을 전한다.
		// msg = [FIRED]/userName
		getRoom(roomNumber).sendToRoom(String.format("[FIRED]/%s", firedUserName));

		// userName인 클라이언트를 얻고,
		S_Client tempC = getRoom(roomNumber).getClient(firedUserName);

		// 우선, 강퇴당한, userName인 해당 사용자를 게임방에서 나가게 한다.
		getRoom(roomNumber).removeClient(tempC);
		tempC.setInGameRoom(false);

		// 해당 사용자의 방번호를 0으로 설정하고,
		tempC.setRoomNumber(0);

		// 대기실에 해당 사용자를 넣고,
		getRoom(0).addClient(tempC);

		// 대기실에 있는 다른 사람들에게 해당 사용자의 입장을 알린다.
		// msg = [ENTER]/userName
		getRoom(0).sendToRoom(String.format("[ENTER]/%s", tempC.getUserName()));

		// 사용자에게 대기실에 있는 사람들의 이름 리스트를 전송한다.
		// : [PLAYERS]/userName 목록 ....
		tempC.sendTo(getRoom(0).getNamesInRoom());
	}
	
	// 테트리스를 시작한다.
	public void tetrisStart(S_Client c){
		int roomNumber = c.getRoomNumber();
		sendToRoomMSG(roomNumber, "[GAME_START]");
		getRoom(roomNumber).setRoomGameStart(true);
	}
	
	// 게임 오버된 gameOverUserName의 이름을 받아서 처리한다.
	public void gameOver(S_Client c, String gameOverUserName) {
			int roomNumber = c.getRoomNumber();
			// userName인 클라이언트를 얻고,
			S_Client tempC = getRoom(roomNumber).getClient(gameOverUserName);

			// 해당 사용자를 게임오버 처리시킨다.
			tempC.setGameOver(true);
			
			// 방의  사람들에게 메시지를 알려준다.
			// sendToRoom [GAME_OVER]/userName
			getRoom(roomNumber).sendToRoom("[GAME_OVER]/" + tempC.getUserName());
			
			// 만약, 해당 방의 모든 클라이언트들이 게임오버 당했다면,
			if (getRoom(roomNumber).isGameOverAll()) {
				// 승자의 이름과 함께 게임이 종료되었다고 알림 . 이경우, 맨 마지막에 게임오버 당한 사람이 승자이다.
				// sendToRoom [GAME_END]/winnerName
				getRoom(roomNumber).sendToRoom("[GAME_END]/" + tempC.getUserName());
			}
		}


	///////////////////////////////////////////////////////////

	// 해당 방번호에 msg 메시지를 전송하는 경우,
	public void sendToRoomMSG(int roomNumber, String msg) {
		getRoom(roomNumber).sendToRoom(msg);
	}

	// 해당 방번호에 msg 메시지를 전송하는 경우,
	public void sendToRoomOtherMSG(S_Client c, String msg) {
		getRoom(c.getRoomNumber()).sendToRoomOthers(c, msg);
	}

	///////////////////////////////////////////////////////////

	// 방이 찼는지 알아본다.
	synchronized boolean isFull(int roomNumber) { 
		boolean isFull = false;
		if (roomNumber != 0) { // 대기실은 차지 않는다. 대기실이 아닐경우에 검사를 시작한다.
			// 다른 방은 해당 방의 제한인원수 이상 입장할 수 없다.
			S_Room room = getRoom(roomNumber);
			if (room.getClientCountInRoom() == room.getRoomLimitEnterCount()) {
				isFull = true;// 현재 방의 인원수가 방의 제한인원수랑 같을 경우, 방이 꽉차있다는 의미.
			}
		}
		return isFull;
	}

	// 방의 비밀번호가 맞아서 들어갈수 있는지 알아본다.
	boolean isEnter(int roomNumber, String roomPwd) {
		boolean isEnter = false;
		S_Room room = getRoom(roomNumber);
		if (room.getRoomPwd().equals(roomPwd)) {
			isEnter = true;
		}
		System.out.println("isEnter()");
		System.out.println("" + roomNumber + "번방의 비밀번호 : " + room.getRoomPwd());
		System.out.println("입력받은 비밀번호 : " + roomPwd);
		return isEnter;
	}

	///////////////////////////////////////////////////////////

	// 전체 접속자 중에 userName가 이미 접속해 있는지 확인한다. [중복 접속 방지때 사용]
	public boolean isAccept(String userName) {
		boolean isAccept = false;
		int size = totalCList.size();
		for (int i = 0; i < size; i++) {
			if (totalCList.get(i).getUserName().equals(userName)) {
				isAccept = true;
				break;
			}
		}
		return isAccept;
	}

	// 만약 강제 종료 등으로 서버가 다운됬을 경우
	public void serverDown() {
		int size = totalCList.size();
		// 접속해있는 모든 클라이언트들에게 서버가 다운됬음을 알린다.
		for (int i = 0; i < size; i++) {
			totalCList.get(i).sendTo("[QUIT]");
		}
	}

	///////////////////////////////////////////////////////////

	// roomNumber방에 있는 사용자들의 이름을 반환한다.
	String getRoomNames() {
		StringBuffer sb = new StringBuffer("[ROOMS]/ ");
		int size = rList.size();
		for (int i = 1; i < size; i++)
			if (!rList.get(i).isRoomGameStart())
				sb.append(rList.get(i).getRoomNumber() + "," + rList.get(i).getRoomName() + "\t");

		return sb.toString();
	}

	// 방의 개수를 반환한다.
	public int getroomNumberCount() {
		return roomNumberCount;
	}

	// roomNumber 방에 속한 사람들의 수를 반환한다.
	public int getClientCountInRoom(int roomNumber) {
		return getRoom(roomNumber).getClientCountInRoom();
	}

	// 빠른대전 리스트에 클라이언트 추가
	public boolean addClient_QuickMatch_waitingCList(S_Client c) {
		boolean isStart = false;
		// 일단 대기 리스트에 클라이언트를 추가하고,
		quickMatch_waitingCList.add(c);

//		 if(quickMatch_waitingCList.size()==5){// 5명이 되었다면,
		if (quickMatch_waitingCList.size() == 2) {// 2명이 되었다면,
			// if (quickMatch_waitingCList.size() == 1) {// 1명이 되었다면,
			isStart = true;

			// 새로운 테트리스 게임방을 만든다. 방번호는 roomNumberCount
			int roomNumber = roomNumberCount;
			// S_Room(roomNumber, roomName, roomPwd,
			// roomOwnerName,roomLimitEnterCount)
			S_Room room = new S_Room(roomNumber, "빠대방", "", "", 5);
			addRoom(room);
			room.setRoomGameStart(true);

			S_Client tempC;
			String nameList = "";
			// 해당 사람들 5명을 게임방에 집어넣는다.
//			 for (int i = 0; i < 5; i++) {
			for (int i = 0; i < 2; i++) {
				// for (int i = 0; i < 1; i++) {
				// 임시 사용자를 얻는다.
				tempC = quickMatch_waitingCList.firstElement();
				quickMatch_waitingCList.remove(0);
				nameList += tempC.getUserName() + "\t";
				// 우선, 해당 사용자를 대기실에서 나가게 한다.
				getRoom(0).removeClient(tempC);

				// 기존 대기실의 사람들에게 해당 사용자의 퇴장을 알린다.
				getRoom(0).sendToRoomOthers(tempC, String.format("[EXIT]/%s", tempC.getUserName()));

				// 해당 사용자의 방번호를 roomNumber로 설정하고,
				tempC.setRoomNumber(roomNumber);

				// roomNumber번 게임방에 해당 사용자를 넣는다
				room.addClient(tempC);
				c.setInGameRoom(true);
			}
			// 플레이어들에게 게임이 시작했음을 알려준다.
			// 빠른대전 : msg = [QUICK_MATCH]/Start or Waiting
			// Start/nameList...
			room.sendToRoom("[QUICK_MATCH]/Start/" + nameList);
		} else

		{// 5명이 안되었다면,
			// 기다리라고 신호를 보낸다.
			c.sendTo("[QUICK_MATCH]/Waiting");
		}
		return isStart;
	}

	// 빠른대전 리스트에서 클라이언트 제거
	public void removeClient_QuickMatch_waitingCList(S_Client client) {
		// 얘는 그냥 제거하고 끝.
		quickMatch_waitingCList.remove(client);
	}

}
