package 일식기주문기_GUI;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import OrderMachine.OrderMachine;

class view extends JFrame {

	//전체 프레임을 바꿔주는 change 함수가 들어있는 함수이다. 
	//그래서 각각의 클래스등릉 선언해주고 view Manager 에서 각가의 객체를 선언 해준다. 여기서는 change 문이 핵싱.
	public orderSection order;
	public managerSection manager;
	public billSection bill;
	public salesSection sales;
	public verifySection verify;
	
	public ramen_Section ramen;
	public ramen_SubSection ramenSub;
	public ramen_ToppingSection ramenTopping;
	
	public kyudong_Section kyudong ; 
	public kyudong_SubSection kyudongSub;
	public kyudong_ToppingSection kyudongTopping;
	
	OrderMachine orderMachine;

	public view(OrderMachine orderMachine){
		this.orderMachine = orderMachine;
	}
	
	public void change(String panelName)
	{
		switch(panelName)
		{
		
		case "order":
			setTitle("주문");
			getContentPane().removeAll();
			getContentPane().add(order);
			revalidate();
			repaint();
			break;
		case "ramen":
			setTitle("라면 선택");
			getContentPane().removeAll();
			getContentPane().add(ramen);
			revalidate();
			repaint();
			break;

		case "ramenSub" :
			setTitle("라면 세부 선택");
			getContentPane().removeAll();
			getContentPane().add(ramenSub);
			revalidate();
			repaint();
			break;
			
		case "ramenTopping" :
			setTitle("라면 추가 토핑 선택");
			getContentPane().removeAll();
			getContentPane().add(ramenTopping);
			revalidate();
			repaint();
			break;	
			
		case "kyudong":
			setTitle("규동 선택");
			getContentPane().removeAll();
			getContentPane().add(kyudong);
			revalidate();
			repaint();
			break;
			
		case "kyudongSub":
			setTitle("규동 세부 선택");
			getContentPane().removeAll();
			getContentPane().add(kyudongSub);
			revalidate();
			repaint();
			break;
			
		case "kyudongTopping":
			setTitle("규동 추가 토핑 선택");
			getContentPane().removeAll();
			getContentPane().add(kyudongTopping);
			revalidate();
			repaint();
			break;
			
		case "bill" :
			setTitle("결제 하는 구역 ");
			billSection.billArea.setText("결재유형을 선택하세요\n총 결재 금액 : "+ orderSection.intTotalPrice + "원");
			getContentPane().removeAll();
			getContentPane().add(bill);
			revalidate();
			repaint();
			break;
		case "verify":
			setTitle("로그인 [관리자 화면] ");
			getContentPane().removeAll();
			getContentPane().add(verify);
			revalidate();
			repaint();
			break;
		case "manager" :
			setTitle("주문");
			getContentPane().removeAll();
			getContentPane().add(manager);
			revalidate();
			repaint();
			break;
		case "sales" :
			setTitle("정산 하는 구역 [관리자 화면] ");
			getContentPane().removeAll();
			getContentPane().add(sales);
			revalidate();
			repaint();
			break;
		}
	}

}