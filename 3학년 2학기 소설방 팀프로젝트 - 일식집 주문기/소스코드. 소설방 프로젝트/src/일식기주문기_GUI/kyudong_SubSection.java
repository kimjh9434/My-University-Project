package 일식기주문기_GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class kyudong_SubSection extends JPanel {

	// 규동의 구체적인 고기 크기
	// 완료 버튼을 누르면 토핑 세션으로 넘어간다.

	private JLabel sizeLabel;
	private JLabel GarlicamountLabel ;
	private JButton CompleteButton;
	private JButton BackButton;
	private JRadioButton kudongSize1;
	private JRadioButton kudongSize2;
	private JRadioButton kudongSize3;
	private JRadioButton Garlic1;
	private JRadioButton Garlic2;
	private JRadioButton Garlic3;
	private JRadioButton Garlic4;
	private view win;
	
	ButtonGroup buttonGroup;
	ButtonGroup buttonGroup2;

	public kyudong_SubSection(view win) {

		setLayout(null);
		this.win = win;

		ActionSort action = new ActionSort();
//		1
		sizeLabel = new JLabel("규동의 크기");
		sizeLabel.setBounds(100, 80, 100, 40);
		add(sizeLabel);

		kudongSize1 = new JRadioButton("소");
		kudongSize2 = new JRadioButton("중");
		kudongSize3 = new JRadioButton("대");
		
		
		kudongSize1.setBounds(200, 80, 80, 40);
		kudongSize2.setBounds(280, 80, 80, 40);
		kudongSize3.setBounds(360, 80, 80, 40);
		kudongSize1.setSelected(true);
		
		add(kudongSize1);
		add(kudongSize2);
		add(kudongSize3);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(kudongSize1);
		buttonGroup.add(kudongSize2);
		buttonGroup.add(kudongSize3);
		
//		2
		GarlicamountLabel = new JLabel("다진 마늘의 양 ") ;
		GarlicamountLabel.setBounds(100, 120, 100 ,  40);
		add(GarlicamountLabel);
		
		Garlic1 = new JRadioButton("1");
		Garlic2 = new JRadioButton("2");
		Garlic3 = new JRadioButton("3");
		Garlic4 = new JRadioButton("4");
		Garlic1.setSelected(true);
		
		Garlic1.setBounds(200,120,40,40);
		Garlic2.setBounds(250,120,40,40);
		Garlic3.setBounds(300,120,40,40);
		Garlic4.setBounds(360,120,40,40);
		
		add(Garlic1);
		add(Garlic2);
		add(Garlic3);
		add(Garlic4);
		
		buttonGroup2 = new ButtonGroup();
		buttonGroup2.add(Garlic1);
		buttonGroup2.add(Garlic2);
		buttonGroup2.add(Garlic3);
		buttonGroup2.add(Garlic4);

		CompleteButton = new JButton("완료");
		CompleteButton.setBounds(200, 320, 200, 80);
		add(CompleteButton);
		CompleteButton.addActionListener(action);

		BackButton = new JButton("주문 취소");
		BackButton.setBounds(200, 400, 200, 80);
		add(BackButton);
		BackButton.addActionListener(action);
	}

	class ActionSort implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("완료")){
				isSelected();
				reset();
				win.change("kyudongTopping");
				orderSection.menuStr +="/";
			}else if(e.getActionCommand().equals("주문 취소")){
				reset();
				orderSection.menuStr = "";
				win.change("order");
			}
		}
		
		private void isSelected(){
			if(kudongSize1.isSelected())
				orderSection.menuStr +="/"+kudongSize1.getText();
			else if(kudongSize2.isSelected())
				orderSection.menuStr +="/"+kudongSize2.getText();
			else if(kudongSize3.isSelected())
				orderSection.menuStr +="/"+kudongSize3.getText();
			
			if(Garlic1.isSelected())
				orderSection.menuStr +="/"+Garlic1.getText();
			else if(Garlic2.isSelected())
				orderSection.menuStr +="/"+Garlic2.getText();
			else if(Garlic3.isSelected())
				orderSection.menuStr +="/"+Garlic3.getText();
			else if(Garlic4.isSelected())
				orderSection.menuStr +="/"+Garlic4.getText();
			
			
		}
		private void reset() {
			kudongSize1.setSelected(true);
			Garlic1.setSelected(true);
			
		}

	}

}
