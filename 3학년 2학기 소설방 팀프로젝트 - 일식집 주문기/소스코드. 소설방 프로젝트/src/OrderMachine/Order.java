package OrderMachine;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import Menu.Chasyu;
import Menu.Egg;
import Menu.Egg_Kyudong;
import Menu.KonkukKyuDongStore;
import Menu.KonkukRamenStore;
import Menu.KyuDong;
import Menu.KyuDongStore;
import Menu.Menu;
import Menu.Ramen;
import Menu.RamenStore;
import Menu.Shrimp;
import PaymentSystem.PaymentSystem;
import 일식기주문기_GUI.orderSection;

public class Order {

	Vector<Menu> menus;
	int totalPrice;
	PaymentSystem paymentSystem;
	SendOrder sendOrder;
	SalesInfo salesInfo;
	int orderNo;

	RamenStore ramenStore = new KonkukRamenStore();
	KyuDongStore kyudongStore = new KonkukKyuDongStore();

	public Order() {
		this.menus = new Vector<Menu>();
		this.totalPrice = 0;
		this.paymentSystem = new PaymentSystem();
		this.sendOrder = new SendOrder();
		this.salesInfo = null;
		this.orderNo = 1;
	}

	// // 주문하기
	// public SalesInfo order() {
	//
	// // 메뉴를 선택할 동안 반복
	// while(true){
	//
	// Menu m = new Menu();
	//
	//// Ramen ramen1 = ramenStore.orderRamen("Tokyo");
	//// System.out.println(ramen1.getDescription() + " : " + ramen1.cost() + "원
	// \n");
	// System.out.println("==================================");
	//// ramen1 = ramenStore.orderRamen("Hokkaido");
	//// System.out.println(ramen1.getDescription() + " : " + ramen1.cost() + "원
	// \n");
	//
	//// System.out.println(ramen1.getDescription() + " : " + ramen1.cost() + "원
	// \n");
	//
	// // addMenu(m) 또는 removeMenu(m)
	//
	// // 토핑을 선택할동안 반복
	// while(true){
	//
	//// ramen1 = new Chasyu(ramen1);
	//// ramen1 = new Egg(ramen1);
	//
	// break;
	// }
	// break;
	// }
	//
	// // 결재하기 - 1이면 카드, 2면 현금
	// int payType = 1; // 결재 유형을 물어보고 정해야 함.
	// pay(totalPrice, payType);
	//
	// // 결재 당시 시각
	// Date date = new Date();
	// SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd
	// HH:mm:ss");
	// String dateStr = transFormat.format(date);
	// salesInfo = new SalesInfo(dateStr, menus, totalPrice);
	//
	// // 주문내력[판매정보] 전송하기
	// sendOrder(salesInfo);
	//
	// return salesInfo; // 판매정보 리턴하기
	// }

	// 주문 추가
	public void addMenu(String menuStr) {
		System.out.println("addMenu : GUI에서 받은 값");
		System.out.println(menuStr);
		String[] rmenuStr = menuStr.split("/");

		Ramen ramen = null;
		KyuDong kyuDong = null;
		Menu m = null;
		if (rmenuStr[0].equals("Ramen")) {
			ramen = ramenStore.orderRamen(rmenuStr[1], rmenuStr[2], rmenuStr[3], rmenuStr[4], rmenuStr[5], rmenuStr[6],
					rmenuStr[7]);
			if (!rmenuStr[8].equals(" ")) {
				rmenuStr[8] = rmenuStr[8].substring(0, rmenuStr[8].length() - 1);
				String[] toppings = rmenuStr[8].split(",");
				if (toppings.length != 0) {
					for (int i = 0; i < toppings.length; i++) {
						if (toppings[i].equals("Egg")) {
							ramen = new Egg(ramen);
						} else if (toppings[i].equals("Chasyu")) {
							ramen = new Chasyu(ramen);
						}
					}
				}
			}
			m = ramen;
			m.setCost(ramen.getCost());
			System.out.println(ramen.getCost() + "원");
			System.out.println(m.getCost() + "원");
		} else if (rmenuStr[0].equals("KyuDong")) {
			kyuDong = kyudongStore.orderKyuDong(rmenuStr[1], rmenuStr[2], rmenuStr[3]);
			if (!rmenuStr[4].equals(" ")) {
				rmenuStr[4] = rmenuStr[4].substring(0, rmenuStr[4].length() - 1);
				String[] toppings = rmenuStr[4].split(",");
				if (toppings.length != 0) {
					for (int i = 0; i < toppings.length; i++) {
						if (toppings[i].equals("Egg")) {
							kyuDong = new Egg_Kyudong(kyuDong);
						} else if (toppings[i].equals("Shrimp")) {
							kyuDong = new Shrimp(kyuDong);
						}
					}
				}
			}
			m = kyuDong;
			m.setCost(kyuDong.getCost());
		}
		System.out.println(m.getDescription());
		menus.add(m);
		totalPrice += m.getCost();
		System.out.println(totalPrice);

		orderSection.modifiedMenu(menus, totalPrice);
	}

	// 주문 제거
	public void removeMenu(int menuIndex) {
		System.out.println("removeMenu : GUI에서 받은 값");
		System.out.println(menuIndex);

		Menu m = menus.get(menuIndex);
		menus.remove(m);
		totalPrice -= m.getCost();

		orderSection.modifiedMenu(menus, totalPrice);
	}

	// 결재하기
	public void pay(int totalPrice, int payType) {
		// 1이면 카드, 2면 현금
		paymentSystem.pay(totalPrice, payType);
	}

	public void orderFinish() {
		// 결재 당시 시각
		Date date = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = transFormat.format(date);
		salesInfo = new SalesInfo(dateStr, menus, totalPrice, orderNo);
		orderNo++;
		menus = new Vector<Menu>();
		totalPrice=0;
		
		// 주문내력[판매정보] 전송하기
		sendOrder(salesInfo);
		

//		 판매분석에 해당 정보를 추가한다.
//		salesAnalysis.addSalesInfos(salesInfo);
		OrderMachine.salesAnalysis.addSalesInfos(salesInfo);
	}

	// 주문내력 전송하기
	public void sendOrder(SalesInfo salesInfo) {
		sendOrder.sendOrder(salesInfo);
	}

}
