package 일식기주문기_GUI;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class salesSection extends JPanel {

	private JButton billButton;
	private JButton backButton;
	public static JTextArea SalesArea;

	private view win ; 
	
	public salesSection(view win)
	{
		
		setLayout(null);
		this.win = win ;
		
		ActionSort action = new ActionSort();
		
		SalesArea = new JTextArea() ; 
		SalesArea.setBounds(200,20,400,120);
		add(SalesArea);
		
		backButton = new JButton("뒤로");
		backButton.setBounds(200,320,160,80);
		add(backButton);
		backButton.addActionListener(action);
		
	}
	
	public static void updateInfo(String bestMenu, String worstMenu){
		SalesArea.append("정보분석회사의 분석결과\n");
		SalesArea.append("가장 많이 팔린 메뉴 : " + bestMenu+"\n");
		SalesArea.append("가장 많이 팔린 메뉴 : " + worstMenu+"\n");
	}
	
	class ActionSort implements ActionListener
	{
		public void actionPerformed(ActionEvent e )
		{
			SalesArea.setText("");
			if(e.getActionCommand().equals("뒤로"))
				win.change("manager");
			
		}
		
	}


}
