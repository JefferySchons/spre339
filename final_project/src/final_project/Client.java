package final_project;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Client {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client window = new Client();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Client() {
		start();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void start() {
		frame = new JFrame();
		frame.setBounds(300, 300, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLayout(null);
		
		JButton btnLogIn = new JButton("log in");
		btnLogIn.setBounds(157, 61, 89, 23);
		
		JButton btnNewUser = new JButton("new user");
		btnNewUser.setBounds(157, 161, 89, 23);
		
		btnLogIn.addActionListener(
				new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					btnNewUser.setVisible(false);
					btnLogIn.setVisible(false);
					login();
				}
		});
		btnNewUser.addActionListener(
				new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					btnNewUser.setVisible(false);
					btnLogIn.setVisible(false);
					make_user();
				}
		});
		
		
		frame.getContentPane().add(btnLogIn);
		frame.getContentPane().add(btnNewUser);
	}
	
	private void make_user() {
		JLabel usrname = new JLabel ("enter name");
		usrname.setBounds(157, 61, 100, 23);
		
		
		JLabel Userpass = new JLabel ("enter password");
		Userpass.setBounds(157, 162, 100, 23);
		
		JButton btnconform = new JButton("conform");
		btnconform.setBounds(20, 20, 89, 23);
		
		JTextField textFieldusrname = new JTextField("name");
		textFieldusrname.setBounds(261, 62, 86, 20);
		
		
		JTextField textFieldusrPass = new JTextField("password"); //lower
		textFieldusrPass.setBounds(261, 162, 86, 20);
		
		
		btnconform.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						usrname.setVisible(false);
						Userpass.setVisible(false);
						btnconform.setVisible(false);
						textFieldusrname.setVisible(false);
						textFieldusrPass.setVisible(false);
						specificAcount();
					}
		});
		
		frame.getContentPane().add(textFieldusrname);
		textFieldusrname.setColumns(10);
		frame.getContentPane().add(textFieldusrPass);
		textFieldusrPass.setColumns(10);
		frame.getContentPane().add(usrname);
		frame.getContentPane().add(Userpass);
		frame.getContentPane().add(btnconform);
	}
	
	private void login() {
		JLabel usrname = new JLabel ("enter name");
		usrname.setBounds(157, 61, 100, 23);
		
		JLabel Userpass = new JLabel ("enter password");
		Userpass.setBounds(157, 162, 100, 23);
		
		JButton btnconform = new JButton("conform");
		btnconform.setBounds(20, 20, 89, 23);
		
		JTextField textFieldusrname = new JTextField("name");
		textFieldusrname.setBounds(261, 62, 86, 20);
		
		JTextField textFieldusrPass = new JTextField("password"); //lower
		textFieldusrPass.setBounds(261, 162, 86, 20);
		
		btnconform.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						usrname.setVisible(false);
						Userpass.setVisible(false);
						btnconform.setVisible(false);
						textFieldusrname.setVisible(false);
						textFieldusrPass.setVisible(false);
						specificAcount();
					}
		});
		
		
		frame.getContentPane().add(textFieldusrname);
		textFieldusrname.setColumns(10);
		frame.getContentPane().add(textFieldusrPass);
		textFieldusrPass.setColumns(10);
		frame.getContentPane().add(usrname);
		frame.getContentPane().add(Userpass);
		frame.getContentPane().add(btnconform);
	}
	
	//private void acounts() {
	//	//display all acounts
	//}
	
	private void specificAcount() {
		//display all acounts
		//setBounds(x, y, width, height)
		JLabel labelAcountName = new JLabel("acount ID");
		labelAcountName.setBounds(20, 20, 90, 25);
		
		JLabel labelAcountAmount = new JLabel("$ amount");
		labelAcountAmount.setBounds(120, 20, 90, 25);
		
		JTextField withdrawField = new JTextField ("withdraw amount");
		withdrawField.setBounds(20, 50, 90, 25);

		JTextField depositField = new JTextField ("deposit amount");
		depositField.setBounds(20, 100, 90, 25);
		
		JButton btnconformWitdraw = new JButton("conf Withdraw");
		btnconformWitdraw.setBounds(150, 50, 120, 25);
		
		JButton btnconformdeposit = new JButton("conform Deposit");
		btnconformdeposit.setBounds(150, 100, 120, 25);
		
		JTextField transferField = new JTextField ("transfer amount");
		transferField.setBounds(20, 150, 120, 25);
		
		JTextField transferIDField = new JTextField ("transfer to ID");
		transferIDField.setBounds(150, 150, 120, 25);
		
		JButton btnconformTransfer = new JButton("conform Transfer");
		btnconformTransfer.setBounds(250, 150, 120, 25);
		
		frame.getContentPane().add(labelAcountName);
		frame.getContentPane().add(labelAcountAmount);
		frame.getContentPane().add(withdrawField);
		frame.getContentPane().add(depositField);
		frame.getContentPane().add(transferField);
		frame.getContentPane().add(transferIDField);
		
		frame.getContentPane().add(btnconformWitdraw);
		frame.getContentPane().add(btnconformdeposit);
		frame.getContentPane().add(btnconformTransfer);
		
	}
	
}
