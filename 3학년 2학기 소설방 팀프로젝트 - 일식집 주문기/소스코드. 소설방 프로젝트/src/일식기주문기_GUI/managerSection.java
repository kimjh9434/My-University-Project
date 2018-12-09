package 일식기주문기_GUI;
import javax.swing.JButton;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class managerSection extends JPanel{
	
	
	private JButton SalesButton;
	private JButton OrderButton ;
	private view win ; 
	
	public managerSection(view win )
	{
		setLayout(null);
		this.win = win ;
		
		ActionSort action  = new ActionSort();
		
		SalesButton = new JButton("정산") ;
		SalesButton.setBounds(200, 160, 200, 80);
		add(SalesButton);
		SalesButton.addActionListener(action);

		OrderButton = new JButton("메뉴로 돌아가기");
		OrderButton.setBounds(200, 240, 200 ,  80);
		add(OrderButton);
		OrderButton.addActionListener(action);
		

	}
	class ActionSort implements ActionListener{
		public void actionPerformed(ActionEvent e )
		{
			if(e.getActionCommand().equals("메뉴로 돌아가기"))
				win.change("order");
			else if (e.getActionCommand().equals("정산")){
				if(win.orderMachine.salesAnalysis())
					win.change("sales");
			}
		}
	}
	

}
