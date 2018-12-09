package 일식기주문기_GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.interfaces.RSAMultiPrimePrivateCrtKey;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ramen_Section extends JPanel {

	private JButton NagasakiButton;
	private JButton TokyoButton;
	private JButton HokkaidoButton;
	private JButton BackButton;
	private JLabel NagasakiJLabel;
	private JLabel TokyoJLabel;
	private JLabel HokkaidoJLabel;

	private view win;
	private String ramen;

	public ramen_Section(view win) {
		setLayout(null);
		this.win = win;

		ActionSort action = new ActionSort();

//		NagasakiButton = new JButton("나가사키 라면[2500원]");
		NagasakiButton = new JButton(new ImageIcon("data/나가사키라면.png"));
		NagasakiButton.setBounds(200, 80, 200, 200);
		add(NagasakiButton);
		NagasakiButton.addActionListener(action);

//		TokyoButton = new JButton("도쿄 라면[3000원]");
		TokyoButton = new JButton(new ImageIcon("data/도쿄라면.png"));
		TokyoButton.setBounds(200, 280, 200, 200);
		add(TokyoButton);
		TokyoButton.addActionListener(action);

//		HokkaidoButton = new JButton("훗카이도 라면[3500원]");
		HokkaidoButton = new JButton(new ImageIcon("data/홋카이도라면.png"));
		HokkaidoButton.setBounds(200, 480, 200, 200);
		add(HokkaidoButton);
		HokkaidoButton.addActionListener(action);
		
		NagasakiJLabel = new JLabel("나가사키 라면[2500원]");
		NagasakiJLabel.setBounds(500, 80, 200, 200);
		add(NagasakiJLabel);
		TokyoJLabel = new JLabel("도쿄 라면[3000원]");
		TokyoJLabel.setBounds(500, 280, 200, 200);
		add(TokyoJLabel);
		HokkaidoJLabel = new JLabel("훗카이도 라면[3500원]");
		HokkaidoJLabel.setBounds(500, 480, 200, 200);
		add(HokkaidoJLabel);
		

		BackButton = new JButton("주문 취소");
		BackButton.setBounds(200, 700, 200, 80);
		add(BackButton);
		BackButton.addActionListener(action);

	}

	class ActionSort implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == BackButton) {
				orderSection.menuStr = "";
				win.change("order");
			} else {
				orderSection.menuStr += "Ramen";
				if (e.getSource() == NagasakiButton) {
					orderSection.menuStr += "/Nagasaki";
				} else if (e.getSource() == TokyoButton) {
					orderSection.menuStr += "/Tokyo";
				} else if (e.getSource() == HokkaidoButton) {
					orderSection.menuStr += "/Hokkaido";
				}
				win.change("ramenSub");
			}
		}
	}
}
