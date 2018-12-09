package Client_GUI_Form;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Client_Function.C_ClientThread;

@SuppressWarnings("serial")
public class GUI_LoginJFrame extends javax.swing.JFrame {

	JFrame prevJFrame;
	C_ClientThread c_ClientThread;
	GUI_WaitingJFrame waiting;
	GUI_CreateAccJFrame createAcc;

	public GUI_LoginJFrame(JFrame prevJFrame, C_ClientThread c_ClientThread) {
		initComponents();
		this.prevJFrame = prevJFrame;
		this.prevJFrame.setVisible(false);
		setLocationRelativeTo(null);
		setVisible(true);
		this.c_ClientThread = c_ClientThread;
	}

	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		Back_jButton = new javax.swing.JButton();
		LoginOK_jButton = new javax.swing.JButton();
		CreateNewAcc_jButton = new javax.swing.JButton();
		jPanel1 = new javax.swing.JPanel();
		jLabel2 = new javax.swing.JLabel();
		accName_jTextField = new javax.swing.JTextField();
		jLabel3 = new javax.swing.JLabel();
		accPwd_jPasswordField = new javax.swing.JPasswordField();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("로그인 화면");
		setName("login_frame"); // NOI18N

		jLabel1.setFont(new java.awt.Font("굴림", 1, 48)); // NOI18N
		jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel1.setText("Welcome to NP Tetris");

		Back_jButton.setFont(new java.awt.Font("굴림", 0, 24)); // NOI18N
		Back_jButton.setText("뒤로가기");
		Back_jButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				Back_jButtonActionPerformed(evt);
			}
		});

		LoginOK_jButton.setFont(new java.awt.Font("굴림", 0, 24)); // NOI18N
		LoginOK_jButton.setText("로그인");
		LoginOK_jButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				LoginOK_jButtonActionPerformed(evt);
			}
		});

		CreateNewAcc_jButton.setFont(new java.awt.Font("굴림", 0, 24)); // NOI18N
		CreateNewAcc_jButton.setText("새 계정 생성");
		CreateNewAcc_jButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				CreateNewAcc_jButtonActionPerformed(evt);
			}
		});

		jLabel2.setFont(new java.awt.Font("굴림", 0, 24)); // NOI18N
		jLabel2.setText("계정 이름 :");

		accName_jTextField.setFont(new java.awt.Font("굴림", 0, 24)); // NOI18N

		jLabel3.setFont(new java.awt.Font("굴림", 0, 24)); // NOI18N
		jLabel3.setText("계정 비밀번호 :");

		accPwd_jPasswordField.setFont(new java.awt.Font("굴림", 0, 24)); // NOI18N

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap()
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jLabel2)
								.addComponent(accName_jTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel3).addComponent(accPwd_jPasswordField,
										javax.swing.GroupLayout.PREFERRED_SIZE, 200,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addContainerGap(84, Short.MAX_VALUE)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(jLabel2)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(accName_jTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addComponent(jLabel3).addGap(18, 18, 18).addComponent(accPwd_jPasswordField,
								javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(22, Short.MAX_VALUE)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
						.addGroup(layout.createSequentialGroup()
								.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(CreateNewAcc_jButton))
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
								layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE)
										.addComponent(LoginOK_jButton)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(Back_jButton)))
				.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap().addComponent(jLabel1).addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGroup(layout.createSequentialGroup().addGap(12, 12, 12).addComponent(CreateNewAcc_jButton)))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(Back_jButton).addComponent(LoginOK_jButton))
				.addContainerGap()));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	// 로그인 버튼을 눌렀을 때,
	private void LoginOK_jButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// 1. 계정 이름과, 비밀번호를 받고,
		String name = accName_jTextField.getText();
		String pwd = accPwd_jPasswordField.getText();

		// 비밀번호가 빈값이면,
		if (pwd.equals("")) {
			// 기본값으로 채운다.
			pwd = "defaultPwd";
		}

		// 2. 로그인을 시도 한다.
		if (c_ClientThread.c_Login.Login(name, pwd)) {
			// 로그인 성공
			System.out.println("대기실 화면 이동");
			waiting = new GUI_WaitingJFrame(this, c_ClientThread.c_WaitingRoom);
		} // 그 외는 로그인 실패
	}

	// 뒤로가기 버튼을 눌렀을 때,
	private void Back_jButtonActionPerformed(java.awt.event.ActionEvent evt) {
		c_ClientThread.quit();// 서버와의 접속을 끊는다.
		this.prevJFrame.setVisible(true);
		this.dispose();
	}

	// 새 계정 생성 버튼을 눌렀을 때,
	private void CreateNewAcc_jButtonActionPerformed(java.awt.event.ActionEvent evt) {
		System.out.println("회원가입 화면 이동");
		createAcc = new GUI_CreateAccJFrame(this, c_ClientThread);
	}

	public static void main(String args[]) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(GUI_LoginJFrame.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(GUI_LoginJFrame.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(GUI_LoginJFrame.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(GUI_LoginJFrame.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		}
		// </editor-fold>

	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton Back_jButton;
	private javax.swing.JButton CreateNewAcc_jButton;
	private javax.swing.JButton LoginOK_jButton;
	private javax.swing.JTextField accName_jTextField;
	private javax.swing.JPasswordField accPwd_jPasswordField;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JPanel jPanel1;
	// End of variables declaration//GEN-END:variables
}
