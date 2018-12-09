package External;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.Vector;

import Menu.Menu;
import OrderMachine.SalesInfo;

public class InfoAnalysisServerThread extends Thread {
	private Socket socket; // 주 소켓

	private DataInputStream reader; // 소켓의 입력 스트림
	private DataOutputStream writer; // 소켓의 출력 스트림
	Vector<SalesInfo> salesInfos = new Vector<SalesInfo>();

	InfoAnalysisServerThread(Socket socket) {
		System.out.println("클라이언트가 서버에 접속했습니다.");
		this.socket = socket;

		try {
			reader = new DataInputStream(socket.getInputStream());
			writer = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void run() {
		// 수신 메시지와 파싱 메시지 처리하는 변수 선언
		String msg;
		String[] rmsg;
		int salesInfosCount = 0;

		while (true) {
			try {
				msg = reader.readUTF();
				System.out.println("ServerThread While: 클라이언트로 부터 받은 값 : " + msg);
				rmsg = msg.split("/"); // '/' 구분자를 기준으로 메시지를 문자열 배열로 파싱

				// msg = [SALE_DATA_COUNT]/salesInfosCount
				if (rmsg[0].equals("[SALE_DATA_COUNT]")) {
					salesInfosCount = Integer.parseInt(rmsg[1]);
				}
				// rmsg[0] rmsg[1] rmsg[2] rmsg[3]
				// msg = [SALE_DATA]/date/totalPrice/menus....
				// menus = menu1 /t menu2 /t menu3 ...
				else if (rmsg[0].equals("[SALE_DATA]")) {

					Vector<Menu> menus = new Vector<Menu>();
					addManuList(menus, rmsg[3]);
					SalesInfo salesInfo = new SalesInfo(rmsg[1], menus, Integer.parseInt(rmsg[2]));
					salesInfos.addElement(salesInfo);

					// 만약 클라이언트쪽에서 보내기로한 판매정보를 모두 보낸다면,
					if (salesInfos.size() == salesInfosCount) {
						// 정보를 분석한다.
						SalesAnalysis();
					}

				}
			} catch (Exception e) {
			}
		}
	}

	private void SalesAnalysis() {
		System.out.println("정보분석 회사의 SalesAnalysis 메소드 실행");
		// 가장 많이 팔린 메뉴와 가장 적게 팔린 메뉴를 찾아야함.

		// 원래 더 많은 정보를 받아와서, 이 회사 자체적인 알고리즘에 따라, 체계적으로 분석이 가능해야 하지만
		// 간단하게만 해보겠다.
		String BestMenu = null;
		String WorstMenu = null;

		Vector<String> menusTotal = new Vector<String>();
		int[] menusCount = new int[100];

		SalesInfo salesInfo;
		Vector<Menu> menus;
		String[] r_menus;// 토큰으로 자름
		// 1. 우선 전체 메뉴 판매 횟수 배열을 만든다.
		// 1.1. 전체 메뉴 판매한 횟수만큼 반복한다.
		for (int i = 0; i < salesInfos.size(); i++) {
			// 1.1.1. 판매정보를 받는다.
			salesInfo = salesInfos.get(i);
			// 1.1.2. 판매정보의 메뉴들을 받는다.
			menus = salesInfo.getMenus();
			// 1.1.3. 해당 메뉴들의 메뉴수만큼 반복한다.
			for (int j = 0; j < menus.size(); j++) {
				// 분석하기 전에 해당 메뉴와 토핑을 분리한다.
//				System.out.println(menus.get(j).getDescription());
				r_menus = menus.get(j).getDescription().split(" ");
				// 1.1.3.1. 해당 메뉴가 전체 메뉴들에 있는지 확인한다.
				boolean isOverap = false;
				int k;
				for (k = 0; k < menusTotal.size(); k++) {
					if (menusTotal.get(k).equals(r_menus[0])) {
						// 있으면, 해당 메뉴의 개수를 증가시킨다.
						isOverap = true;
						menusCount[k] += 1;
						break;
					}
				}
				if (!isOverap) {// 없으면 [=새로운 메뉴이면]
					// 메뉴를 전체 메뉴에 추가한다.
					menusTotal.add(r_menus[0]);
					menusCount[k] += 1;
				}
			}
		}
//		// 1.5. 디버깅  중간점검 : 전체 메뉴 출력
//		for (int i = 0; i < menusTotal.size(); i++) {
//			System.out.println("메뉴 : " + menusTotal.get(i) + " - " + menusCount[i] + "회 주문");
//		}

		// 2. 횟수를 통해 정렬한다.
		// 그까이꺼 그냥 버블정렬을 한다.
		int tmp;
		String tmpStr;
		for (int i = 0; i < menusTotal.size(); i++) {
			for (int j = 0; j < menusTotal.size() - 1; j++) {
				// 앞의 수와 바로 뒤의 수를 비교해서, 뒤의 수가 클 경우 값을 교환
				if (menusCount[j] < menusCount[j + 1]) {
					tmp = menusCount[j];
					tmpStr = menusTotal.get(j);
					menusCount[j] = menusCount[j + 1];
					menusTotal.set(j, menusTotal.get(j + 1));
					menusCount[j + 1] = tmp;
					menusTotal.set(j + 1, tmpStr);
				}
			}
		}
		System.out.println("정렬된 이후");
		// 2.5. 디버깅 중간점검 : 정렬된 전체 메뉴 출력
		for (int i = 0; i < menusTotal.size(); i++) {
			System.out.println("메뉴 : " + menusTotal.get(i) + " - " + menusCount[i] + "회 주문");
		}
		
		// 3. 가장 많이 팔린 메뉴와 적게 팔린 메뉴를 찾아서 보낸다.
		// [공동 1등 혹은 공동 꼴찌이면?] - 그런건 귀찮으니 고려하지 않겠다.
		BestMenu = menusTotal.firstElement() + " - " + menusCount[0] + "회 주문";
		WorstMenu = menusTotal.lastElement() + " - " + menusCount[menusTotal.size()-1] + "회 주문";

		try
		{
			System.out.println("주문기로 다시 전송");
			System.out.println("가장 많이 팔린 메뉴 : " + BestMenu);
			System.out.println("가장 적게 팔린 메뉴 : " + WorstMenu);
			// msg = "[SALE_ANALYSIS_DATA]/BestMenu/WorstMenu
			writer.writeUTF(String.format("[SALE_ANALYSIS_DATA]/%s/%s", BestMenu, WorstMenu));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 메뉴 리스트에서 메뉴들을 추출하여 menus에 추가한다.
	private void addManuList(Vector<Menu> menus, String msg) {
		StringTokenizer st = new StringTokenizer(msg, "\t");
		Menu menu;
		while (st.hasMoreElements()) {
			menu = new Menu();
			menu.setDescription(st.nextToken());
			menus.add(menu);
		}
	}

	// private class Menu_{
	// Menu menu;
	// int count;
	//
	// Menu_(Menu menu, int count){
	// this.menu = menu;
	// this.count = count;
	// }
	// void countPlus(){
	// count++;
	// }
	// }
}
