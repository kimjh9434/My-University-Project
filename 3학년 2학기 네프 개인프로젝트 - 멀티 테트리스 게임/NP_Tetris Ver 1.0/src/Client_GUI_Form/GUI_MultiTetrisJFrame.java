package Client_GUI_Form;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Client_Function.C_TetrisRoom;
import Tetris.Multi_NP_TetrisControl;

public class GUI_MultiTetrisJFrame extends javax.swing.JFrame {
	
	JFrame prevJFrame;
	public C_TetrisRoom c_TetrisRoom;
	public Multi_NP_TetrisControl tetrisPanel;
	public String[] nameList;
	Thread tetrisThread;

	public GUI_MultiTetrisJFrame() {
		setLocationRelativeTo(null);
		setVisible(true);
		nameList = new String[5];
		nameList[0] = "1";
		nameList[1] = "2";
		nameList[2] = "3";
		nameList[3] = "4";
		nameList[4] = "5";
		System.out.println("initComponents()");
		initComponents();
		System.out.println("initComponents() 끝남");
	}

	public GUI_MultiTetrisJFrame(JFrame prevJFrame) {
		initComponents();
		this.prevJFrame = prevJFrame;
		this.prevJFrame.setVisible(false);
//		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public GUI_MultiTetrisJFrame(String[] nameList) {
		this.c_TetrisRoom = c_TetrisRoom;
//		setLocationRelativeTo(null);
		setVisible(true);
		this.nameList = nameList;
		initComponents();
	}
	
	public GUI_MultiTetrisJFrame(JFrame prevJFrame, C_TetrisRoom c_TetrisRoom, String[] nameList) {
		this.prevJFrame = prevJFrame;
		this.prevJFrame.setVisible(false);
		this.c_TetrisRoom = c_TetrisRoom;
//		setLocationRelativeTo(null);
		setVisible(true);
		 this.c_TetrisRoom.enterTetrisRoom(this);
		this.nameList = nameList;
		initComponents();
		tetrisChat_jTextArea.append("테트리스 게임을 시작합니다.\n");
	}


	private void initComponents() {
		ActionSort action = new ActionSort();
		addKeyListener(new MyKeyListener());
		
		setSize(1250,700);
		setTitle("멀티 모드 테트리스 게임방");
		setLayout(null);
		
		jLabel1 = new JLabel("멀티 모드 테트리스 게임방");
		jLabel1.setFont(new Font("굴림", 1, 48));
		jLabel1.setBounds(350, -50, 1200, 200);
		add(jLabel1);
		
		tetris_jPanel = new JPanel();
		tetris_jPanel.setLayout(null);
		tetris_jPanel.setBounds(20, 100, 900, 500);
		tetris_jPanel.setBackground(Color.RED);
		add(tetris_jPanel);
		
		jLabel2 = new JLabel("NO 템전");
		jLabel2.setFont(new Font("굴림", 1, 24));
		jLabel2.setBounds(20, -50, 200, 200);
		tetris_jPanel.add(jLabel2);
		
		TetrisMain_jPanel = new JPanel();
		TetrisMain_jPanel.setLayout(null);
		TetrisMain_jPanel.setBounds(120, 0, 900, 500);
		TetrisMain_jPanel.setBackground(Color.BLACK);
		tetris_jPanel.add(TetrisMain_jPanel);
		
		
		jScrollPane1 = new JScrollPane();
		
		tetrisChat_jTextArea = new JTextArea();
		tetrisChat_jTextArea.setBounds(1000, 100, 200, 500);
		add(tetrisChat_jTextArea);
//		tetrisChat_jTextArea.enable(false);
		
		Back_jButton = new JButton("나가기");
		Back_jButton.setBounds(1100, 610, 100, 40);
		add(Back_jButton);
		Back_jButton.addActionListener(action);
		

		int otherplayerNum = 4;
		System.out.println(nameList[0]);
		System.out.println(nameList[1]);
		System.out.println(nameList[2]);
		System.out.println(nameList[3]);
		System.out.println(nameList[4]);

		for (int i = 0; i < 5; i++) {
			if (nameList[i].equals("")) {
				otherplayerNum = i-1;
				break;
			}
		}
		System.out.println("otherplayerNum : " + otherplayerNum);

		tetrisPanel = new Multi_NP_TetrisControl(otherplayerNum, nameList, this);
		TetrisMain_jPanel.setLayout(null);
		TetrisMain_jPanel.add(tetrisPanel);
		tetrisPanel.setBounds(0, 0, 1000, 1000);
		tetrisThread = new Thread(tetrisPanel);
		tetrisThread.start();
	}

	public void Back_jButtonActionPerformed() {
		if(prevJFrame instanceof GUI_WaitingJFrame){
            prevJFrame.setVisible(true);
            // 가기전에 대기실의 체팅목록을 초기화시킨다.
            ((GUI_WaitingJFrame)prevJFrame).totalChat_jTextArea.setText("");
        }else{
            JFrame prevprevJFrame =  ((GUI_GameRoomJFrame)this.prevJFrame).prevJFrame;
            
            if(prevprevJFrame instanceof GUI_JoinRoomJFrame){
                ((GUI_JoinRoomJFrame)prevprevJFrame).prevJFrame.setVisible(true);
                ((GUI_WaitingJFrame)((GUI_JoinRoomJFrame)prevprevJFrame).prevJFrame).totalChat_jTextArea.setText("");
                prevprevJFrame.dispose();
                prevJFrame.dispose();
            }else if(prevprevJFrame instanceof GUI_CreateRoomJFrame){
                ((GUI_CreateRoomJFrame)prevprevJFrame).prevJFrame.setVisible(true);
                ((GUI_WaitingJFrame)((GUI_CreateRoomJFrame)prevprevJFrame).prevJFrame).totalChat_jTextArea.setText("");
                prevprevJFrame.dispose();
                prevJFrame.dispose();
            } 
        }
        this.dispose();
		tetrisThread.stop();
		c_TetrisRoom.exitRoom();
	}
	
	class ActionSort implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("나가기")){
				System.out.println("나가기 버튼 눌림");
				Back_jButtonActionPerformed();
			}
		}
	}
	
	private JButton Back_jButton;
	private JPanel TetrisMain_jPanel;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JScrollPane jScrollPane1;
	public JTextArea tetrisChat_jTextArea;
	private JPanel tetris_jPanel;
	
//Key 리스너 구현 // 굳이 이거 없으면 안돌아감 [제어를 못받음]
class MyKeyListener extends KeyAdapter 
{
	public void keyPressed(KeyEvent e) {
		int keyCode = ' '; // 입력받은 key값

		if (!tetrisPanel.isGameOver)// 게임이 돌아가고 있다면,
		{
			keyCode = e.getKeyCode(); // key값을 입력받는다.
			// 어떤 key값을 입력받았는지 확인하기 위한 임시 코드
//			System.out.println(" MyKeyListener. 눌린 키값은 : " + keyCode);
//			System.out.println("c_TetrisRoom.tetrisCMD(keyCode);");
			c_TetrisRoom.tetrisCMD(keyCode);
//			tetrisChat_jTextArea.append(" 눌린 키값은 : " + keyCode + "\n");
		}
		if (!tetrisPanel.tetrimino.getIsLocked()) // 테트리미노가 잠겨있지 않다면,
		{
			//  상하좌우 이동 및 회전
			if (keyCode == KeyEvent.VK_LEFT && tetrisPanel.tetrisCheckMove(-1, 0)) // MOVE LEFT
			{
				tetrisPanel.tetrimino.moveLeft();// tetrimino.posX--;
			}
			// MOVE RIGHT
			else if (keyCode == KeyEvent.VK_RIGHT && tetrisPanel.tetrisCheckMove(1, 0)) 
			{
				tetrisPanel.tetrimino.moveRight();// tetrimino.posX++;
			} else if (keyCode == KeyEvent.VK_DOWN) // DOWN
			{
				tetrisPanel.tetrimino.setIsDownStep(true);
			} else if (keyCode == KeyEvent.VK_UP) // UP
			{
				tetrisPanel.tetrimino.setIsDrop(true);
			} else if ((keyCode == 'w' || keyCode == 'W') && tetrisPanel.tetrisCanSave)// SAVE tetrisIMINO

			{
				tetrisPanel.tetrisSave();
			}
			// ROTATE LEFT
			else if ((keyCode == 'q' || keyCode == 'Q') && tetrisPanel.tetrisCheckRotate(-1)) 
			{
				tetrisPanel.tetrimino.turnLeft();
			} else if ((keyCode == 'e' || keyCode == 'E') && tetrisPanel.tetrisCheckRotate(+1)) // ROTATE																						// RIGHT
			{
				tetrisPanel.tetrimino.turnRight();
			} 
		}
	}
}

}
