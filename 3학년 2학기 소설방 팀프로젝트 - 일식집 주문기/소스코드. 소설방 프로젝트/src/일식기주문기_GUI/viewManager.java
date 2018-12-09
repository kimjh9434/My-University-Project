package 일식기주문기_GUI;

import javax.swing.JFrame;

import OrderMachine.OrderMachine;

public class viewManager {

	OrderMachine orderMachine = null;
	public void Start()
	{
		orderMachine = new OrderMachine();
		
		//모든 GUI들을 객체를 형성한다음 order 를 재생 시켜서 그 다음 패널들을 돌려준다. 
		view view = new view(orderMachine);
		view.setTitle("시작");
		
		view.order = new orderSection(view);
		view.manager = new managerSection(view);
		view.sales = new salesSection(view);
		view.verify = new verifySection(view);
		
		view.ramen = new ramen_Section(view );
		view.ramenSub = new ramen_SubSection(view);
		view.ramenTopping = new ramen_ToppingSection(view);
		
		view.kyudong = new kyudong_Section(view);
		view.kyudongSub = new  kyudong_SubSection(view);
		view.kyudongTopping = new kyudong_ToppingSection(view);
		
		view.bill = new billSection(view);
		
		view.add(view.order);
		view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		view.setSize(1300, 1000);
		view.setVisible(true);

	
	}
}
