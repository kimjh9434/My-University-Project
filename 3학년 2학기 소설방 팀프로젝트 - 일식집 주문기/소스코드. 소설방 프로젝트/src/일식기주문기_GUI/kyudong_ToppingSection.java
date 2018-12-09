package 일식기주문기_GUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class kyudong_ToppingSection  extends JPanel {

	private JButton EggButton ;
	private JButton ShrimpButton;
	private JButton Button ;
	private JButton HokkaidoButton ;
	private JButton BackButton;
	private JButton CompleteButton ; 
	private view win ;

	public kyudong_ToppingSection(view win)
	{

		setLayout(null);
		this.win = win ;

		ActionSort action = new ActionSort();

		EggButton = new JButton("계란 추가 [+500원]");
		EggButton.setBounds(200,80, 200 ,  80);
		add(EggButton);
		EggButton.addActionListener(action);

		ShrimpButton = new JButton("새우 추가 [+400원]");
		ShrimpButton.setBounds(200,160, 200 ,  80);
		add(ShrimpButton);
		ShrimpButton.addActionListener(action);
		
		CompleteButton = new JButton("완료");
		CompleteButton.setBounds(200,240, 200 ,  80);
		add(CompleteButton);
		CompleteButton.addActionListener(action);
		
		
		BackButton = new JButton("주문 취소");
		BackButton.setBounds(200,320,200,80);
		add(BackButton);
		BackButton.addActionListener(action);

	}
	class ActionSort implements ActionListener
	{
		public void actionPerformed(ActionEvent e )
		{
			if(e.getSource() == EggButton){
				orderSection.menuStr +=",Egg";
			} else if(e.getSource() == ShrimpButton){
				orderSection.menuStr +=",Shrimp";
			}
			else if(e.getSource() == BackButton){
				orderSection.menuStr = "";
				win.change("order");
			}
			else  if (e.getActionCommand().equals("완료"))
			{
				orderSection.menuStr +=" ";
				win.change("order");
				win.orderMachine.order.addMenu(orderSection.menuStr);
				orderSection.menuStr="";
			}
		}

	}


}