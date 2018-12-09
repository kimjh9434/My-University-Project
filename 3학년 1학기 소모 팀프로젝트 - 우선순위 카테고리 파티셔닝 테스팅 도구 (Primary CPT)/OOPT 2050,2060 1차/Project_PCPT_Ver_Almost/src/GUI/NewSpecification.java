package GUI;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

import System.*;

public class NewSpecification extends JFrame{
	private JLabel description;
	private JPanel basePanel;
	private JTextField name;
	private JButton ok;
	private Container cont;
	private FirstFrame first;
	private FileManager fm;
	private Specification spec;

	public NewSpecification(){
		initComponents();
	}

	public NewSpecification(FirstFrame f,FileManager manager,Specification s){
		first=f;
		fm = manager;
		spec = s;
		initComponents();
	}

	private void initComponents(){

		this.setSize(350,150);
		this.setLayout(null);
		this.setTitle("새 프로젝트");
		this.setResizable(false);

		description = new JLabel("새 파일 이름 : ");
		name = new JTextField();
		ok = new JButton("결정");
		basePanel = new JPanel();

		basePanel.setSize(350,40);
		description.setPreferredSize(new Dimension(100,30));
		name.setPreferredSize(new Dimension(150, 30));
		ok.setPreferredSize(new Dimension(70,30));

		name.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==e.VK_ENTER){
					int ret=fm.newSpecification(name.getText());
					if(ret == 0){
						new MainFrame(fm,spec).setVisible(true);
						dispose();
						first.dispose();
					}else{
						dispose();
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		ok.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int ret=fm.newSpecification(name.getText());
				if(ret == 0){
					new MainFrame(fm,spec).setVisible(true);
					dispose();
					first.dispose();
				}else{
					dispose();
				}
			}
		});		
		basePanel.add(description);
		basePanel.add(name);
		basePanel.add(ok);

		basePanel.setLocation(0,40);
		this.getContentPane().add(basePanel);

	}
}
