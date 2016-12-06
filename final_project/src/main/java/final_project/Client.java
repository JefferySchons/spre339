package final_project;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Client {

	private static final String SERVER_URL = "http://127.0.0.1:8080/";
	
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Unirest.setObjectMapper(new ObjectMapper()
				{
					private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
					public <T> T readValue(String value, Class<T> valueType)
					{
						try
						{
							return jacksonObjectMapper.readValue(value, valueType);
						}
						catch(IOException e)
						{
							throw new RuntimeException(e);
						}
					}
					public String writeValue(Object value) 
					{
						try
						{
							return jacksonObjectMapper.writeValueAsString(value);
						}
						catch(JsonProcessingException e)
						{
							throw new RuntimeException(e);
						}
						
					}
				});
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
		
		final JButton btnLogIn = new JButton("Log in");
		btnLogIn.setBounds(157, 61, 89, 23);
		
		final JButton btnNewUser = new JButton("New user");
		btnNewUser.setBounds(157, 161, 89, 23);
		
		btnLogIn.addActionListener(
				new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					frame.getContentPane().removeAll();
					frame.repaint();
					login();
				}
		});
		btnNewUser.addActionListener(
				new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					frame.getContentPane().removeAll();
					frame.repaint();
					make_user();
				}
		});
		
		
		frame.getContentPane().add(btnLogIn);
		frame.getContentPane().add(btnNewUser);
	}
	
	private void make_user() {
		final JLabel usrname = new JLabel ("Enter name");
		usrname.setBounds(157, 61, 100, 23);
		
		
		final JLabel Userpass = new JLabel ("Enter password");
		Userpass.setBounds(157, 162, 100, 23);
		
		final JButton btnconform = new JButton("Confirm");
		btnconform.setBounds(20, 20, 89, 23);
		
		final JTextField textFieldusrname = new JTextField("");
		textFieldusrname.setBounds(261, 62, 86, 20);
		
		
		final JTextField textFieldusrPass = new JTextField("");
		textFieldusrPass.setBounds(261, 162, 86, 20);
		
		
		btnconform.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						
						try {
							User user = new User(textFieldusrname.getText(), textFieldusrPass.getText());
							Unirest.post(SERVER_URL + "user")
								.header("Content-Type", "application/json")
								.body(user)
								.asJson();
						} catch (UnirestException e) {
							e.printStackTrace();
						}
						
						frame.getContentPane().removeAll();
						frame.repaint();
						accounts(textFieldusrname.getText());
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
		final JLabel usrname = new JLabel ("Enter name");
		usrname.setBounds(157, 61, 100, 23);
		
		final JLabel Userpass = new JLabel ("Enter password");
		Userpass.setBounds(157, 162, 100, 23);
		
		final JButton btnconform = new JButton("Confirm");
		btnconform.setBounds(20, 20, 89, 23);
		
		final JTextField textFieldusrname = new JTextField("");
		textFieldusrname.setBounds(261, 62, 86, 20);
		
		final JTextField textFieldusrPass = new JTextField("");
		textFieldusrPass.setBounds(261, 162, 86, 20);
		
		final JLabel invalidLogin = new JLabel("Invalid credentials");
		invalidLogin.setVisible(false);
		invalidLogin.setBounds(20, 40, 200, 23);
		
		btnconform.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent arg0) {
						int responseCode = -1;
						try 
						{
							HttpResponse<Response> response = Unirest.get(SERVER_URL + "user/{uid}/auth")
									.routeParam("uid", textFieldusrname.getText())
									.queryString("password", textFieldusrPass.getText())
									.asObject(Response.class);
							responseCode = response.getBody().getStatus();
						} 
						catch (UnirestException e) 
						{
							
							e.printStackTrace();
						}				
						
						if(responseCode != 200)
						{
							invalidLogin.setVisible(true);
							return;
						}
						
						usrname.setVisible(false);
						Userpass.setVisible(false);
						btnconform.setVisible(false);
						textFieldusrname.setVisible(false);
						textFieldusrPass.setVisible(false);
						accounts(textFieldusrname.getText());
					}
		});
		
		
		frame.getContentPane().add(textFieldusrname);
		textFieldusrname.setColumns(10);
		frame.getContentPane().add(textFieldusrPass);
		textFieldusrPass.setColumns(10);
		frame.getContentPane().add(usrname);
		frame.getContentPane().add(Userpass);
		frame.getContentPane().add(btnconform);
		frame.getContentPane().add(invalidLogin);
	}
	
	private void accounts(final String userId) {
		List<String> accounts = null;
		try
		{
			HttpResponse<User> userResponse = Unirest.get(SERVER_URL + "user/{uid}")
					.routeParam("uid", userId)
					.asObject(User.class);
			accounts = userResponse.getBody().getAccounts();
		} 
		catch (UnirestException e) 
		{
			e.printStackTrace();
		}
		
		
		final JButton newAccount = new JButton("New Account");
		newAccount.setBounds(0, 0, 200, 20);
		frame.getContentPane().add(newAccount);

		//Combo box wouldn't let me just do accounts.toArray(), and I'm running out of time
		String[] arr = new String[accounts.size()];
		for(int i = 0; i < arr.length; i++)
		{
			arr[i] = accounts.get(i);
		}
		final JComboBox<String> accountsBox = new JComboBox<String>(arr);
		accountsBox.setBounds(frame.getWidth() / 2 - 50, frame.getHeight() / 2 - 30, 100, 20);
		frame.getContentPane().add(accountsBox);
		
		final JButton confirmButton = new JButton("Confirm");
		confirmButton.setBounds(frame.getWidth() / 2 - 50, frame.getHeight() / 2 - 10, 100, 20);
		confirmButton.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					frame.getContentPane().removeAll();
					frame.repaint();
					specificAccount((String) accountsBox.getSelectedItem(), userId);
				}
			});
		frame.getContentPane().add(confirmButton);
		
		newAccount.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				frame.getContentPane().removeAll();
				frame.repaint();
				addAccount(userId);
			}
		});
	}
	
	private void addAccount(final String username)
	{
		JLabel lblAccountName = new JLabel("Account name");
		lblAccountName.setBounds(20, 20, 100, 20);
		frame.getContentPane().add(lblAccountName);
		
		final JTextField txtAccountName = new JTextField("Account name");
		txtAccountName.setBounds(120, 20, 100, 20);
		frame.getContentPane().add(txtAccountName);
		
		JLabel lblBalance = new JLabel("Balance");
		lblBalance.setBounds(20, 40, 100, 20);
		frame.getContentPane().add(lblBalance);
		
		final JTextField txtBalance = new JTextField();
		txtBalance.setBounds(120, 40, 100, 20);
		frame.getContentPane().add(txtBalance);
		
		String[] validTypes = { "Checkings", "Savings" };
		final JComboBox<String> typeList = new JComboBox<String>(validTypes);
		typeList.setSelectedIndex(0);
		typeList.setBounds(20, 60, 200, 20);
		frame.getContentPane().add(typeList);
		
		JButton createButton = new JButton("Create Account");
		createButton.setBounds(100, 100, 200, 20);
		frame.getContentPane().add(createButton);
		createButton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						//Defensive programming: check user input for balance, don't allow non-numbers
						double balance = 0;
						try
						{
							balance = Double.parseDouble(txtBalance.getText());
						}
						catch(NumberFormatException e1)
						{
							JOptionPane.showMessageDialog(null,  "Invalid input for balance. Please use a number.", "Invalid input", JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						Account account = new Account(username, balance, (String) typeList.getSelectedItem(), txtAccountName.getText());
						try
						{
							Unirest.post(SERVER_URL + "account")
								.header("Content-Type", "application/json")
								.body(account)
								.asJson();
							
							HttpResponse<User> user = Unirest.get(SERVER_URL + "user/{uid}")
									.routeParam("uid", username)
									.asObject(User.class);
							
							user.getBody().getAccounts().add(account.getName());
							System.out.printf("%d\n", user.getBody().getAccounts().size());
							Unirest.put(SERVER_URL + "user/{uid}")
								.header("Content-Type", "application/json")
								.routeParam("uid", username)
								.body(user.getBody())
								.asJson();
							
							frame.getContentPane().removeAll();
							frame.repaint();
							specificAccount(account.getName(), username);
						}
						catch (UnirestException e1)
						{
							e1.printStackTrace();
						}
					}
				});
	}
	
	private void specificAccount(String accountName, String userId) 
	{
		HttpResponse<Account> response;
		try
		{
			response = Unirest.get(SERVER_URL + "account/{aid}")
					.routeParam("aid", accountName)
					.asObject(Account.class);
		}
		catch(UnirestException e)
		{
			response = null;
		}
		
		final Account account = response.getBody();
		
		//display all acounts
		//setBounds(x, y, width, height)
		JLabel labelAcountName = new JLabel(account.getName());
		labelAcountName.setBounds(20, 20, 200, 25);
		
		final JLabel labelAcountAmount = new JLabel(String.format("$%f", account.getBalance()));
		labelAcountAmount.setBounds(120, 20, 90, 25);

		JLabel typeLabel = new JLabel(account.getType());
		typeLabel.setBounds(220, 20, 90, 25);
		
		final JTextField withdrawField = new JTextField("Withdraw amount");
		withdrawField.setBounds(20, 50, 90, 25);

		final JTextField depositField = new JTextField("Deposit amount");
		depositField.setBounds(20, 100, 90, 25);
		
		JButton btnconformWitdraw = new JButton("Confirm Withdraw");
		btnconformWitdraw.setBounds(150, 50, 200, 25);
		btnconformWitdraw.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						//Defensive programming: check user input for balance, don't allow non-numbers
						double amount = 0;
						try
						{
							amount = Double.parseDouble(withdrawField.getText());
						}
						catch(NumberFormatException e1)
						{
							JOptionPane.showMessageDialog(null,  "Invalid input for balance. Please use a number.", "Invalid input", JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						
						account.setBalance(account.getBalance() - amount);
						labelAcountAmount.setText(String.format("$%f", account.getBalance()));
						try
						{
							Unirest.put(SERVER_URL + "account/{aid}")
								.header("Content-Type", "application/json")
								.routeParam("aid", account.getName())
								.body(account)
								.asJson();
						} 
						catch (UnirestException e1)
						{
							e1.printStackTrace();
						}
					}
				});
		
		JButton btnconformdeposit = new JButton("Confirm Deposit");
		btnconformdeposit.setBounds(150, 100, 200, 25);
		btnconformdeposit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//Defensive programming: check user input for balance, don't allow non-numbers
				double amount = 0;
				try
				{
					amount = Double.parseDouble(depositField.getText());
				}
				catch(NumberFormatException e1)
				{
					JOptionPane.showMessageDialog(null,  "Invalid input for balance. Please use a number.", "Invalid input", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				account.setBalance(account.getBalance() + amount);
				labelAcountAmount.setText(String.format("$%f", account.getBalance()));
				try
				{
					Unirest.put(SERVER_URL + "account/{aid}")
						.header("Content-Type", "application/json")
						.routeParam("aid", account.getName())
						.body(account)
						.asJson();
				} 
				catch (UnirestException e1)
				{
					e1.printStackTrace();
				}
			}
		});
		
		frame.getContentPane().add(labelAcountName);
		frame.getContentPane().add(labelAcountAmount);
		frame.getContentPane().add(typeLabel);
		frame.getContentPane().add(withdrawField);
		frame.getContentPane().add(depositField);
		
		frame.getContentPane().add(btnconformWitdraw);
		frame.getContentPane().add(btnconformdeposit);
	}
}