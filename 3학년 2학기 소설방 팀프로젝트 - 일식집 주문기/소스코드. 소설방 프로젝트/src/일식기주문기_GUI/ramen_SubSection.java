package 일식기주문기_GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class ramen_SubSection extends JPanel{
	
	//라면의 구체적인 면발 및 마늘의 양 그리고 국물의 농도를 정하는 세션 .
		//완료 버튼을 누르면 토핑 세션으로 넘어간다. 
		
		private JLabel NoodleThicknessLabel ;
		private JLabel NoodleTypeLabel;
		private JLabel soupconcentrationLabel ;
		private JLabel soupTypeLabel;
		private JLabel SpiceAmountLabel ;
		private JLabel GarlicamountLabel ;
		
		private JRadioButton Thick1;
		private JRadioButton Thick2;
		private JRadioButton Thick3;
		private JRadioButton Thick4;

		private JRadioButton NoodleType1;
		private JRadioButton NoodleType2;
		private JRadioButton NoodleType3;
		private JRadioButton NoodleType4;
		
		private JRadioButton concentration1;
		private JRadioButton concentration2;
		private JRadioButton concentration3;
		private JRadioButton concentration4;
		
		private JRadioButton soupType1;
		private JRadioButton soupType2;
		private JRadioButton soupType3;
		private JRadioButton soupType4;
		
		private JRadioButton spice1;
		private JRadioButton spice2;
		private JRadioButton spice3;
		private JRadioButton spice4;	
		
		private JRadioButton Garlic1;
		private JRadioButton Garlic2;
		private JRadioButton Garlic3;
		private JRadioButton Garlic4;
		
		
		private JButton CompleteButton;
		private JButton BackButton ; 
		
		private ButtonGroup buttonGroup;
		private ButtonGroup buttonGroup2;
		private ButtonGroup buttonGroup3;
		private ButtonGroup buttonGroup4;
		private ButtonGroup buttonGroup5;
		private ButtonGroup buttonGroup6;
		
		private view win ; 

	public ramen_SubSection(view win)
	{
		setLayout(null);
		this.win = win ;
		
		ActionSort action = new ActionSort();
//		1
		NoodleThicknessLabel = new JLabel("면발의 굵기");
		NoodleThicknessLabel.setBounds(100,80, 80 ,  40);
		add(NoodleThicknessLabel);
		
		Thick1 = new JRadioButton("1");
		Thick2 = new JRadioButton("2");
		Thick3 = new JRadioButton("3");
		Thick4 = new JRadioButton("4");
		
		Thick1.setBounds(260,80,40,40);
		Thick2.setBounds(300,80,40,40);
		Thick3.setBounds(340,80,40,40);
		Thick4.setBounds(380,80,40,40);
		Thick1.setSelected(true);
		
		add(Thick4);
		add(Thick1);	
		add(Thick3);	
		add(Thick2);
		
		buttonGroup = new ButtonGroup();
		buttonGroup.add(Thick1);
		buttonGroup.add(Thick2);
		buttonGroup.add(Thick3);
		buttonGroup.add(Thick4);
//		2
		NoodleTypeLabel = new JLabel("면의 종류"); 
		NoodleTypeLabel.setBounds(100,120, 80 ,  40);
		add(NoodleTypeLabel);

		NoodleType1 = new JRadioButton("1");
		NoodleType2 = new JRadioButton("2");
		NoodleType3 = new JRadioButton("3");
		NoodleType4 = new JRadioButton("4");
		NoodleType1.setSelected(true);
		
		NoodleType1.setBounds(260,120,40,40);
		NoodleType2.setBounds(300,120,40,40);
		NoodleType3.setBounds(340,120,40,40);
		NoodleType4.setBounds(380,120,40,40);
		
		add(NoodleType1);
		add(NoodleType2);
		add(NoodleType3);
		add(NoodleType4);
		
		buttonGroup2 =new ButtonGroup();
		buttonGroup2.add(NoodleType1);
		buttonGroup2.add(NoodleType2);
		buttonGroup2.add(NoodleType3);
		buttonGroup2.add(NoodleType4);
		

//		3
		soupconcentrationLabel = new JLabel("국물의 농도") ;
		soupconcentrationLabel.setBounds(100, 160, 160, 40);
		add(soupconcentrationLabel);
		
		concentration1 = new JRadioButton("1");
		concentration2 = new JRadioButton("2");
		concentration3 = new JRadioButton("3");
		concentration4 = new JRadioButton("4");
		concentration1.setSelected(true);
		
		concentration1.setBounds(260,160,40,40);
		concentration2.setBounds(300,160,40,40);
		concentration3.setBounds(340,160,40,40);
		concentration4.setBounds(380,160,40,40);
		
		add(concentration1);
		add(concentration2);
		add(concentration3);
		add(concentration4);
		
		ButtonGroup buttonGroup3 = new ButtonGroup();
		buttonGroup3.add(concentration1);
		buttonGroup3.add(concentration2);
		buttonGroup3.add(concentration3);
		buttonGroup3.add(concentration4);
		
//		4
		soupTypeLabel = new JLabel("국물의 종류"); 
		soupTypeLabel.setBounds(100,200, 80 ,  40);
		add(soupTypeLabel);

		soupType1 = new JRadioButton("1");
		soupType2 = new JRadioButton("2");
		soupType3 = new JRadioButton("3");
		soupType4 = new JRadioButton("4");
		soupType1.setSelected(true);
		
		soupType1.setBounds(260,200,40,40);
		soupType2.setBounds(300,200,40,40);
		soupType3.setBounds(340,200,40,40);
		soupType4.setBounds(380,200,40,40);
		
		add(soupType1);
		add(soupType2);
		add(soupType3);
		add(soupType4);
		
		buttonGroup4 =new ButtonGroup();
		buttonGroup4.add(soupType1);
		buttonGroup4.add(soupType2);
		buttonGroup4.add(soupType3);
		buttonGroup4.add(soupType4);

//		5
		SpiceAmountLabel = new JLabel("양념의 양"); 
		SpiceAmountLabel.setBounds(100,240, 80 ,  40);
		add(SpiceAmountLabel);

		spice1 = new JRadioButton("1");
		spice2 = new JRadioButton("2");
		spice3 = new JRadioButton("3");
		spice4 = new JRadioButton("4");
		spice1.setSelected(true);
		
		spice1.setBounds(260,240,40,40);
		spice2.setBounds(300,240,40,40);
		spice3.setBounds(340,240,40,40);
		spice4.setBounds(380,240,40,40);
		
		add(spice1);
		add(spice2);
		add(spice3);
		add(spice4);
		
		buttonGroup5 =new ButtonGroup();
		buttonGroup5.add(spice1);
		buttonGroup5.add(spice2);
		buttonGroup5.add(spice3);
		buttonGroup5.add(spice4);
		
		
//		6
		GarlicamountLabel = new JLabel("다진 마늘의 양 ") ;
		GarlicamountLabel.setBounds(100, 280, 100, 40);
		add(GarlicamountLabel);
		
		Garlic1 = new JRadioButton("1");
		Garlic2 = new JRadioButton("2");
		Garlic3 = new JRadioButton("3");
		Garlic4 = new JRadioButton("4");
		Garlic1.setSelected(true);
		
		Garlic1.setBounds(260,280,40,40);
		Garlic2.setBounds(300,280,40,40);
		Garlic3.setBounds(340,280,40,40);
		Garlic4.setBounds(380,280,40,40);
		
		add(Garlic1);
		add(Garlic2);
		add(Garlic3);
		add(Garlic4);
		
		buttonGroup6 = new ButtonGroup();
		buttonGroup6.add(Garlic1);
		buttonGroup6.add(Garlic2);
		buttonGroup6.add(Garlic3);
		buttonGroup6.add(Garlic4);
		
		
		CompleteButton = new JButton("완료");
		CompleteButton.setBounds(200,320,200,80);
		add(CompleteButton);
		CompleteButton.addActionListener(action);
		
		BackButton = new JButton("주문 취소");
		BackButton.setBounds(200,400,200,80);
		add(BackButton);
		BackButton.addActionListener(action);
		
	}
	class ActionSort implements ActionListener
	{
		public void actionPerformed(ActionEvent e )
		{
			if(e.getActionCommand().equals("완료")){
				isSelected();
				reset();
				orderSection.menuStr +="/";
				win.change("ramenTopping");
			}else if(e.getActionCommand().equals("주문 취소")){
				reset();
				orderSection.menuStr = "";
				win.change("order");
			}
		}
		private void isSelected(){
			if(Thick1.isSelected())
				orderSection.menuStr +="/"+Thick1.getText();
			else if(Thick2.isSelected())
				orderSection.menuStr +="/"+Thick2.getText();
			else if(Thick3.isSelected())
				orderSection.menuStr +="/"+Thick3.getText();
			else if(Thick4.isSelected())
				orderSection.menuStr +="/"+Thick4.getText();
			
			if(NoodleType1.isSelected())
				orderSection.menuStr +="/"+NoodleType1.getText();
			else if(NoodleType2.isSelected())
				orderSection.menuStr +="/"+NoodleType2.getText();
			else if(NoodleType3.isSelected())
				orderSection.menuStr +="/"+NoodleType3.getText();
			else if(NoodleType4.isSelected())
				orderSection.menuStr +="/"+NoodleType4.getText();
			
			if(concentration1.isSelected())
				orderSection.menuStr +="/"+concentration1.getText();
			else if(concentration2.isSelected())
				orderSection.menuStr +="/"+concentration2.getText();
			else if(concentration3.isSelected())
				orderSection.menuStr +="/"+concentration3.getText();
			else if(concentration4.isSelected())
				orderSection.menuStr +="/"+concentration4.getText();
			
			if(soupType1.isSelected())
				orderSection.menuStr +="/"+soupType1.getText();
			else if(soupType2.isSelected())
				orderSection.menuStr +="/"+soupType2.getText();
			else if(soupType3.isSelected())
				orderSection.menuStr +="/"+soupType3.getText();
			else if(soupType4.isSelected())
				orderSection.menuStr +="/"+soupType4.getText();
			
			if(spice1.isSelected())
				orderSection.menuStr +="/"+spice1.getText();
			else if(spice2.isSelected())
				orderSection.menuStr +="/"+spice2.getText();
			else if(spice3.isSelected())
				orderSection.menuStr +="/"+spice3.getText();
			else if(spice4.isSelected())
				orderSection.menuStr +="/"+spice4.getText();
			
			if(Garlic1.isSelected())
				orderSection.menuStr +="/"+Garlic1.getText();
			else if(Garlic2.isSelected())
				orderSection.menuStr +="/"+Garlic2.getText();
			else if(Garlic3.isSelected())
				orderSection.menuStr +="/"+Garlic3.getText();
			else if(Garlic4.isSelected())
				orderSection.menuStr +="/"+Garlic4.getText();
		}
		public void reset(){
			Thick1.setSelected(true);
			NoodleType1.setSelected(true);
			concentration1.setSelected(true);
			soupType1.setSelected(true);
			spice1.setSelected(true);
			Garlic1.setSelected(true);
		}
	}
	
	
}
