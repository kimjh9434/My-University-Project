/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client_GUI_Form;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Client_Function.C_ClientThread;
import Client_Function.C_WaitingRoom;

@SuppressWarnings("serial")
public class GUI_CreateRoomJFrame extends javax.swing.JFrame {

	JFrame prevJFrame;
	GUI_GameRoomJFrame gameRoom;
	C_WaitingRoom c_WaitingRoom;

	public GUI_CreateRoomJFrame(JFrame prevJFrame, C_WaitingRoom c_WaitingRoom) {
		initComponents();
		this.prevJFrame = prevJFrame;
		this.prevJFrame.setVisible(false);
		this.c_WaitingRoom = c_WaitingRoom;
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		roomName_jTextField = new javax.swing.JTextField("테트리스 한판!!!");
		roomPwd_jTextField = new javax.swing.JTextField();
		jLabel4 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		jTextField1 = new javax.swing.JTextField();
		jTextField2 = new javax.swing.JTextField();
		CreateRoomOK_jButton = new javax.swing.JButton();
		Back_jButton = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jLabel1.setFont(new java.awt.Font("굴림", 0, 48)); // NOI18N
		jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel1.setText("방생성");

		jLabel2.setFont(new java.awt.Font("굴림", 0, 24)); // NOI18N
		jLabel2.setText("방 이름 : ");

		jLabel3.setFont(new java.awt.Font("굴림", 0, 24)); // NOI18N
		jLabel3.setText("방 비밀번호 : ");

		roomName_jTextField.setFont(new java.awt.Font("굴림", 0, 24)); // NOI18N

		roomPwd_jTextField.setFont(new java.awt.Font("굴림", 0, 24)); // NOI18N

		jLabel4.setFont(new java.awt.Font("굴림", 0, 24)); // NOI18N
		jLabel4.setText("게임 유형 : ");

		jLabel5.setFont(new java.awt.Font("굴림", 0, 24)); // NOI18N
		jLabel5.setText("인원수 : ");

		jTextField1.setEditable(false);
		jTextField1.setFont(new java.awt.Font("굴림", 0, 24)); // NOI18N
		jTextField1.setText("개인전");

		jTextField2.setEditable(false);
		jTextField2.setFont(new java.awt.Font("굴림", 0, 24)); // NOI18N
		jTextField2.setText("2~5명");

		CreateRoomOK_jButton.setFont(new java.awt.Font("굴림", 0, 24)); // NOI18N
		CreateRoomOK_jButton.setText("생성");
		CreateRoomOK_jButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				CreateRoomOK_jButtonActionPerformed(evt);
			}
		});

		Back_jButton.setFont(new java.awt.Font("굴림", 0, 24)); // NOI18N
		Back_jButton.setText("뒤로가기");
		Back_jButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				Back_jButtonActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(jLabel3)
										.addComponent(roomName_jTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(roomPwd_jTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jLabel2))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95,
										Short.MAX_VALUE)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
												layout.createSequentialGroup().addComponent(CreateRoomOK_jButton)
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.RELATED)
														.addComponent(Back_jButton))
										.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
												layout.createSequentialGroup().addGroup(layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(jLabel5)
														.addComponent(jTextField2,
																javax.swing.GroupLayout.PREFERRED_SIZE, 200,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jTextField1,
																javax.swing.GroupLayout.PREFERRED_SIZE, 200,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel4)).addGap(77, 77, 77)))))
				.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jLabel1).addGap(45, 45, 45)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel2).addComponent(jLabel4))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(roomName_jTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addGap(12, 12, 12).addComponent(jLabel3))
								.addGroup(layout.createSequentialGroup()
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jLabel5)))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(roomPwd_jTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(CreateRoomOK_jButton).addComponent(Back_jButton))
						.addContainerGap()));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	// 방 생성 버튼을 눌렀을 때,
	private void CreateRoomOK_jButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// 1. 방 이름과, 방 비밀번호를 받고,
		String roomName = roomName_jTextField.getText();
		String roomPwd = roomPwd_jTextField.getText();

		// 2. 방이름이 널이 아닌지 확인한다. [방이름은 빈칸이면 안된다]
		if (roomName.equals("")) {
			JOptionPane.showMessageDialog(null, "방 이름은 빈 값이 있으면 안됩니다.", "방 생성 조건",
					JOptionPane.WARNING_MESSAGE);
		} else {
			// 방 비밀번호가 빈값이면,
			if(roomPwd.equals("")){
				// 기본값으로 채운다.
				roomPwd = "defaultPwd";
			}
			
			// 2. 게임방을 만든다.
			c_WaitingRoom.createRoom(roomName, roomPwd);

			// 3. 게임방으로 이동한다.
			System.out.println("게임방 화면 이동");
			gameRoom = new GUI_GameRoomJFrame(this, c_WaitingRoom.c_GameRoom);
		}
	}

	// 뒤로가기 버튼을 눌렀을 때,
	private void Back_jButtonActionPerformed(java.awt.event.ActionEvent evt) {
		this.prevJFrame.setVisible(true);
		this.dispose();
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton Back_jButton;
	private javax.swing.JButton CreateRoomOK_jButton;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JTextField jTextField1;
	private javax.swing.JTextField jTextField2;
	private javax.swing.JTextField roomName_jTextField;
	private javax.swing.JTextField roomPwd_jTextField;
	// End of variables declaration//GEN-END:variables
}
