package 일식기주문기_GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;

public class billSection extends JPanel {

	private JButton billButton;
	private JButton backButton;
	private JRadioButton CardButton;
	private JRadioButton CashButton;
	static public JTextArea billArea;
	ButtonGroup buttonGroup;

	private view win;

	public billSection(view win) {

		setLayout(null);
		this.win = win;

		billSection.billArea = new JTextArea("");
		ActionSort action = new ActionSort();
		billArea.setBounds(50, 100, 300, 50);
		add(billArea);

		CardButton = new JRadioButton("카드");
		CardButton.setBounds(100, 160, 100, 40);
		add(CardButton);
		CardButton.addActionListener(action);
		CardButton.setSelected(true);

		CashButton = new JRadioButton("현금");
		CashButton.setBounds(280, 160, 100, 40);
		add(CashButton);
		CashButton.addActionListener(action);

		buttonGroup = new ButtonGroup();
		buttonGroup.add(CardButton);
		buttonGroup.add(CashButton);

		billButton = new JButton("결제");
		billButton.setBounds(200, 240, 200, 80);
		add(billButton);
		billButton.addActionListener(action);

		backButton = new JButton("주문 취소");
		backButton.setBounds(200, 320, 200, 80);
		add(backButton);
		backButton.addActionListener(action);
	}

	class ActionSort implements ActionListener {
		public void actionPerformed(ActionEvent e )
		{
			if(e.getActionCommand().equals("결제")){
				isSelected();
				reset();
				win.orderMachine.order.orderFinish();
				win.change("order");
			}
			else if (e.getActionCommand().equals("주문 취소")){
				reset();
				win.change("order");
			}
		}
		private void isSelected(){
			if(CardButton.isSelected()){
				win.orderMachine.order.pay(orderSection.intTotalPrice, 1);
				JOptionPane.showMessageDialog(null, "카드로 성공적으로 결재되었습니다.", "결재 성공", JOptionPane.WARNING_MESSAGE);
			}
				
			if(CashButton.isSelected()){
				win.orderMachine.order.pay(orderSection.intTotalPrice, 2);
				JOptionPane.showMessageDialog(null, "현금으로 성공적으로 결재되었습니다.", "결재 성공", JOptionPane.WARNING_MESSAGE);
			}
		}
		private void reset(){
			String[] strings = {};
			orderSection.menuArea.setListData(strings);
			orderSection.intTotalPrice=0;
			orderSection.totalPrice.setText("총 가격 : 0원");
			CardButton.setSelected(true);
		}
		
	}

}
