package Client_GUI_Form;

import javax.swing.JOptionPane;

import Client_Function.C_ClientThread;

public class GUI_MainJFrame extends javax.swing.JFrame {
	
	C_ClientThread c_ClientThread;
	GUI_SingleTetrisJFrame single;
	GUI_LoginJFrame login;
	
    public GUI_MainJFrame() {
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
        c_ClientThread = new C_ClientThread();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        siglePlay_jButton = new javax.swing.JButton();
        multiPlay_jButton = new javax.swing.JButton();
        Quit_jButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("NP 테트리스 메인 화면");
        setName("main_frame"); // NOI18N
        setPreferredSize(new java.awt.Dimension(600, 400));

        jLabel1.setFont(new java.awt.Font("굴림", 0, 48)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("NP 테트리스");

        siglePlay_jButton.setFont(new java.awt.Font("굴림", 0, 36)); // NOI18N
        siglePlay_jButton.setText("싱글 플레이");
        siglePlay_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siglePlay_jButtonActionPerformed(evt);
            }
        });

        multiPlay_jButton.setFont(new java.awt.Font("굴림", 0, 36)); // NOI18N
        multiPlay_jButton.setText("멀티 플레이");
        multiPlay_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                multiPlay_jButtonActionPerformed(evt);
            }
        });

        Quit_jButton.setFont(new java.awt.Font("굴림", 0, 36)); // NOI18N
        Quit_jButton.setText("종료하기");
        Quit_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Quit_jButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(161, 161, 161)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(siglePlay_jButton, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                    .addComponent(Quit_jButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(multiPlay_jButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(179, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(siglePlay_jButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(multiPlay_jButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Quit_jButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
        );

        getAccessibleContext().setAccessibleName("");
        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>                        
    // 싱글 플레이 버튼을 눌렀을 때,
    private void siglePlay_jButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        single = new GUI_SingleTetrisJFrame(this);
    }                                                 
    // 멀티 플레이 버튼을 눌렀을 때,
    private void multiPlay_jButtonActionPerformed(java.awt.event.ActionEvent evt) {
    	if(c_ClientThread.connect()){//서버에 접속을 시도한다.
    		System.out.println("로그인 화면 이동");
    		login = new GUI_LoginJFrame(this, c_ClientThread);
    	}else{
    		JOptionPane.showMessageDialog(null, "서버가 켜져있지 않던지, 네트워크에 연결되어있지 않습니다.", "서버 접속 실패", JOptionPane.WARNING_MESSAGE);
    	}
    }                                                 
    // 종료하기 버튼을 눌렀을 때,
    private void Quit_jButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
        this.dispose();
        System.exit(0);
    }                                            

    private javax.swing.JButton Quit_jButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton multiPlay_jButton;
    private javax.swing.JButton siglePlay_jButton;
}