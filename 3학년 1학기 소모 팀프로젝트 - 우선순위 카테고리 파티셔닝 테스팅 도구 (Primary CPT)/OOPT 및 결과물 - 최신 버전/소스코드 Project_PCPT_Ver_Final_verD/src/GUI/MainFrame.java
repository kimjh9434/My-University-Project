package GUI;

import System.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.border.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Enumeration;

public class MainFrame extends JFrame {
	private FileManager fm;
	private Specification spec;
	private JPanel mainPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	/**
	 * Create the frame.
	 */
	public void expandAllNodes(JTree tree, int startingIndex, int rowCount){
		for(int i=startingIndex;i<rowCount;++i){
			tree.expandRow(i);
		}

		if(tree.getRowCount()!=rowCount){
			expandAllNodes(tree, rowCount, tree.getRowCount());
		}
	}

	/*public MainFrame(){

	}*/

	public MainFrame(FileManager manager, Specification s) {
		fm = manager;
		spec =s;
		setTitle(fm.getPath());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 658, 418);
		mainPane = new JPanel();
		mainPane.setBounds(this.getBounds());
		mainPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPane);
		mainPane.setLayout(new BorderLayout(0, 0));

		JPanel subPane = new JPanel();
		subPane.setBounds(mainPane.getBounds());
		mainPane.add(subPane,BorderLayout.CENTER);
		subPane.setLayout(new BorderLayout(0, 0));

		JSplitPane contentDivPane = new JSplitPane();
		contentDivPane.setBounds(subPane.getBounds());
		contentDivPane.setDividerLocation(subPane.getWidth()/4);
		subPane.add(contentDivPane);

		JPanel detailPane = new JPanel();
		detailPane.setBounds(contentDivPane.getBounds().x+(contentDivPane.getWidth()/4),contentDivPane.getBounds().y,contentDivPane.getBounds().width*3/4,contentDivPane.getBounds().height);
		detailPane.setLayout(new BorderLayout(0, 0));
		contentDivPane.setRightComponent(detailPane);

		ButtonGroup priorityGroup = new ButtonGroup();

		JPanel radioPane = new JPanel();
		detailPane.add(radioPane, BorderLayout.SOUTH);
		radioPane.setLayout(new BorderLayout(0, 0));

		JPanel priorityPanel = new JPanel();
		priorityPanel.setBorder(null);
		radioPane.add(priorityPanel, BorderLayout.SOUTH);

		JLabel priorityLabel = new JLabel("Priority");
		priorityPanel.add(priorityLabel);

		JRadioButton priority1 = new JRadioButton("1");
		priorityGroup.add(priority1);
		priorityPanel.add(priority1);

		JRadioButton priority2 = new JRadioButton("2");
		priorityGroup.add(priority2);
		priorityPanel.add(priority2);

		JRadioButton priority3 = new JRadioButton("3");
		priorityGroup.add(priority3);
		priorityPanel.add(priority3);

		JRadioButton priority4 = new JRadioButton("4");
		priorityGroup.add(priority4);
		priorityPanel.add(priority4);

		JRadioButton priority5 = new JRadioButton("5");
		priorityGroup.add(priority5);
		priorityPanel.add(priority5);

		JPanel ErrorNSingle = new JPanel();
		radioPane.add(ErrorNSingle, BorderLayout.CENTER);

		JLabel lblErrorSingle = new JLabel("Error & Single");
		ErrorNSingle.add(lblErrorSingle);

		JRadioButton nopBtn = new JRadioButton("Nop");
		buttonGroup.add(nopBtn);
		ErrorNSingle.add(nopBtn);

		JRadioButton errorBtn = new JRadioButton("Error");
		buttonGroup.add(errorBtn);
		ErrorNSingle.add(errorBtn);

		JRadioButton singleBtn = new JRadioButton("Single");
		buttonGroup.add(singleBtn);
		ErrorNSingle.add(singleBtn);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerLocation(detailPane.getWidth()/2);
		detailPane.add(splitPane);

		JPanel p_rootPane = new JPanel();
		p_rootPane.setLayout(new BorderLayout(0, 0));
		splitPane.setLeftComponent(p_rootPane);

		JScrollPane p_scrollPane = new JScrollPane();
		p_rootPane.add(p_scrollPane, BorderLayout.CENTER);

		JList<String> propertyList = new JList<>();
		p_scrollPane.setViewportView(propertyList);

		JPanel p_btnPane = new JPanel();
		p_rootPane.add(p_btnPane, BorderLayout.SOUTH);

		JButton p_addBtn = new JButton("추가");
		p_btnPane.add(p_addBtn);

		JButton p_delBtn = new JButton("제거");
		p_btnPane.add(p_delBtn);

		JButton p_editBtn = new JButton("편집");
		p_btnPane.add(p_editBtn);

		JLabel propertyLabel = new JLabel("PROPERTY");
		propertyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		p_rootPane.add(propertyLabel, BorderLayout.NORTH);

		JPanel if_rootPane = new JPanel();
		splitPane.setRightComponent(if_rootPane);
		if_rootPane.setLayout(new BorderLayout(0, 0));

		JScrollPane if_scrollPane = new JScrollPane();
		if_rootPane.add(if_scrollPane, BorderLayout.CENTER);

		JList<String> ifList = new JList<>();
		if_scrollPane.setViewportView(ifList);

		JPanel if_btnPane = new JPanel();
		if_rootPane.add(if_btnPane, BorderLayout.SOUTH);

		JButton if_addBtn = new JButton("추가");
		if_btnPane.add(if_addBtn);

		JButton if_delBtn = new JButton("제거");
		if_btnPane.add(if_delBtn);

		JButton if_editBtn = new JButton("편집");
		if_btnPane.add(if_editBtn);

		JLabel ifLabel = new JLabel("IF-PROPERTY");
		ifLabel.setHorizontalAlignment(SwingConstants.CENTER);
		if_rootPane.add(ifLabel, BorderLayout.NORTH);

		JPanel leftPane = new JPanel();
		contentDivPane.setLeftComponent(leftPane);
		leftPane.setBounds(contentDivPane.getBounds().x,contentDivPane.getBounds().y,contentDivPane.getBounds().width/4,contentDivPane.getBounds().height);
		leftPane.setLayout(new BorderLayout(0, 0));

		JScrollPane treePane = new JScrollPane();

		JPanel btnPane = new JPanel();
		leftPane.add(btnPane, BorderLayout.SOUTH);

		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Categories");
		JTree tree = new JTree(root);

		for(int i=0;i<spec.getNumberOfCategories();i++){
			root.add(new DefaultMutableTreeNode(spec.getCategory(i).getCategoryName()));
			for(int j=0;j<spec.getCategory(i).getNumberOfRepresentativeValues();j++){
				((DefaultMutableTreeNode)root.getChildAt(i)).add(new DefaultMutableTreeNode(spec.getCategory(i).getRepresentativeValue(j).getRepresentativeValueName()));
			}
		}

		treePane.setViewportView(tree);
		expandAllNodes(tree, 0, tree.getRowCount());
		tree.updateUI();

		tree.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				Object o = tree.getLastSelectedPathComponent();
				DefaultMutableTreeNode d = (DefaultMutableTreeNode)o;
				if(d != null){
					if(d.getLevel()==2){
						int categoryIndex = d.getParent().getParent().getIndex(d.getParent());
						int representativeIndex = d.getParent().getIndex(d);
						if(spec.getCategory(categoryIndex).getRepresentativeValue(representativeIndex).getNumberOfPropertyConstraints()!=0){
							propertyList.setModel(new javax.swing.AbstractListModel<String>() {
								public int getSize() { return spec.getCategory(categoryIndex).getRepresentativeValue(representativeIndex).getNumberOfPropertyConstraints(); }
								public String getElementAt(int i) { return spec.getCategory(categoryIndex).getRepresentativeValue(representativeIndex).getProperty(i).getName(); }
							});
							propertyList.updateUI();
						}else{
							propertyList.setModel(new javax.swing.AbstractListModel<String>() {
								public int getSize() { return 0; }
								public String getElementAt(int i) { return null; }
							});
							propertyList.updateUI();
						}
						if(spec.getCategory(categoryIndex).getRepresentativeValue(representativeIndex).getNumberOfIfPropertyConstraints()!=0){
							ifList.setModel(new javax.swing.AbstractListModel<String>() {
								public int getSize() { return spec.getCategory(categoryIndex).getRepresentativeValue(representativeIndex).getNumberOfIfPropertyConstraints(); }
								public String getElementAt(int i) { return spec.getCategory(categoryIndex).getRepresentativeValue(representativeIndex).getIfProperty(i).getName(); }
							});
							ifList.updateUI();
						}else{
							ifList.setModel(new javax.swing.AbstractListModel<String>() {
								public int getSize() { return 0; }
								public String getElementAt(int i) { return null; }
							});
							ifList.updateUI();
						}
						Enumeration<AbstractButton> pbuttons = priorityGroup.getElements();
						for(int i=0;i<priorityGroup.getButtonCount();i++){
							AbstractButton btn = pbuttons.nextElement();
							if(i+1==spec.getCategory(categoryIndex).getRepresentativeValue(representativeIndex).getPriorityRank())
							{
								btn.setSelected(true);
								break;
							}
						}
						Enumeration<AbstractButton> ebuttons = buttonGroup.getElements();
						for(int i=0;i<priorityGroup.getButtonCount();i++){
							AbstractButton btn = ebuttons.nextElement();
							if(i==spec.getCategory(categoryIndex).getRepresentativeValue(representativeIndex).getSingleError())
							{
								btn.setSelected(true);
								break;
							}
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

		p_addBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String s = JOptionPane.showInputDialog(null, "이름입력", "PropertyName", 3);
				Object o = tree.getLastSelectedPathComponent();
				DefaultMutableTreeNode d = (DefaultMutableTreeNode)o;
				if(d != null){
					if(d.getLevel()==2){
						int categoryIndex = d.getParent().getParent().getIndex(d.getParent());
						int representativeIndex = d.getParent().getIndex(d);
						if(s!=null && s.compareTo("")!=0){
							spec.getCategory(categoryIndex).getRepresentativeValue(representativeIndex).addProperty(s);
							propertyList.setModel(new javax.swing.AbstractListModel<String>() {
								public int getSize() { return spec.getCategory(categoryIndex).getRepresentativeValue(representativeIndex).getNumberOfPropertyConstraints(); }
								public String getElementAt(int i) { return spec.getCategory(categoryIndex).getRepresentativeValue(representativeIndex).getProperty(i).getName(); }
							});
							propertyList.updateUI();
						}	
					}
				}
			}
		});

		if_addBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String s = JOptionPane.showInputDialog(null, "이름입력", "IfPropertyName", 3);
				Object o = tree.getLastSelectedPathComponent();
				DefaultMutableTreeNode d = (DefaultMutableTreeNode)o;
				if(d != null && s!=null){
					if(d.getLevel()==2){
						int categoryIndex = d.getParent().getParent().getIndex(d.getParent());
						int representativeIndex = d.getParent().getIndex(d);
						spec.getCategory(categoryIndex).getRepresentativeValue(representativeIndex).addIfProperty(s);
						ifList.setModel(new javax.swing.AbstractListModel<String>() {
							public int getSize() { return spec.getCategory(categoryIndex).getRepresentativeValue(representativeIndex).getNumberOfIfPropertyConstraints(); }
							public String getElementAt(int i) { return spec.getCategory(categoryIndex).getRepresentativeValue(representativeIndex).getIfProperty(i).getName(); }
						});
						ifList.updateUI();
					}
				}
			}
		});

		p_delBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Object o = tree.getLastSelectedPathComponent();
				DefaultMutableTreeNode d = (DefaultMutableTreeNode)o;
				if(d != null&&(!propertyList.isSelectionEmpty())){
					if(d.getLevel()==2){
						int categoryIndex = d.getParent().getParent().getIndex(d.getParent());
						int representativeIndex = d.getParent().getIndex(d);
						spec.getCategory(categoryIndex).getRepresentativeValue(representativeIndex).delProperty(propertyList.getSelectedIndex());
						spec.updateData();
						propertyList.updateUI();
						ifList.updateUI();
					}
				}
			}
		});

		if_delBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Object o = tree.getLastSelectedPathComponent();
				DefaultMutableTreeNode d = (DefaultMutableTreeNode)o;
				if(d != null&&(!ifList.isSelectionEmpty())){
					if(d.getLevel()==2){
						int categoryIndex = d.getParent().getParent().getIndex(d.getParent());
						int representativeIndex = d.getParent().getIndex(d);
						spec.getCategory(categoryIndex).getRepresentativeValue(representativeIndex).delIfProperty(ifList.getSelectedIndex());
						propertyList.updateUI();
						ifList.updateUI();
					}
				}
			}
		});

		p_editBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Object o = tree.getLastSelectedPathComponent();
				DefaultMutableTreeNode d = (DefaultMutableTreeNode)o;
				if(d != null&&(!propertyList.isSelectionEmpty())){
					if(d.getLevel()==2){
						String s = JOptionPane.showInputDialog(null, "이름변경", "PropertyName", 3);
						int categoryIndex = d.getParent().getParent().getIndex(d.getParent());
						int representativeIndex = d.getParent().getIndex(d);
						if(s!=null && s.compareTo("")!=0){
							int ret =spec.getCategory(categoryIndex).getRepresentativeValue(representativeIndex).setProperty(propertyList.getSelectedIndex(),s);
							if(ret ==0){
								propertyList.updateUI();
								ifList.updateUI();	
							}
						}

					}
				}
			}
		});

		if_editBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Object o = tree.getLastSelectedPathComponent();
				DefaultMutableTreeNode d = (DefaultMutableTreeNode)o;
				if(d != null&&(!ifList.isSelectionEmpty())){
					if(d.getLevel()==2){
						String s = JOptionPane.showInputDialog(null, "이름변경", "IfPropertyName", 3);
						int categoryIndex = d.getParent().getParent().getIndex(d.getParent());
						int representativeIndex = d.getParent().getIndex(d);
						if(s!=null && s.compareTo("")!=0){
							int ret = spec.getCategory(categoryIndex).getRepresentativeValue(representativeIndex).setIfProperty(ifList.getSelectedIndex(),s);
							if(ret==0){
								propertyList.updateUI();
								ifList.updateUI();
							}
						}
					}
				}
			}
		});

		nopBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Object o = tree.getLastSelectedPathComponent();
				DefaultMutableTreeNode d = (DefaultMutableTreeNode)o;
				if(d != null){
					if(d.getLevel()==2){
						int categoryIndex = d.getParent().getParent().getIndex(d.getParent());
						int representativeIndex = d.getParent().getIndex(d);
						spec.getCategory(categoryIndex).getRepresentativeValue(representativeIndex).setSingleError(0);
					}
				}
			}
		});

		errorBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Object o = tree.getLastSelectedPathComponent();
				DefaultMutableTreeNode d = (DefaultMutableTreeNode)o;
				if(d != null){
					if(d.getLevel()==2){
						int categoryIndex = d.getParent().getParent().getIndex(d.getParent());
						int representativeIndex = d.getParent().getIndex(d);
						spec.getCategory(categoryIndex).getRepresentativeValue(representativeIndex).setSingleError(1);
					}
				}
			}
		});

		singleBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Object o = tree.getLastSelectedPathComponent();
				DefaultMutableTreeNode d = (DefaultMutableTreeNode)o;
				if(d != null){
					if(d.getLevel()==2){
						int categoryIndex = d.getParent().getParent().getIndex(d.getParent());
						int representativeIndex = d.getParent().getIndex(d);
						spec.getCategory(categoryIndex).getRepresentativeValue(representativeIndex).setSingleError(2);
					}
				}
			}
		});

		Enumeration<AbstractButton> buttons = priorityGroup.getElements();
		for(int i=0;i<priorityGroup.getButtonCount();i++){
			AbstractButton btn = buttons.nextElement();
			btn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					Object o = tree.getLastSelectedPathComponent();
					DefaultMutableTreeNode d = (DefaultMutableTreeNode)o;
					if(d != null){
						if(d.getLevel()==2){
							int categoryIndex = d.getParent().getParent().getIndex(d.getParent());
							int representativeIndex = d.getParent().getIndex(d);
							spec.getCategory(categoryIndex).getRepresentativeValue(representativeIndex).setPriorityRank(Integer.parseInt(((JRadioButton)e.getSource()).getText()));
						}
					}
				}
			});
		}

		leftPane.add(treePane,BorderLayout.CENTER);

		JButton addBtn = new JButton("추가");
		addBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(tree.getLastSelectedPathComponent()!=null)
				{
					Object o = tree.getLastSelectedPathComponent();
					DefaultMutableTreeNode d = (DefaultMutableTreeNode)o;
					String s;
					switch(d.getLevel()){
					case 0 :
						s = JOptionPane.showInputDialog("카테고리 이름 입력");
						if(s==null){
							break;
						}
						if(spec.addCategory(s)==0){
							d.add(new DefaultMutableTreeNode(s));
							expandAllNodes(tree,0,tree.getRowCount());
							tree.updateUI();
						}
						break;
					case 1 :
						s = JOptionPane.showInputDialog("values 이름 입력");
						if(s==null){
							break;
						}
						int categoryIndex = d.getParent().getIndex(d);
						if(spec.getCategory(categoryIndex).addRepresentativeValue(s)==0){
							d.add(new DefaultMutableTreeNode(s));
							expandAllNodes(tree,0,tree.getRowCount());
							tree.updateUI();
						}
						break;
					default :
						break;
					}
				}
			}
		});
		btnPane.add(addBtn);

		JButton delBtn = new JButton("제거");
		delBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tree.getLastSelectedPathComponent()!=null)
				{
					Object o = tree.getLastSelectedPathComponent();
					DefaultMutableTreeNode d = (DefaultMutableTreeNode)o;
					switch(d.getLevel()){
					case 1:
						int categoryIndex = d.getParent().getIndex(d);
						spec.delCategory(categoryIndex);
						spec.updateData();
						d.removeFromParent();
						tree.updateUI();
						break;
					case 2:
						int cIndex = d.getParent().getParent().getIndex(d.getParent());
						int rIndex = d.getParent().getIndex(d);
						spec.getCategory(cIndex).delRepresentativeValue(rIndex);
						spec.updateData();
						d.removeFromParent();
						tree.updateUI();
						break;
					default:
						break;
					}

				}
			}
		});
		btnPane.add(delBtn);

		JButton editBtn = new JButton("편집");
		editBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tree.getLastSelectedPathComponent()!=null)
				{
					Object o = tree.getLastSelectedPathComponent();
					DefaultMutableTreeNode d = (DefaultMutableTreeNode)o;
					String s;
					if(d.getLevel()==1){
						s = JOptionPane.showInputDialog(null, "새로운 카테고리 이름 입력");
						if(s!=null && s.compareTo("")!=0){
							int ret = spec.setCategory(d.getParent().getIndex(d),s);
							if(ret == 0){
								d.setUserObject(new DefaultMutableTreeNode(s));
								tree.updateUI();
							}
						}
					}else if(d.getLevel()==2){
						s = JOptionPane.showInputDialog(null, "새로운 values 이름 입력");
						if(s!=null && s.compareTo("")!=0){
							int ret = spec.getCategory(d.getParent().getParent().getIndex(d.getParent())).setRepresentativeValue(d.getParent().getIndex(d), s);
							if(ret==0){
								d.setUserObject(new DefaultMutableTreeNode(s));
								tree.updateUI();
							}
						}
					}else{
					}
				}
			}
		});
		btnPane.add(editBtn); 

		JMenuBar menuBar = new JMenuBar();

		mainPane.add(menuBar, BorderLayout.NORTH);

		JMenu menu1 = new JMenu("파일");
		menuBar.add(menu1);

		JMenuItem menuItem1 = new JMenuItem("저장");
		menuItem1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				fm.saveSpecification();
			}
		});
		menu1.add(menuItem1);

		JMenuItem menuItem2 = new JMenuItem("다른 이름으로 저장");
		menuItem2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int ret = new Explorer().doit(1, fm, spec);
				if(ret==0){
					MainFrame.this.setTitle(fm.getPath());
					JOptionPane.showMessageDialog(null, "잘 저장되었습니다!", "Done!", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		menu1.add(menuItem2);

		JMenuItem menuItem4 = new JMenuItem("TestCase 생성 및 엑셀 저장");
		menuItem4.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int ret = spec.generateTestCase();
				if(ret>0){
					JOptionPane.showMessageDialog(null, "총 "+ret+"개의 testCase가 생성되었습니다!");
					String s = JOptionPane.showInputDialog("엑셀파일명 입력")+".xls";
					spec.export2excel(s);
				}
				else{
					JOptionPane.showMessageDialog(null, "TestCase를 만들기 위한 조건이 성립되지 않습니다!");
				}
			}
		});
		menu1.add(menuItem4);

		JMenuItem menuItem3 = new JMenuItem("종료");
		menuItem3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.exit(0);	
			}
		});
		menu1.add(menuItem3);

	}
}
