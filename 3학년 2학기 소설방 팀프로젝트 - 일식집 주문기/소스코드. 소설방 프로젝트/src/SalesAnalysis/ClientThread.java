package SalesAnalysis;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JOptionPane;

import Menu.Menu;
import OrderMachine.SalesInfo;
import 일식기주문기_GUI.salesSection;

public class ClientThread implements Runnable {

	private Socket socket; // 주 소켓

	private DataInputStream reader; // 소켓의 입력 스트림
	private DataOutputStream writer; // 소켓의 출력 스트림

	// 서버와의 접속 연결하는 메소드
	public boolean connect() {
		boolean isConnect = false;
		try {
			System.out.println("서버에 연결을 요청합니다.");
			socket = new Socket("127.0.0.1", 6000);

			System.out.println("---서버 접속 성공--");
			isConnect = true;
			reader = new DataInputStream(socket.getInputStream());
			writer = new DataOutputStream(socket.getOutputStream());

			new Thread(this).start(); // 쓰레드를 실행시킨다.
		} catch (Exception e) {
			System.out.println("\n\n서버 연결 실패..\n");
		}
		return isConnect;
	}

	@Override
	public void run() {
		// 수신 메시지와 파싱 메시지 처리하는 변수 선언
		String msg;
		String[] rmsg;

		while (true) {
			try {
				msg = reader.readUTF();
				System.out.println("C_ClientThread | 서버로 부터 받은 값 : " + msg);
				rmsg = msg.split("/"); // '/' 구분자를 기준으로 메시지를 문자열 배열로 파싱

				// msg = "[SALE_ANALYSIS_DATA]/BestMenu/WorstMenu
				if (rmsg[0].equals("[SALE_ANALYSIS_DATA]")) {

					System.out.println("분석회사에서 판매자료를 분석한 결과");
					System.out.println("가장 많이 팔린 메뉴 : " + rmsg[1]);
					System.out.println("가장 적게 팔린 메뉴 : " + rmsg[2]);
					
					salesSection.updateInfo(rmsg[1], rmsg[2]);

				} else if (rmsg[0].equals("[QUIT]")) { // 서버 다운

				}

				// 그냥 돌아가는 중
			} catch (Exception e) {
				System.out.println(e);
				System.out.println("클라이언트 쪽에서 에러가 발생해서, 서버와의 연결이 끊겼습니다.");
				try {
					// 서버쪽에 클라이언트와 접속을 끊으라고 전달함.
					writer.writeUTF("[QUIT]");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				System.out.println("서버와 접속을 종료함");
			}
		}

	}

	// 판매 정보를 서보로 보낸다.
	public boolean SendSalesAnalysis(Vector<SalesInfo> salesInfos) {
		// SalesInfo {
		// private String date; // 주문을 한 날짜
		// private Vector<Menu> menus; // 주문 리스트
		// int totalPrice; // 총 가격
		// }
		boolean isAnalysis = false;
		String msg = null;
		String menus;

		int salesInfosSize = salesInfos.size();
		if (salesInfosSize != 0) {
			try {
				// msg = [SALE_DATA_COUNT]/salesInfosCount
				writer.writeUTF("[SALE_DATA_COUNT]/" + salesInfos.size());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (int i = 0; i < salesInfosSize; i++) {
				// msg = [SALE_DATA]/date/totalPrice/menus....
				// menus = menu1 /t menu2 /t menu3 ...
				menus = getManuNamesInSalesInfo(salesInfos.get(i).getMenus());
				msg = String.format("[SALE_DATA]/%s/%d/%s", salesInfos.get(i).getDate(),
						salesInfos.get(i).getTotalPrice(), menus);
				try {
					writer.writeUTF(msg);
				} catch (IOException e) {
					e.printStackTrace();
				}
				isAnalysis = true;
			}
		} else{
			System.out.println("분석할 정보가 없습니다.");
			JOptionPane.showMessageDialog(null, "분석할 정보가 없습니다.", "정보 분석 실패", JOptionPane.WARNING_MESSAGE);
		}
		return isAnalysis;
	}

	// salesInfo에 있는 메뉴이름들을 붙여서 반환한다.
	private String getManuNamesInSalesInfo(Vector<Menu> menus) {
		StringBuffer sb = new StringBuffer("");
		int size = menus.size();
		for (int i = 0; i < size; i++)
			sb.append(menus.get(i).getDescription() + "\t");
		return sb.toString();
	}

}
