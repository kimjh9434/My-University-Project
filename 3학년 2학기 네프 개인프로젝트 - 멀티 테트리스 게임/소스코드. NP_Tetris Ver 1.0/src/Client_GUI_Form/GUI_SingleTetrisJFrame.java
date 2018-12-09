/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client_GUI_Form;

import javax.swing.JFrame;

import Tetris.Multi_NP_TetrisControl;
import Tetris.Single_NP_TetrisControl;

@SuppressWarnings("serial")
public class GUI_SingleTetrisJFrame extends javax.swing.JFrame {

	JFrame prevJFrame;

	public GUI_SingleTetrisJFrame(JFrame prevJFrame) {
		initComponents();
		this.prevJFrame = prevJFrame;
		this.prevJFrame.setVisible(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
//	NP_TetrisControl tetrisPanel = new NP_TetrisControl();
//	TetrisMain_jPanel.setLayout(null);
//	TetrisMain_jPanel.add(tetrisPanel);
//	tetrisPanel.setBounds(0, 0, 1000, 1000);
//	new Thread(tetrisPanel).start();
	
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        tetris_jPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        TetrisMain_jPanel = new javax.swing.JPanel();
        Back_jButton = new javax.swing.JButton();
        
//        Multi_NP_TetrisControl tetrisPanel = new Multi_NP_TetrisControl();
        Single_NP_TetrisControl tetrisPanel = new Single_NP_TetrisControl();
    	TetrisMain_jPanel.setLayout(null);
    	TetrisMain_jPanel.add(tetrisPanel);
    	tetrisPanel.setBounds(0, 0, 1000, 1000);
    	new Thread(tetrisPanel).start();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("싱글 모드 테트리스 게임방");

        jLabel1.setFont(new java.awt.Font("굴림", 0, 48)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("싱글 모드 테트리스 게임방");

        jLabel2.setFont(new java.awt.Font("굴림", 1, 24)); // NOI18N
        jLabel2.setText("NO 템전");

        javax.swing.GroupLayout TetrisMain_jPanelLayout = new javax.swing.GroupLayout(TetrisMain_jPanel);
        TetrisMain_jPanel.setLayout(TetrisMain_jPanelLayout);
        TetrisMain_jPanelLayout.setHorizontalGroup(
            TetrisMain_jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        TetrisMain_jPanelLayout.setVerticalGroup(
            TetrisMain_jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout tetris_jPanelLayout = new javax.swing.GroupLayout(tetris_jPanel);
        tetris_jPanel.setLayout(tetris_jPanelLayout);
        tetris_jPanelLayout.setHorizontalGroup(
            tetris_jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tetris_jPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TetrisMain_jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tetris_jPanelLayout.setVerticalGroup(
            tetris_jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tetris_jPanelLayout.createSequentialGroup()
                .addGroup(tetris_jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tetris_jPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addGap(72, 387, Short.MAX_VALUE))
                    .addComponent(TetrisMain_jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        Back_jButton.setFont(new java.awt.Font("굴림", 0, 24)); // NOI18N
        Back_jButton.setText("나가기");
        Back_jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Back_jButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 645, Short.MAX_VALUE)
                    .addComponent(tetris_jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(Back_jButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tetris_jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Back_jButton)
                .addContainerGap())
        );

        pack();
    }


	private void Back_jButtonActionPerformed(java.awt.event.ActionEvent evt) {
		this.prevJFrame.setVisible(true);
		this.dispose();
	}

	private javax.swing.JButton Back_jButton;
	private javax.swing.JPanel TetrisMain_jPanel;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JPanel tetris_jPanel;
}
