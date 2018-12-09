package OrderMachine;

import java.util.Vector;
import Menu.Menu;

// 판매 정보 클래스
public class SalesInfo {
	private String date;        // 주문을 한 날짜
	private Vector<Menu> menus; // 주문 리스트
	int totalPrice;             // 총 가격
	int orderNo;
	

	public SalesInfo(String date, Vector<Menu> menus, int totalPrice) {
		this.date = date;
		this.menus = menus;
		this.totalPrice = totalPrice;
	}
	
	public SalesInfo(String date, Vector<Menu> menus, int totalPrice, int orderNo) {
		this.date = date;
		this.menus = menus;
		this.totalPrice = totalPrice;
		this.orderNo = orderNo;
	}
	
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Vector<Menu> getMenus() {
		return menus;
	}
	public void setMenus(Vector<Menu> menus) {
		this.menus = menus;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
}
