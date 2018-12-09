package Server_Function;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// 서버쪽에서 로그인 관련 작업을 처리하는 클래스
public class S_Login {

	private Socket socket; // 주 소켓
	private Socket socket2; // 임시 소켓
	S_RoomManager roomManager;

	private DataOutputStream writer; // 소켓의 출력 스트림
	private DataOutputStream writer2; // 소켓의 출력 스트림

	Connection conn; // DB와 연결된 Connection

	// Socket 2를 넘겨받음
	S_Login(Socket socket, Socket socket2, S_RoomManager roomManager) {
		this.socket = socket;
		this.socket2 = socket2;
		this.roomManager = roomManager;
		try {
			writer = new DataOutputStream(socket.getOutputStream());
			writer2 = new DataOutputStream(socket2.getOutputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// DB 연결
		String url = "jdbc:mysql://localhost:3306/project?verifyServerCertificate=false&useSSL=true";
		String userid = "root";
		String pwd = "111111";

		try {
			// 1. 드라이버 로드
			Class.forName("com.mysql.jdbc.Driver"); // 드라이버 이름 대소문자 주의

			// 2. Connection 생성
			conn = DriverManager.getConnection(url, userid, pwd);

			System.out.println("DB 접속 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("DB Driver Error");
			e.printStackTrace();
		} catch (SQLException se) {
			System.out.println("DB Connection Error");
			se.printStackTrace();
		}
	}

	public boolean login(String id, String pwd) {
		boolean isLogin = false;
		String retString = "[LOGIN]";
		System.out.println("로그인");
		try {
			// 1. id와 pwd를 받는다.
			System.out.println("전송받은 아이디 값 : " + id);
			System.out.println("전송받은 비밀번호 값 : " + pwd);

			// 2. id와 pwd값이 DB에 있는지 확인한다.
			String query = String.format("SELECT COUNT(*) FROM member WHERE id='%s' AND pwd='%s';", id, pwd);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);/* SQL 문 실행 */
			rs.next();
			// 3. 값이 있으면 로그인에 성공했고, 없으면 실패함.
			if (rs.getInt(1) == 1) {

				// 3.1. 이미 서버에 접속해있는지 체크한다.
				if (!roomManager.isAccept(id)) {
					System.out.println("로그인 성공");
					isLogin = true;
					retString += "/SUCCESS";
					// 3.2 . 성공여부를 클라이언트로 보낸다. [이건 메인 소켓으로 보냄] 
					// msg = [LOGIN]/SUCCESS/id
					writer.writeUTF(String.format("%s/%s", retString, id));
				} else {
					retString += "/FAILURE/ALREADY_CONNECTED";
					System.out.println("로그인 실패. 이미 동일한 계정이 접속중임");
				}

			} else {
				retString += "/FAILURE/INVAILD_VALUE";
				System.out.println("로그인 실패. 아이디 혹은 비밀번호가 틀림");
			}

			// 4. 성공여부를 클라이언트로 보낸다.
			writer2.writeUTF(retString);
			System.out.println(retString);

			// 5. 이후 뒷정리
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return isLogin;
	}

	public boolean join(String id, String pwd) {
		boolean isJoin = false;
		String retString = "[JOIN]";
		System.out.println("회원가입");
		try {
			// 1. id와 pwd를 받는다.
			System.out.println("전송받은 아이디 값 : " + id);
			System.out.println("전송받은 비밀번호 값 : " + pwd);

			// 2. id값이 DB에 있는지 확인한다.
			String query = String.format("SELECT COUNT(*) FROM member WHERE id='%s';", id);
			System.out.println(query);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);/* SQL 문 실행 */
			rs.next();
			// 3. 값이 없으면 회원가입을 진행한다. [있으면 가입안됨]
			if (rs.getInt(1) == 0) {
				query = String.format("INSERT INTO member(id, pwd) VALUES('%s','%s');", id, pwd);
				stmt.executeUpdate(query);/* SQL 문 실행 */
				System.out.println("회원가입 성공");
				isJoin = true;
				retString += "/SUCCESS";
			} else {// 이미 회원가입된 id값임.
				System.out.println("회원가입 실패. 이미 가입되어있는 아이디임");
				retString += "/FAILURE/ALREADY_JOIN";
			}

			// 4. 성공여부를 클라이언트로 보낸다.
			writer2.writeUTF(retString);

			// 5. 이후 뒷정리
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return isJoin;
	}

}
