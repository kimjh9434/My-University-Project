package 일식기주문기_GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class kyudong_Section extends JPanel {

	private JButton beefButton ;
	private JButton porkButton ;
	private JButton chickenButton ;
	private JButton backButton;
	private JLabel beefJLabel;
	private JLabel porkJLabel;
	private JLabel chickenJLabel;
	
	private view win ; 
	
	public kyudong_Section(view win)
	{
		setLayout(null);
		this.win = win;
		
		ActionSort action = new ActionSort();
		
//		chickenButton = new JButton("오야코동 (닭고기)[4500원]") ;
		chickenButton = new JButton(new ImageIcon("data/오야꼬동.png"));
		chickenButton.setBounds(200, 80, 200, 200);
		add(chickenButton);
		chickenButton.addActionListener(action);
		
//		porkButton = new JButton("가츠동 (돼지고기)[5000원]") ;
		porkButton = new JButton(new ImageIcon("data/가츠동.png"));
		porkButton.setBounds(200, 280, 200, 200);
		add(porkButton);
		porkButton.addActionListener(action);
		
//		beefButton = new JButton("규동(소고기)[5500원]");
		beefButton = new JButton(new ImageIcon("data/규동.png"));
		beefButton.setBounds(200, 480, 200 , 200);
		add(beefButton);
		beefButton.addActionListener(action);
		
		beefJLabel = new JLabel("오야코동 (닭고기)[4500원]");
		beefJLabel.setBounds(500, 80, 200, 200);
		add(beefJLabel);
		porkJLabel = new JLabel("가츠동 (돼지고기)[5000원]");
		porkJLabel.setBounds(500, 280, 200, 200);
		add(porkJLabel);
		chickenJLabel = new JLabel("규동(소고기)[5500원]");
		chickenJLabel.setBounds(500, 480, 200, 200);
		add(chickenJLabel);
		
		backButton = new JButton("주문 취소");
		backButton.setBounds(200,700, 200,80);
		add(backButton);
		backButton.addActionListener(action);
		
	}
	class ActionSort implements ActionListener
	{
		public void actionPerformed(ActionEvent e )
		{
			if (e.getSource() == backButton) {
				orderSection.menuStr = "";
				win.change("order");
			} else {
				orderSection.menuStr += "KyuDong";
				if (e.getSource() == chickenButton) {
					orderSection.menuStr += "/Chicken";
				} else if (e.getSource() == porkButton) {
					orderSection.menuStr += "/Pork";
				} else if (e.getSource() == beefButton) {
					orderSection.menuStr += "/Beef";
				}
				win.change("kyudongSub");
			}
		}
	}
}
