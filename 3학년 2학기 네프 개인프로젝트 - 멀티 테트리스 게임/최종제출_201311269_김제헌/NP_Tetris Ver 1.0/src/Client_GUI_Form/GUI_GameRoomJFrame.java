/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client_GUI_Form;

import javax.swing.JFrame;

import Client_Function.C_GameRoom;

@SuppressWarnings("serial")
public class GUI_GameRoomJFrame extends javax.swing.JFrame {

	JFrame prevJFrame;
	GUI_MultiTetrisJFrame multiTetrisJFrame;
	C_GameRoom c_GameRoom;

	// 기존의 상태값
	public boolean state = false;
	public String payer1_combo = "Open";
	public String payer2_combo = "Open";
	public String payer3_combo = "Open";
	public String payer4_combo = "Open";
	public String payer5_combo = "Open";
	public String payer1Ready_combo = "Not ready";
	public String payer2Ready_combo = "Not ready";
	public String payer3Ready_combo = "Not ready";
	public String payer4Ready_combo = "Not ready";
	public String payer5Ready_combo = "Not ready";
	
	String[] nameList = {"", "", "", "", ""};

	public GUI_GameRoomJFrame(JFrame prevJFrame, C_GameRoom c_GameRoom) {
		initComponents();
		this.prevJFrame = prevJFrame;
		this.prevJFrame.setVisible(false);
		this.c_GameRoom = c_GameRoom;
		setLocationRelativeTo(null);
		setVisible(true);

		c_GameRoom.enterGameRoom(this);
	}

    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        payer1_jComboBox = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        payer4_jComboBox = new javax.swing.JComboBox<>();
        payer2_jComboBox = new javax.swing.JComboBox<>();
        payer5_jComboBox = new javax.swing.JComboBox<>();
        payer3_jComboBox = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        payer1Ready_jComboBox = new javax.swing.JComboBox<>();
        payer2Ready_jComboBox = new javax.swing.JComboBox<>();
        payer3Ready_jComboBox = new javax.swing.JComboBox<>();
        payer4Ready_jComboBox = new javax.swing.JComboBox<>();
        payer5Ready_jComboBox = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        roomName_jTextField = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        Back_jButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        roomChat_jTextArea = new javax.swing.JTextArea();
        inputChat_jTextField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        accListNum_jLabel = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        accList_jTextArea = new javax.swing.JTextArea();
        GameStart_jButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("굴림", 1, 48)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("게임방");

        payer1_jComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Open" }));

        jLabel2.setFont(new java.awt.Font("굴림", 0, 24)); // NOI18N
        jLabel2.setText("참여자들");

        payer4_jComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Open", "Close" }));
        payer4_jComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payer4_jComboBoxActionPerformed(evt);
            }
        });

        payer2_jComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Open", "Close" }));
        payer2_jComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payer2_jComboBoxActionPerformed(evt);
            }
        });

        payer5_jComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Open", "Close" }));
        payer5_jComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payer5_jComboBoxActionPerformed(evt);
            }
        });

        payer3_jComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Open", "Close" }));
        payer3_jComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payer3_jComboBoxActionPerformed(evt);
            }
        });

        jLabel8.setText("Player1");

        jLabel10.setText("Player2");

        jLabel11.setText("Player3");

        jLabel12.setText("Player4");

        jLabel13.setText("Player5");

        payer1Ready_jComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Not ready", "Ready" }));
        payer1Ready_jComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payer1Ready_jComboBoxActionPerformed(evt);
            }
        });

        payer2Ready_jComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Not ready", "Ready" }));
        payer2Ready_jComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payer2Ready_jComboBoxActionPerformed(evt);
            }
        });

        payer3Ready_jComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Not ready", "Ready" }));
        payer3Ready_jComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payer3Ready_jComboBoxActionPerformed(evt);
            }
        });

        payer4Ready_jComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Not ready", "Ready" }));
        payer4Ready_jComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payer4Ready_jComboBoxActionPerformed(evt);
            }
        });

        payer5Ready_jComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Not ready", "Ready" }));
        payer5Ready_jComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payer5Ready_jComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10)
                            .addComponent(jLabel13)
                            .addComponent(jLabel12)
                            .addComponent(jLabel11))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(payer1_jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(payer2_jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(payer3_jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(payer5_jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(payer4_jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(payer4Ready_jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(payer5Ready_jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(payer3Ready_jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(payer2Ready_jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(payer1Ready_jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 37, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(payer1_jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(payer1Ready_jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(payer2_jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(payer2Ready_jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(payer3_jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(payer3Ready_jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(payer4Ready_jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(payer4_jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(payer5_jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(payer5Ready_jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel4.setFont(new java.awt.Font("굴림", 0, 24)); // NOI18N
        jLabel4.setText("게임 유형 : ");

        jLabel3.setFont(new java.awt.Font("굴림", 0, 24)); // NOI18N
        jLabel3.setText("방이름 : ");

        jLabel5.setFont(new java.awt.Font("굴림", 0, 24)); // NOI18N
        jLabel5.setText("인원 수 : ");

        roomName_jTextField.setEditable(false);
        roomName_jTextField.setFont(new java.awt.Font("굴림", 0, 24)); // NOI18N

        jTextField2.setEditable(false);
        jTextField2.setFont(new java.awt.Font("굴림", 0, 24)); // NOI18N
        jTextField2.setText("개인전");

        jTextField3.setEditable(false);
        jTextField3.setFont(new java.awt.Font("굴림", 0, 24)); // NOI18N
        jTextField3.setText("2~5인");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(roomName_jTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel3)
                .addGap(2, 2, 2)
                .addComponent(roomName_jTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(6, 6, 6)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        Back_jButton.setFont(new java.awt.Font("굴림", 0, 24)); // NOI18N
        Back_jButton.setText("나가기");
        Back_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Back_jButtonActionPerformed(evt);
            }
        });

        roomChat_jTextArea.setEditable(false);
        roomChat_jTextArea.setColumns(20);
        roomChat_jTextArea.setRows(5);
        jScrollPane1.setViewportView(roomChat_jTextArea);

        inputChat_jTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputChat_jTextFieldActionPerformed(evt);
            }
        });

        jLabel6.setText("방 채팅");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
            .addComponent(inputChat_jTextField)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(inputChat_jTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel7.setText("접속자 목록 (");

        accListNum_jLabel.setText("0");

        jLabel9.setText("명)");

        accList_jTextArea.setColumns(20);
        accList_jTextArea.setRows(5);
        jScrollPane2.setViewportView(accList_jTextArea);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(accListNum_jLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addGap(0, 53, Short.MAX_VALUE))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(accListNum_jLabel)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        GameStart_jButton.setFont(new java.awt.Font("굴림", 0, 24)); // NOI18N
        GameStart_jButton.setText("시작");
        GameStart_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GameStart_jButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addComponent(GameStart_jButton)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Back_jButton))
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(GameStart_jButton)
                            .addComponent(Back_jButton)))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    // [방장이] 게임 시작 버튼을 눌렀을 때,
	private void GameStart_jButtonActionPerformed(java.awt.event.ActionEvent evt) {

		// 1. 게임 시작을 시도 한다.
		if (c_GameRoom.gameStart()) {
			GameStart();
		} // 그 외는 게임시작 실패
	}
	
	public void GameStart(){
		// 게심 시작 성공
		System.out.println("멀티 테트리스 화면 이동");
		GameStart_jButton.enable(false);
		Back_jButton.enable(false);
		
		roomChat_jTextArea.append("게임시작 카운트 다운\n");
		for (int i = 5; i >= 0; i--) {
			roomChat_jTextArea.append("게임시작 " + i + "초전\n");
			System.out.println("게임시작 " + i + "초전\n");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		roomChat_jTextArea.append("게임시작\n");
		System.out.println("게임시작\n");
		
		nameList = c_GameRoom.getPlayerList();
		multiTetrisJFrame = new GUI_MultiTetrisJFrame(this, c_GameRoom.c_TetrisRoom, nameList);
	}

	// 방 나가기 버튼을 눌렀을 때,
	public void Back_jButtonActionPerformed(java.awt.event.ActionEvent evt) {
		c_GameRoom.exitGameRoom();

		if (prevJFrame instanceof GUI_JoinRoomJFrame) {
			((GUI_JoinRoomJFrame) this.prevJFrame).prevJFrame.setVisible(true);
			((GUI_WaitingJFrame)((GUI_JoinRoomJFrame) this.prevJFrame).prevJFrame).totalChat_jTextArea.setText("");
		} else if (prevJFrame instanceof GUI_CreateRoomJFrame) {
			((GUI_CreateRoomJFrame) this.prevJFrame).prevJFrame.setVisible(true);
			((GUI_WaitingJFrame)((GUI_CreateRoomJFrame) this.prevJFrame).prevJFrame).totalChat_jTextArea.setText("");
		}
		this.prevJFrame.dispose();
		this.dispose();
	}

	// 채팅 값을 입력하고 엔터키를 눌렀을 때,
	private void inputChat_jTextFieldActionPerformed(java.awt.event.ActionEvent evt) {
		c_GameRoom.sendTalk();
	}

	//레디 값을 변경했을 때,
	private void payer1Ready_jComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
		if (!payer1Ready_combo.equals(payer1Ready_jComboBox.getSelectedItem().toString())) {
			if (payer1Ready_combo.equals("Not ready")) {
				payer1Ready_combo = "Ready";
			} else {
				payer1Ready_combo = "Not ready";
			}
			c_GameRoom.ready(1, payer1Ready_combo);
		}
	}

	private void payer2_jComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
		if (!state) {
			if (!payer2_combo.equals(payer2_jComboBox.getSelectedItem().toString())) {
				if (payer2_jComboBox.getSelectedItem().equals("Open")) {
					payer2_combo = "Open";
				} else if (payer2_jComboBox.getSelectedItem().equals("Close")) {
					if(!payer2_combo.equals("Open")){
						System.out.println(payer2_jComboBox.getItemAt(1).toString() + " 강퇴당함\n");
						c_GameRoom.fire(payer2_jComboBox.getItemAt(1).toString());
						firedGameRoom(payer2_jComboBox.getItemAt(1).toString());
					}
					payer2_combo = "Close";
				}
				c_GameRoom.place(2, payer2_combo);
			}		
		}
	}

	private void payer2Ready_jComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
		if (!payer2Ready_combo.equals(payer2Ready_jComboBox.getSelectedItem().toString())) {
			if (payer2Ready_combo.equals("Not ready")) {
				payer2Ready_combo = "Ready";
			} else {
				payer2Ready_combo = "Not ready";
			}
			c_GameRoom.ready(2, payer2Ready_combo);
		}
	}

	private void payer3_jComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
		if (!state) {
			if (!payer3_combo.equals(payer3_jComboBox.getSelectedItem().toString())) {
				if (payer3_jComboBox.getSelectedItem().equals("Open")) {
					payer3_combo = "Open";
				} else if (payer3_jComboBox.getSelectedItem().equals("Close")) {
					if(!payer3_combo.equals("Open")){
						System.out.println(payer3_jComboBox.getItemAt(1).toString() + " 강퇴당함\n");
						c_GameRoom.fire(payer2_jComboBox.getItemAt(1).toString());
						firedGameRoom(payer3_jComboBox.getItemAt(1).toString());
					}
					payer3_combo = "Close";
				}
				c_GameRoom.place(3, payer3_combo);
			}
		}
	}

	private void payer3Ready_jComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
		if (!payer3Ready_combo.equals(payer3Ready_jComboBox.getSelectedItem().toString())) {
			if (payer3Ready_combo.equals("Not ready")) {
				payer3Ready_combo = "Ready";
			} else {
				payer3Ready_combo = "Not ready";
			}
			c_GameRoom.ready(3, payer3Ready_combo);
		}
	}

	private void payer4_jComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
		if (!state) {
			if (!payer4_combo.equals(payer4_jComboBox.getSelectedItem().toString())) {
				if (payer4_jComboBox.getSelectedItem().equals("Open")) {
					payer4_combo = "Open";
				} else if (payer4_jComboBox.getSelectedItem().equals("Close")) {
					if(!payer4_combo.equals("Open")){
						System.out.println(payer4_jComboBox.getItemAt(1).toString() + " 강퇴당함\n");
						c_GameRoom.fire(payer2_jComboBox.getItemAt(1).toString());
						firedGameRoom(payer4_jComboBox.getItemAt(1).toString());
					}
					payer4_combo = "Close";
				}
				c_GameRoom.place(4, payer4_combo);
			}
		}
	}

	private void payer4Ready_jComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
		if (!payer4Ready_combo.equals(payer4Ready_jComboBox.getSelectedItem().toString())) {
			if (payer4Ready_combo.equals("Not ready")) {
				payer4Ready_combo = "Ready";
			} else {
				payer4Ready_combo = "Not ready";
			}
			c_GameRoom.ready(4, payer4Ready_combo);
		}
	}

	private void payer5_jComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
		if (!state) {
			if (!payer5_combo.equals(payer5_jComboBox.getSelectedItem().toString())) {
				if (payer5_jComboBox.getSelectedItem().equals("Open")) {
					payer5_combo = "Open";
				} else if (payer5_jComboBox.getSelectedItem().equals("Close")) {
					if(!payer5_combo.equals("Open")){
						System.out.println(payer5_jComboBox.getItemAt(1).toString() + " 강퇴당함\n");
						c_GameRoom.fire(payer2_jComboBox.getItemAt(1).toString());
						firedGameRoom(payer5_jComboBox.getItemAt(1).toString());
					}
					payer5_combo = "Close";
				}
				c_GameRoom.place(5, payer5_combo);
			}
		}
	}

	private void payer5Ready_jComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
		if (!payer5Ready_combo.equals(payer5Ready_jComboBox.getSelectedItem().toString())) {
			if (payer5Ready_combo.equals("Not ready")) {
				payer5Ready_combo = "Ready";
			} else {
				payer5Ready_combo = "Not ready";
			}
			c_GameRoom.ready(5, payer5Ready_combo);
		}
	}

	private void firedGameRoom(String userName) {
		// 해당 이름의 JComboBox를 찾아서 Open으로 만든다. + 레디창을 안보이게 한다.
		state = true;
		if (payer2_jComboBox.getItemAt(1).toString().equals(userName)) {
			payer2_jComboBox.addItem("Open");
			payer2_combo = "Open";
			payer2_jComboBox.removeItem(userName);
			if (payer2Ready_jComboBox.getSelectedItem().toString().equals("Ready")) {
				payer2Ready_jComboBox.setSelectedItem("Not ready");
				payer2Ready_combo = "Not ready";
			}
			payer2Ready_jComboBox.setVisible(false);
			payer2Ready_jComboBox.setEnabled(false);
		} else if (payer3_jComboBox.getItemAt(1).toString().equals(userName)) {
			payer3_jComboBox.addItem("Open");
			payer3_combo = "Open";
			payer3_jComboBox.removeItem(userName);
			if (payer3Ready_jComboBox.getSelectedItem().toString().equals("Ready")) {
				payer3Ready_jComboBox.setSelectedItem("Not ready");
				payer3Ready_combo = "Not ready";
			}
			payer3Ready_jComboBox.setVisible(false);
			payer3Ready_jComboBox.setEnabled(false);
		} else if (payer4_jComboBox.getItemAt(1).toString().equals(userName)) {
			payer4_jComboBox.addItem("Open");
			payer4_combo = "Open";
			payer4_jComboBox.removeItem(userName);
			if (payer4Ready_jComboBox.getSelectedItem().toString().equals("Ready")) {
				payer4Ready_jComboBox.setSelectedItem("Not ready");
				payer4Ready_combo = "Not ready";
			}
			payer4Ready_jComboBox.setVisible(false);
			payer4Ready_jComboBox.setEnabled(false);
		} else if (payer5_jComboBox.getItemAt(1).toString().equals(userName)) {
			payer5_jComboBox.addItem("Open");
			payer5_combo = "Open";
			payer5_jComboBox.removeItem(userName);
			if (payer5Ready_jComboBox.getSelectedItem().toString().equals("Ready")) {
				payer5Ready_jComboBox.setSelectedItem("Not ready");
				payer5Ready_combo = "Not ready";
			}
			payer5Ready_jComboBox.setVisible(false);
			payer5Ready_jComboBox.setEnabled(false);
		}
		state = false;
	}
	
	public void changeReady(int index, String isReady){
		state = true;
		if(isReady.equals("Ready")){
			switch(index){
			case 1:
				payer1Ready_jComboBox.removeItem("Not ready");
				payer1Ready_jComboBox.addItem("Not ready");
				payer1Ready_combo = "Open";
				break;
			case 2:
				payer2Ready_jComboBox.removeItem("Not ready");
				payer2Ready_jComboBox.addItem("Not ready");
				payer2Ready_combo = "Ready";
				break;
			case 3:
				payer3Ready_jComboBox.removeItem("Not ready");
				payer3Ready_jComboBox.addItem("Not ready");
				payer3Ready_combo = "Ready";
				break;
			case 4:
				payer4Ready_jComboBox.removeItem("Not ready");
				payer4Ready_jComboBox.addItem("Not ready");
				payer4Ready_combo = "Ready";
				break;
			case 5:
				payer5Ready_jComboBox.removeItem("Not ready");
				payer5Ready_jComboBox.addItem("Not ready");
				payer5Ready_combo = "Ready";
				break;
			}
		}else if(isReady.equals("Not ready")){
			switch(index){
			case 1:
				payer1Ready_jComboBox.removeItem("Ready");
				payer1Ready_jComboBox.addItem("Ready");
				payer1Ready_combo = "Not ready";
				break;
			case 2:
				payer2Ready_jComboBox.removeItem("Ready");
				payer2Ready_jComboBox.addItem("Ready");
				payer2Ready_combo = "Not ready";
				break;
			case 3:
				payer3Ready_jComboBox.removeItem("Ready");
				payer3Ready_jComboBox.addItem("Ready");
				payer3Ready_combo = "Not ready";
				break;
			case 4:
				payer4Ready_jComboBox.removeItem("Ready");
				payer4Ready_jComboBox.addItem("Ready");
				payer4Ready_combo = "Not ready";
				break;
			case 5:
				payer5Ready_jComboBox.removeItem("Ready");
				payer5Ready_jComboBox.addItem("Ready");
				payer5Ready_combo = "Not ready";
				break;
			}
		}
		
		state = false;
	}
	
	public void changePlace(int index, String isOpen){
		state = true;
		if(isOpen.equals("Open")){
			switch(index){
			case 1:
				payer1_jComboBox.removeItem("Close");
				payer1_jComboBox.addItem("Close");
				payer1_combo = "Open";
				break;
			case 2:
				payer2_jComboBox.removeItem("Close");
				payer2_jComboBox.addItem("Close");
				payer2_combo = "Open";
				break;
			case 3:
				payer3_jComboBox.removeItem("Close");
				payer3_jComboBox.addItem("Close");
				payer3_combo = "Open";
				break;
			case 4:
				payer4_jComboBox.removeItem("Close");
				payer4_jComboBox.addItem("Close");
				payer4_combo = "Open";
				break;
			case 5:
				payer5_jComboBox.removeItem("Close");
				payer5_jComboBox.addItem("Close");
				payer5_combo = "Open";
				break;
			}
		}else if(isOpen.equals("Close")){
			String str = null;
			switch(index){
			case 2:
				str = payer2_jComboBox.getSelectedItem().toString();
				payer2_jComboBox.removeItem(str);
				payer2_jComboBox.addItem("Open");
				payer2_combo = "Close";
				if (payer2Ready_jComboBox.getSelectedItem().toString().equals("Ready")) {
					payer2Ready_jComboBox.setSelectedItem("Not ready");
					payer2Ready_combo = "Not ready";
				}
				payer2Ready_jComboBox.setVisible(false);
				payer2Ready_jComboBox.setEnabled(false);
				break;
			case 3:
				str = payer3_jComboBox.getSelectedItem().toString();
				payer3_jComboBox.removeItem(str);
				payer3_jComboBox.addItem("Open");
				payer3_combo = "Close";
				if (payer3Ready_jComboBox.getSelectedItem().toString().equals("Ready")) {
					payer3Ready_jComboBox.setSelectedItem("Not ready");
					payer3Ready_combo = "Not ready";
				}
				payer3Ready_jComboBox.setVisible(false);
				payer3Ready_jComboBox.setEnabled(false);
				break;
			case 4:
				str = payer4_jComboBox.getSelectedItem().toString();
				payer4_jComboBox.removeItem(str);
				payer4_jComboBox.addItem("Open");
				payer4_combo = "Close";
				if (payer4Ready_jComboBox.getSelectedItem().toString().equals("Ready")) {
					payer4Ready_jComboBox.setSelectedItem("Not ready");
					payer4Ready_combo = "Not ready";
				}
				payer4Ready_jComboBox.setVisible(false);
				payer4Ready_jComboBox.setEnabled(false);
				break;
			case 5:
				str = payer5_jComboBox.getSelectedItem().toString();
				payer5_jComboBox.removeItem(str);
				payer5_jComboBox.addItem("Open");
				payer5_combo = "Close";
				if (payer5Ready_jComboBox.getSelectedItem().toString().equals("Ready")) {
					payer5Ready_jComboBox.setSelectedItem("Not ready");
					payer5Ready_combo = "Not ready";
				}
				payer5Ready_jComboBox.setVisible(false);
				payer5Ready_jComboBox.setEnabled(false);
				break;
			}
		}
		state = false;
	}

	private javax.swing.JButton Back_jButton;
	public javax.swing.JButton GameStart_jButton;
	public javax.swing.JLabel accListNum_jLabel;
	public javax.swing.JTextArea accList_jTextArea;
	public javax.swing.JTextField inputChat_jTextField;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel10;
	private javax.swing.JLabel jLabel11;
	private javax.swing.JLabel jLabel12;
	private javax.swing.JLabel jLabel13;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JTextField jTextField2;
	private javax.swing.JTextField jTextField3;
	public javax.swing.JComboBox<String> payer1Ready_jComboBox;
	public javax.swing.JComboBox<String> payer1_jComboBox;
	public javax.swing.JComboBox<String> payer2Ready_jComboBox;
	public javax.swing.JComboBox<String> payer2_jComboBox;
	public javax.swing.JComboBox<String> payer3Ready_jComboBox;
	public javax.swing.JComboBox<String> payer3_jComboBox;
	public javax.swing.JComboBox<String> payer4Ready_jComboBox;
	public javax.swing.JComboBox<String> payer4_jComboBox;
	public javax.swing.JComboBox<String> payer5Ready_jComboBox;
	public javax.swing.JComboBox<String> payer5_jComboBox;
	public javax.swing.JTextArea roomChat_jTextArea;
	public javax.swing.JTextField roomName_jTextField;
}
