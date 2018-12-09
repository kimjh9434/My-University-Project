package 일식기주문기_GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import 일식기주문기_GUI.ramen_Section.ActionSort;

public class ramen_ToppingSection  extends JPanel {

	private JButton EggButton ;
	private JButton ChasyuButton ;
	private JButton Button ;
	private JButton HokkaidoButton ;
	private JButton BackButton;
	private JButton CompleteButton ; 
	private view win ;

	public ramen_ToppingSection(view win)
	{
		setLayout(null);
		this.win = win ;

		ActionSort action = new ActionSort();

		EggButton = new JButton("계란 추가 [+500원]");
		EggButton.setBounds(200,80, 200 ,  80);
		add(EggButton);
		EggButton.addActionListener(action);
		
		ChasyuButton = new JButton("차슈 추가 [+800원]");
		ChasyuButton.setBounds(200,160, 200 ,  80);
		add(ChasyuButton);
		ChasyuButton.addActionListener(action);

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
			if(e.getActionCommand().equals("계란 추가 [+500원]")){
				orderSection.menuStr +=",Egg";
			} else if(e.getActionCommand().equals("차슈 추가 [+800원]")){
				orderSection.menuStr +=",Chasyu";
			}
			else if(e.getActionCommand().equals("주문 취소")){
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
