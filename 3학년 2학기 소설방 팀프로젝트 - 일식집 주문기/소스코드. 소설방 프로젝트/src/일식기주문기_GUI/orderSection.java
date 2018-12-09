package 일식기주문기_GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Menu.Menu;

public class orderSection extends JPanel {

	static public String menuStr = "";
	static public int intTotalPrice;
	static public JList menuArea;
	static public JLabel totalPrice;
	
	private JButton ramenButton;
	private JButton kyudongButton;
	private JButton salesButton;
	private JButton orderCancelButton;
	private view win;
	

	@SuppressWarnings("unchecked")
	public orderSection(view win) {
		setLayout(null);
		this.win = win;
		
		win.setSize(1300, 1000);
		ActionSort action = new ActionSort();
		ramenButton = new JButton("라면");
		ramenButton.setBounds(1000, 80, 120, 80);
		add(ramenButton);
		ramenButton.addActionListener(action);

		kyudongButton = new JButton("규동");
		kyudongButton.setBounds(1000, 160, 120, 80);
		add(kyudongButton);
		kyudongButton.addActionListener(action);

		kyudongButton = new JButton("로그인");
		kyudongButton.setBounds(1000, 320, 120, 80);
		add(kyudongButton);
		kyudongButton.addActionListener(action);

		menuArea = new JList<String>();
		menuArea.setBounds(20, 80, 900, 240);
		add(menuArea);

		salesButton = new JButton("계산");
		salesButton.setBounds(1000, 240, 120, 80);
		add(salesButton);
		salesButton.addActionListener(action);
		
		orderCancelButton = new JButton("주문 취소");
		orderCancelButton.setBounds(300, 350, 120, 80);
		add(orderCancelButton);
		orderCancelButton.addActionListener(action);

		totalPrice = new JLabel("총 가격 : 0원");
		totalPrice.setBounds(20, 350, 150, 30);
		add(totalPrice);
	}

	class ActionSort implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("라면"))
				win.change("ramen");
			else if (e.getActionCommand().equals("규동"))
				win.change("kyudong");
			else if (e.getActionCommand().equals("계산")){
				if( intTotalPrice != 0 ){
					win.change("bill");
				}else{
					System.out.println("아직 계산할 메뉴들이 없습니다.");
					JOptionPane.showMessageDialog(null, "아직 계산할 메뉴들이 없습니다.", "계산 실패", JOptionPane.WARNING_MESSAGE);
				
				}
			}else if (e.getActionCommand().equals("로그인"))
				win.change("verify");
			else if (e.getActionCommand().equals("주문 취소")){
				if(!menuArea.isSelectionEmpty()){
					win.orderMachine.order.removeMenu(menuArea.getSelectedIndex());
				}
			}
		}
	}

	public static void modifiedMenu(Vector<Menu> menus, int totalPrice) {
		int count = menus.size();
		String[] strings = new String[count];
		for (int i = 0; i < count; i++) {
			strings[i] = String.format("주문 %d번. [%d원] :%s", i+1, menus.get(i).getCost(),menus.get(i).getDescription());
		}
		menuArea.setListData(strings);
		orderSection.totalPrice.setText("총 가격 : " + totalPrice + "원");
		intTotalPrice = totalPrice;
	}

}
