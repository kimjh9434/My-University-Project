
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client_GUI_Form;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Client_Function.C_ClientThread;
import Client_Function.C_WaitingRoom;

@SuppressWarnings("serial")
public class GUI_JoinRoomJFrame extends javax.swing.JFrame {

	JFrame prevJFrame;
	GUI_GameRoomJFrame gameRoom;
	C_WaitingRoom c_WaitingRoom;
	int roomIndex = -1;

	public GUI_JoinRoomJFrame(JFrame prevJFrame, C_WaitingRoom c_WaitingRoom) {
		initComponents();
		this.prevJFrame = prevJFrame;
		this.prevJFrame.setVisible(false);
		this.c_WaitingRoom = c_WaitingRoom;
		setLocationRelativeTo(null);
		setVisible(true);
		c_WaitingRoom.enterJoinRoom(this);
	}

	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		JoinRoomOK_jButton = new javax.swing.JButton();
		Back_jButton = new javax.swing.JButton();
		jLabel2 = new javax.swing.JLabel();
		roomName_jTextField = new javax.swing.JTextField();
		jLabel3 = new javax.swing.JLabel();
		roomPwd_jTextField = new javax.swing.JTextField();
		jLabel4 = new javax.swing.JLabel();
		jScrollPane2 = new javax.swing.JScrollPane();
		roomList_jList = new javax.swing.JList<>();
		rommListUpdate_jButton = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("방 참여 화면");

		jLabel1.setFont(new java.awt.Font("굴림", 1, 48)); // NOI18N
		jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel1.setText("방참여");

		JoinRoomOK_jButton.setFont(new java.awt.Font("굴림", 0, 24)); // NOI18N
		JoinRoomOK_jButton.setText("입장");
		JoinRoomOK_jButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				JoinRoomOK_jButtonActionPerformed(evt);
			}
		});

		Back_jButton.setFont(new java.awt.Font("굴림", 0, 24)); // NOI18N
		Back_jButton.setText("돌아가기");
		Back_jButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				Back_jButtonActionPerformed(evt);
			}
		});

		jLabel2.setFont(new java.awt.Font("굴림", 0, 24)); // NOI18N
		jLabel2.setText("방 이름 :");

		roomName_jTextField.setFont(new java.awt.Font("굴림", 0, 24)); // NOI18N

		jLabel3.setFont(new java.awt.Font("굴림", 0, 24)); // NOI18N
		jLabel3.setText("방 비밀번호 :");

		jLabel4.setFont(new java.awt.Font("굴림", 0, 24)); // NOI18N
		jLabel4.setText("개인전 방 전체 목록 : ");

		roomList_jList.setFont(new java.awt.Font("굴림", 0, 24)); // NOI18N
		roomList_jList.setModel(new javax.swing.AbstractListModel<String>() {
			String[] strings = {};

			public int getSize() {
				return strings.length;
			}

			public String getElementAt(int i) {
				return strings[i];
			}
		});
		jScrollPane2.setViewportView(roomList_jList);

		JListHandler handler = new JListHandler();
		roomList_jList.addListSelectionListener(handler);

		rommListUpdate_jButton.setFont(new java.awt.Font("굴림", 0, 24)); // NOI18N
		rommListUpdate_jButton.setText("방목록 갱신");
		rommListUpdate_jButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				rommListUpdate_jButtonActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup().addComponent(jLabel4)
												.addGap(0, 96, Short.MAX_VALUE))
										.addComponent(jScrollPane2))
								.addGap(18, 18, 18)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(jLabel2)
										.addGroup(layout.createSequentialGroup().addComponent(JoinRoomOK_jButton)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(Back_jButton))
										.addComponent(roomName_jTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jLabel3)
										.addComponent(roomPwd_jTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(rommListUpdate_jButton, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
				.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap().addComponent(jLabel1).addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel2)
						.addComponent(jLabel4))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addComponent(roomName_jTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18).addComponent(jLabel3)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(roomPwd_jTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(rommListUpdate_jButton)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(JoinRoomOK_jButton).addComponent(Back_jButton)))
						.addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE))
				.addContainerGap()));

		pack();
	}

	// 입장 버튼을 눌렀을 때,
	private void JoinRoomOK_jButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// 1. 방 비밀번호를 받고,
		String roomPwd = roomPwd_jTextField.getText();

		// 방 비밀번호가 빈값이면,
		if (roomPwd.equals("")) {
			// 기본값으로 채운다.
			roomPwd = "defaultPwd";
		}
		if (roomIndex != -1) {
			// 2. 게임방 입장을 시도 한다.
			if (c_WaitingRoom.JoinRoom(roomIndex, roomPwd)) {
				// 게임방 입장 성공
				System.out.println("게임방 화면 이동");
				gameRoom = new GUI_GameRoomJFrame(this, c_WaitingRoom.c_GameRoom);
			} // 그 외는 게임방 입장 실패
		} else {
			JOptionPane.showMessageDialog(null, "방이 선택되지 않았습니다.", "방 입장 불가", JOptionPane.WARNING_MESSAGE);
		}

	}

	// 뒤로가기 버튼을 눌렀을 때,
	private void Back_jButtonActionPerformed(java.awt.event.ActionEvent evt) {
		this.prevJFrame.setVisible(true);
		this.dispose();
	}

	private void rommListUpdate_jButtonActionPerformed(java.awt.event.ActionEvent evt) {
		c_WaitingRoom.enterJoinRoom(this);
	}

	private class JListHandler implements ListSelectionListener {
		// 리스트의 항목이 선택이 되면, 순서와 방이름을 받는다.
		public void valueChanged(ListSelectionEvent event) {
			roomIndex = (int) roomList_jList.getSelectedIndex();
			String roomName = (String) roomList_jList.getSelectedValue();
			roomName_jTextField.setText(roomName);
		}
	}

	private javax.swing.JButton Back_jButton;
	private javax.swing.JButton JoinRoomOK_jButton;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JButton rommListUpdate_jButton;
	public javax.swing.JList<String> roomList_jList;
	private javax.swing.JTextField roomName_jTextField;
	private javax.swing.JTextField roomPwd_jTextField;
}
