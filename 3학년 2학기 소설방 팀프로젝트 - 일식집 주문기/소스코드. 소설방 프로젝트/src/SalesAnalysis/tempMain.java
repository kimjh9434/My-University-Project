package SalesAnalysis;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.Vector;

import Menu.Chasyu;
import Menu.Egg;
import Menu.KonkukKyuDongStore;
import Menu.KonkukRamenStore;
import Menu.KyuDong;
import Menu.KyuDongStore;
import Menu.Menu;
import Menu.Ramen;
import Menu.RamenStore;
import Menu.Shrimp;
import OrderMachine.SalesInfo;

public class tempMain {

	public static void main(String[] args) {

//		SalesAnalysis salesAnalysis = SalesAnalysisFactory
//				.getSalesAnalysisType(new InfoSalesAnalysistFactory("제 3의 분석회사"));
//
//		// 결재 당시 시각
//		Date date = new Date();
//		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String dateStr = transFormat.format(date);
//
//		Vector<Menu> menus = new Vector<Menu>();
//		Menu menu = new Menu();
//
//		RamenStore ramenStore = new KonkukRamenStore();
//		KyuDongStore kyuDongStore = new KonkukKyuDongStore();
//		//////////////////////////////////////////////////////////
//		// 주문 Sample 1
//		Ramen ramen1 = ramenStore.orderRamen("Tokyo");
//		ramen1 = new Chasyu(ramen1);
//		menu = ramen1;
//		menus.add(menu);
//		menus.add(menu);
//		menus.add(menu);
//		menus.add(menu);
//		menus.add(menu);
//		menus.add(menu);
//		SalesInfo salesInfo = new SalesInfo(dateStr, menus, 1000);
//		// 판매정보를 받아서, 판매분석에 해당 정보를 추가한다.
//		salesAnalysis.addSalesInfos(salesInfo);
//		//////////////////////////////////////////////////////////
//		// 주문 Sample 2
//		menus = new Vector<Menu>();
//		KyuDong kyuDong1 = kyuDongStore.orderKyuDong("Beef");
//		menu = kyuDong1;
//		menus.add(menu);
//		salesInfo = new SalesInfo(dateStr, menus, 2000);
//		// 판매정보를 받아서, 판매분석에 해당 정보를 추가한다.
//		salesAnalysis.addSalesInfos(salesInfo);
//		//////////////////////////////////////////////////////////
//		// 주문 Sample 3
//		menus = new Vector<Menu>();
//		ramen1 = ramenStore.orderRamen("Nagasiki");
//		ramen1 = new Egg(ramen1);
//		menu = ramen1;
//		menus.add(menu);
//		kyuDong1 = kyuDongStore.orderKyuDong("Chicken");
//		menu = kyuDong1;
//		menus.add(menu);
//		kyuDong1 = kyuDongStore.orderKyuDong("Pork");
//		kyuDong1 = new Shrimp(kyuDong1);
//		menu = kyuDong1;
//		menus.add(menu);
//		salesInfo = new SalesInfo(dateStr, menus, 3000);
//		// 판매정보를 받아서, 판매분석에 해당 정보를 추가한다.
//		salesAnalysis.addSalesInfos(salesInfo);
//		//////////////////////////////////////////////////////////
//		// 주문 Sample 4
//		menus = new Vector<Menu>();
//		ramen1 = ramenStore.orderRamen("Nagasiki");
//		ramen1 = new Egg(ramen1);
//		menu = ramen1;
//		menus.add(menu);
//		menus = new Vector<Menu>();
//		ramen1 = ramenStore.orderRamen("Nagasiki");
//		menu = ramen1;
//		menus.add(menu);
//		ramen1 = ramenStore.orderRamen("Tokyo");
//		menu = ramen1;
//		menus.add(menu);
//		kyuDong1 = kyuDongStore.orderKyuDong("Chicken");
//		menu = kyuDong1;
//		menus.add(menu);
//		menus.add(menu);
//		menus.add(menu);
//		kyuDong1 = kyuDongStore.orderKyuDong("Pork");
//		kyuDong1 = new Shrimp(kyuDong1);
//		menu = kyuDong1;
//		menus.add(menu);
//		salesInfo = new SalesInfo(dateStr, menus, 3000);
//		// 판매정보를 받아서, 판매분석에 해당 정보를 추가한다.
//		salesAnalysis.addSalesInfos(salesInfo);
		//////////////////////////////////////////////////////////
		// 이렇게 모아진 판매 정보들을 분석하라고 시킨다.
//		salesAnalysis.salesAnalysis();
	}
}
