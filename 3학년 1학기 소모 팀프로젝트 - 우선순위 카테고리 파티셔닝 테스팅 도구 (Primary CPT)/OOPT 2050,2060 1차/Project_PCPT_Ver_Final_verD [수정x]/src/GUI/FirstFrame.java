package GUI;

import System.*;

import com.jtattoo.plaf.hifi.HiFiLookAndFeel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

public class FirstFrame extends JFrame {
	private FileManager fm;
	private Specification spec;
	private FirstFrame f = this;
	private JButton newSpec;
	private JButton loadSpec;
	private JButton quit;
	private JList<String> recentList;
	private JPanel startMenuPane;
	private JPanel listBasePane;
	private JLabel listTitle;
	private JScrollPane listPane;
	private JSplitPane startBase;
	GridLayout gl = new GridLayout(3,0);
	/**
	 * Creates new form NewJFrame
	 */
	public FirstFrame() {
		initComponents();
	}

	public FirstFrame(FileManager manager,Specification s){
		fm = manager;
		spec = s;
		initComponents();
	}

	private void initComponents() {
		this.setSize(400,400);
		this.setTitle("Make Test Case!");
		this.setResizable(false);

		startBase = new JSplitPane();
		listTitle = new JLabel();
		listBasePane = new JPanel();
		listPane = new JScrollPane();
		recentList = new JList<>();
		startMenuPane = new JPanel();
		newSpec = new JButton();
		loadSpec = new JButton();
		quit = new JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		startBase.setSize(400,400);
		startBase.setDividerLocation(200);
		startBase.setEnabled(false);
		this.getContentPane().add(startBase);
		
		recentList.setModel(new javax.swing.AbstractListModel<String>() {
			public int getSize() { return fm.recentFileList.length; }
			public String getElementAt(int i) { return fm.recentFileList[i]; }
		});

		listBasePane.setLayout(null);
		listBasePane.setSize(200,400);

		listTitle.setText("최근 파일 목록");
		listTitle.setSize(100,40);
		listTitle.setBounds(60,50,100,40);
		listPane.setViewportView(recentList);
		recentList.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.getClickCount()==2){
					if(recentList.getModel().getElementAt(0).compareTo("")==0){

					}else{
						int ret = fm.loadSpecification(recentList.getModel().getElementAt(recentList.getSelectedIndex()));
						if(ret==0){
							new MainFrame(fm,spec).setVisible(true);
							dispose();
						}else{
						}
					}
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

		});
		recentList.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==e.VK_ENTER){
					if(recentList.getModel().getElementAt(0).compareTo("")==0){

					}else{
						int ret = fm.loadSpecification(recentList.getModel().getElementAt(recentList.getSelectedIndex()));
						if(ret==0){
							new MainFrame(fm,spec).setVisible(true);
							dispose();
						}else{

						}
					}
				}
				// TODO Auto-generated method stub
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
		listPane.setBounds(10,120,180,100);
		listBasePane.add(listTitle);
		listBasePane.add(listPane);

		startBase.setLeftComponent(listBasePane);

		newSpec.setText("새로 만들기");
		newSpec.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				new NewSpecification(f,fm,spec).setVisible(true);
			}	
		});
		loadSpec.setText("불러오기");
		loadSpec.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int ret = new Explorer().doit(0, fm, spec);
				if(ret==0){
					dispose();
				}
			}
		});
		quit.setText("종료");
		quit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});

		startMenuPane.setLayout(gl);
		startMenuPane.add(newSpec);
		startMenuPane.add(loadSpec);
		startMenuPane.add(quit);

		startBase.setRightComponent(startMenuPane);

	}

	// Variables declaration - do not modify                     

	// End of variables declaration                   
}
