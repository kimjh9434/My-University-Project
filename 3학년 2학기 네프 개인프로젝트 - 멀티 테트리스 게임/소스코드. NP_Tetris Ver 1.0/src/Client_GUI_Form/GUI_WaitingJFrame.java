
package Client_GUI_Form;

import javax.swing.JFrame;

import Client_Function.C_ClientThread;
import Client_Function.C_WaitingRoom;

@SuppressWarnings("serial")
public class GUI_WaitingJFrame extends javax.swing.JFrame {

	JFrame prevJFrame;
	C_WaitingRoom c_WaitingRoom;
	GUI_CreateRoomJFrame CreateRoom;
	GUI_JoinRoomJFrame joinRoom;

	public GUI_WaitingJFrame(JFrame prevJFrame, C_WaitingRoom c_WaitingRoom) {
		initComponents();
		this.prevJFrame = prevJFrame;
		this.prevJFrame.setVisible(false);
		setLocationRelativeTo(null);
		setVisible(true);
		this.c_WaitingRoom = c_WaitingRoom;
		this.c_WaitingRoom.enterWatingRoom(this);
	}

	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		accListNum_jLabel = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		CreateRoom_jButton = new javax.swing.JButton();
		JoinRoom_jButton = new javax.swing.JButton();
		Quit_jButton = new javax.swing.JButton();
		jScrollPane1 = new javax.swing.JScrollPane();
		totalChat_jTextArea = new javax.swing.JTextArea();
		jScrollPane2 = new javax.swing.JScrollPane();
		accList_jTextArea = new javax.swing.JTextArea();
		inputChat_jTextField = new javax.swing.JTextField();
		QuickMatch_jButton = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("대기실 화면");
		setPreferredSize(new java.awt.Dimension(630, 400));

		jLabel1.setFont(new java.awt.Font("굴림", 1, 48)); // NOI18N
		jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel1.setText("Welcome to NP Tetris");

		jLabel2.setText("전체 체팅");

		jLabel3.setText("접속자 목록(");

		accListNum_jLabel.setText("0");

		jLabel5.setText("명)");

		CreateRoom_jButton.setFont(new java.awt.Font("굴림", 0, 24)); // NOI18N
		CreateRoom_jButton.setText("방생성");
		CreateRoom_jButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				CreateRoom_jButtonActionPerformed(evt);
			}
		});

		JoinRoom_jButton.setFont(new java.awt.Font("굴림", 0, 24)); // NOI18N
		JoinRoom_jButton.setText("방참여");
		JoinRoom_jButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				JoinRoom_jButtonActionPerformed(evt);
			}
		});

		Quit_jButton.setFont(new java.awt.Font("굴림", 0, 24)); // NOI18N
		Quit_jButton.setText("종료");
		Quit_jButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				Quit_jButtonActionPerformed(evt);
			}
		});

		totalChat_jTextArea.setEditable(false);
		totalChat_jTextArea.setColumns(20);
		totalChat_jTextArea.setRows(5);
		jScrollPane1.setViewportView(totalChat_jTextArea);

		accList_jTextArea.setEditable(false);
		accList_jTextArea.setColumns(20);
		accList_jTextArea.setRows(5);
		jScrollPane2.setViewportView(accList_jTextArea);

		inputChat_jTextField.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				inputChat_jTextFieldActionPerformed(evt);
			}
		});

		QuickMatch_jButton.setFont(new java.awt.Font("굴림", 1, 16)); // NOI18N
		QuickMatch_jButton.setText("빠른대전");
		QuickMatch_jButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				QuickMatch_jButtonActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 564,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addGroup(layout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
												.addComponent(Quit_jButton, javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(CreateRoom_jButton, javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(JoinRoom_jButton, javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addComponent(QuickMatch_jButton, javax.swing.GroupLayout.PREFERRED_SIZE, 105,
												javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
												.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300,
														Short.MAX_VALUE)
												.addComponent(inputChat_jTextField)))
								.addGroup(layout.createSequentialGroup().addGap(115, 115, 115).addComponent(jLabel2)))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup().addComponent(jLabel3)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(accListNum_jLabel, javax.swing.GroupLayout.PREFERRED_SIZE,
														26, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(jLabel5))
										.addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 159,
												javax.swing.GroupLayout.PREFERRED_SIZE))))
				.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap().addComponent(jLabel1)
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel2)
						.addComponent(jLabel3).addComponent(accListNum_jLabel).addComponent(jLabel5))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
						.addGroup(layout.createSequentialGroup()
								.addComponent(CreateRoom_jButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(JoinRoom_jButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(11, 11, 11)
								.addComponent(QuickMatch_jButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(10, 10, 10).addComponent(Quit_jButton, javax.swing.GroupLayout.PREFERRED_SIZE,
										50, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGroup(javax.swing.GroupLayout.Alignment.LEADING,
								layout.createSequentialGroup()
										.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 186,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(11, 11, 11).addComponent(inputChat_jTextField,
												javax.swing.GroupLayout.PREFERRED_SIZE, 35,
												javax.swing.GroupLayout.PREFERRED_SIZE))
						.addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING))
				.addContainerGap(28, Short.MAX_VALUE)));
		pack();
	}

	// 방 생성하기 버튼을 눌렀을 때,
	private void CreateRoom_jButtonActionPerformed(java.awt.event.ActionEvent evt) {
		CreateRoom = new GUI_CreateRoomJFrame(this, c_WaitingRoom);
	}

	// 방 참여하기 버튼을 눌렀을 때,
	private void JoinRoom_jButtonActionPerformed(java.awt.event.ActionEvent evt) {
		joinRoom = new GUI_JoinRoomJFrame(this, c_WaitingRoom);
	}

	// 종료하기 버튼을 눌렀을 때,
	private void Quit_jButtonActionPerformed(java.awt.event.ActionEvent evt) {
		c_WaitingRoom.quit();// 서버와의 접속을 끊는다.
		((GUI_LoginJFrame) this.prevJFrame).prevJFrame.setVisible(true);
		this.dispose();
		this.prevJFrame.dispose();
	}

	// 채팅 값을 입력하고 엔터키를 눌렀을 때,
	private void inputChat_jTextFieldActionPerformed(java.awt.event.ActionEvent evt) {
		c_WaitingRoom.sendTalk();
	}

	// 빠른대전 버튼을 눌렀을 때,
	private void QuickMatch_jButtonActionPerformed(java.awt.event.ActionEvent evt) {
		c_WaitingRoom.quickMatch();
	}

	private javax.swing.JButton CreateRoom_jButton;
	private javax.swing.JButton JoinRoom_jButton;
	private javax.swing.JButton QuickMatch_jButton;
	private javax.swing.JButton Quit_jButton;
	public javax.swing.JLabel accListNum_jLabel;
	public javax.swing.JTextArea accList_jTextArea;
	public javax.swing.JTextField inputChat_jTextField;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	public javax.swing.JTextArea totalChat_jTextArea;
}
