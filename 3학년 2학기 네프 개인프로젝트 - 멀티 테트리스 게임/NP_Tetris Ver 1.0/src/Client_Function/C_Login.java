package Client_Function;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JOptionPane;

public class C_Login {
	
	private Socket socket; // 주 소켓
	private Socket socket2; // 임시 소켓
	
	private DataOutputStream writer; // 소켓의 출력 스트림
	private DataInputStream reader2; // 소켓의 입력 스트림
	
	public C_Login(Socket socket, Socket socket2) {
		this.socket = socket;
		this.socket2 = socket2;

		try {
			writer = new DataOutputStream(socket.getOutputStream());
			reader2 = new DataInputStream(socket2.getInputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public boolean Login(String id, String pwd) {
		boolean isLogin = false;
		String msg; // 수신 메시지와 파싱 메시지 처리하는 변수 선언
		String[] rmsg;

		System.out.println("로그인");
		try {
			// 1. 아이디와 비밀번호를 받는다.
			System.out.println("입력한 아이디 값 : " + id);
			System.out.println("입력한 비밀번호 값 : " + pwd);

			// 2. 서버로 보낸다. // msg = [LOGIN]/id/pwd
			writer.writeUTF(String.format("[LOGIN]/%s/%s", id, pwd));

			// 3. 로그인 성공여부를 받는다.
			msg = reader2.readUTF();
			rmsg = msg.split("/"); // '/' 구분자를 기준으로 메시지를 문자열 배열로 파싱
			System.out.println(msg);

			if (rmsg[0].equals("[LOGIN]")) {
				if (rmsg[1].equals("SUCCESS")) {
					System.out.println("로그인이 성공적으로 됨");
					isLogin = true;
				} else if (rmsg[1].equals("FAILURE")) {
					if (rmsg[2].equals("INVAILD_VALUE")) {
						JOptionPane.showMessageDialog(null, "아이디 혹은 비밀번호가 틀렸습니다.", "로그인 실패",
								JOptionPane.WARNING_MESSAGE);
						System.out.println("로그인 실패. 아이디 또는 비밀번호가 틀림");
					} else if (rmsg[2].equals("ALREADY_CONNECTED")) {
						JOptionPane.showMessageDialog(null, "이미 동일한 아이디가 접속중입니다.", "로그인 실패",
								JOptionPane.WARNING_MESSAGE);
						System.out.println("로그인 실패. 이미 동일한 계정이 접속중임");
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return isLogin;
	}

	public boolean Join(String id, String pwd) {
		boolean isJoin = false;
		String msg; // 수신 메시지와 파싱 메시지 처리하는 변수 선언
		String[] rmsg;
		System.out.println("회원가입");
		try {
			// 1. 새 아이디와 비밀번호를 받는다.
			System.out.println("입력한 아이디 값 : " + id);
			System.out.println("입력한 비밀번호 값 : " + pwd);

			// 2. 서버로 보낸다. // msg = [JOIN]/id/pwd
			writer.writeUTF(String.format("[JOIN]/%s/%s", id, pwd));

			// 3. 회원가입 성공여부를 받는다.
			msg = reader2.readUTF();
			rmsg = msg.split("/"); // '/' 구분자를 기준으로 메시지를 문자열 배열로 파싱
			if (rmsg[0].equals("[JOIN]")) {
				if (rmsg[1].equals("SUCCESS")) {
					System.out.println("회원가입이 성공적으로 됨");
					isJoin = true;
				} else if (rmsg[1].equals("FAILURE")) {
					if (rmsg[2].equals("ALREADY_JOIN")) {
						JOptionPane.showMessageDialog(null, "이미 중복된 아이디의 계정이 가입되어있습니다.", "회원가입 실패",
								JOptionPane.WARNING_MESSAGE);
						System.out.println("회원가입 실패. 이미 중복된 아이디가 존재함");
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return isJoin;
	}
}
