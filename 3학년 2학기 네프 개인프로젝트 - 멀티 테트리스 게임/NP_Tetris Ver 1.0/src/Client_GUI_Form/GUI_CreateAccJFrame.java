/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client_GUI_Form;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Client_Function.C_ClientThread;

@SuppressWarnings("serial")
public class GUI_CreateAccJFrame extends javax.swing.JFrame {

    JFrame prevJFrame;
    C_ClientThread c_ClientThread;
    public GUI_CreateAccJFrame(JFrame prevJFrame, C_ClientThread c_ClientThread) {
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
        CreateAccOK_jButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        accNameNew_jTextField = new javax.swing.JTextField();
        accPwdNew_jPasswordField = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("새 계정 생성 화면");

        jLabel1.setFont(new java.awt.Font("굴림", 1, 48)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("새 계정 생성");

        Back_jButton.setFont(new java.awt.Font("굴림", 0, 24)); // NOI18N
        Back_jButton.setText("뒤로가기");
        Back_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Back_jButtonActionPerformed(evt);
            }
        });

        CreateAccOK_jButton.setFont(new java.awt.Font("굴림", 0, 24)); // NOI18N
        CreateAccOK_jButton.setText("생성");
        CreateAccOK_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	CreateAccOK_jButtonActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("굴림", 0, 24)); // NOI18N
        jLabel2.setText("새 계정 이름 : ");

        jLabel3.setFont(new java.awt.Font("굴림", 0, 24)); // NOI18N
        jLabel3.setText("새 계정 비밀번호 : ");

        accNameNew_jTextField.setFont(new java.awt.Font("굴림", 0, 24)); // NOI18N
        accNameNew_jTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accNameNew_jTextFieldActionPerformed(evt);
            }
        });

        accPwdNew_jPasswordField.setFont(new java.awt.Font("굴림", 0, 24)); // NOI18N

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
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(0, 227, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(CreateAccOK_jButton)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Back_jButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(accNameNew_jTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(accPwdNew_jPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(accNameNew_jTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(accPwdNew_jPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(74, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Back_jButton)
                            .addComponent(CreateAccOK_jButton))
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void CreateAccOK_jButtonActionPerformed(java.awt.event.ActionEvent evt) {       
        // 1. 계정 이름과, 비밀번호를 받고,
        String newName = accNameNew_jTextField.getText();
        String newPwd = accPwdNew_jPasswordField.getText();

        // 2. 모두 널이 아닌지 확인하고 [널이 하나라도 있으면 두 값 둘줄 하나라도 빈칸이면 안된다고 전한다. 
        if( newName.equals("") || newPwd.equals("") ){
            JOptionPane.showMessageDialog(null, "계정 이름과 비밀번호 중 빈 값이 있으면 안됩니다.", "회원가입 실패", JOptionPane.WARNING_MESSAGE);
        }
        else{
        	// 3. 회원 가입 시킨다. [성공적으로 회원가입되었다고 알린다.]
            if(c_ClientThread.c_Login.Join(newName, newPwd))
            {
            	JOptionPane.showMessageDialog(null, "성공적으로 회원가입 되었습니다.", "계정 생성", JOptionPane.WARNING_MESSAGE);

                // 4. 이후 로그인 화면으로 넘어간다.
                this.prevJFrame.setVisible(true);
                this.dispose();
            }
        }
    }

    private void Back_jButtonActionPerformed(java.awt.event.ActionEvent evt) {
        this.prevJFrame.setVisible(true);
        this.dispose();
    }

    private void accNameNew_jTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accNameNew_jTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_accNameNew_jTextFieldActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Back_jButton;
    private javax.swing.JButton CreateAccOK_jButton;
    private javax.swing.JTextField accNameNew_jTextField;
    private javax.swing.JPasswordField accPwdNew_jPasswordField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
