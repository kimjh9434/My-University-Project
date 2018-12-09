package 일식기주문기_GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JPanel;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class verifySection extends JPanel{

	private JButton EnterButton;
	private JTextField IdField;
	private JTextField passwordField;
	private view win ;
	private Socket socket ; 
	public verifySection(view win) {

		//매니저 모드 들어가기 전에 아이디를 입력하는 장소. 
		
		setLayout(null);
		this.win = win ;
		
		JLabel idLb = new JLabel("아이디:");
		idLb.setBounds(31,40, 67 ,15);
		add(idLb);

		IdField = new JTextField();
		IdField.setBounds(123,  40 ,  116 ,  21);
		add(IdField);
		IdField.setColumns(10);

		JLabel passLb = new JLabel("암호:");
		passLb.setBounds(31,84,67,15 );
		add(passLb);

		passwordField = new JPasswordField();
		passwordField.setBounds(123,84,116,21);
		add(passwordField);

		EnterButton = new JButton("확인");
		EnterButton.setSize(100,65);
		EnterButton.setLocation(250,40);
		add(EnterButton);
		EnterButton.addActionListener(new EnterAction());	

	}
	
//	void getSocket(Socket socket)
//	{
//		this.socket = socket ; 
//	}
	class EnterAction implements ActionListener{
		
		public void actionPerformed(ActionEvent e)
		{
			try{
//				if(IdField.getText().equals("1234") && passwordField.getText().equals("1234")){
				if(IdField.getText().equals("manager") && passwordField.getText().equals("1234")){
					win.change("manager");
				}else{
					JOptionPane.showMessageDialog(null, "아이디 혹은 비밀번호가 틀렸습니다.", "로그인 실패",
							JOptionPane.WARNING_MESSAGE);
				}
				IdField.setText("");
				passwordField.setText("");
			}
			catch (Exception e1){
			}
		}
	}
}
