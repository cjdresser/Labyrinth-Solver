package gui;

import login.AccountWriter;
import login.LoginHandler;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;


/**
 * The Class LoginUI allows the user to enter a username and password to log into the system or create a new account.
 */
public class LoginUI extends JFrame implements ActionListener{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The frame. */
	private JFrame frame;
	
	/** The username field. */
	private JTextField userField;
	
	/** The password field. */
	private JPasswordField passwordField;
	
	/** The login handler. */
	private LoginHandler login;
	
	/** The verified flag. */
	boolean verified = false;
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginUI window = new LoginUI();
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
	public LoginUI() {
		initialize();
	}
	
	/**
	 * Verify.
	 *
	 * @return true, if successful
	 */
	public boolean verify() {
		return verified;
	}
	
	/**
	 * Gets the frame.
	 *
	 * @return the frame
	 */
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 377, 229);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JLabel userLabel = new JLabel("Username:");
		userLabel.setBounds(37, 37, 88, 21);
		frame.getContentPane().add(userLabel);
		
		userField = new JTextField();
		userField.setBounds(171, 38, 96, 19);
		frame.getContentPane().add(userField);
		userField.setColumns(10);
		
		JLabel passLabel = new JLabel("Password:");
		passLabel.setBounds(37, 83, 88, 21);
		frame.getContentPane().add(passLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(171, 84, 96, 19);
		frame.getContentPane().add(passwordField);
		
		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(this);
		submitButton.setBounds(128, 143, 85, 21);
		frame.getContentPane().add(submitButton);
	}
	
	/**
	 * Creates the Main UI.
	 */
	public void createUI() {
		MainGUI ui = new MainGUI();
		ui.getFrame().setVisible(true);
		frame.dispose();
	}
	
	/**
	 * Action performed.
	 *
	 * @param ae the action event
	 */
	public void actionPerformed(ActionEvent ae) {
		String user = userField.getText();//username input
		String pass = String.valueOf(passwordField.getPassword());//password input
	
		try {
			login = new LoginHandler(user, pass);
			//if username exists and password input matches password in database, create main UI and dispose of login frame
			if(login.verify()) {
				createUI();
			}
			//if login is not successful
			else {
				//if username exists but password input does not match password in database
				//show error message and allow user to reenter password
				if(login.getSuccess() == -1) {
					JOptionPane.showMessageDialog(null, "Incorrect Password. Please Try Again.", "Incorrect Password", JOptionPane.WARNING_MESSAGE);
					passwordField.setText("");
				}
				//if username does not exist in the database, allow user option of creating a new account
				else if(login.getSuccess() == 0) {
					int newUserOption = JOptionPane.showConfirmDialog(null, "This Username Does Not Exist. Would You Like To Create A New Account?", 
							  "Non-Existant User", JOptionPane.YES_NO_OPTION);
					//if user wants to create a new account 
					if(newUserOption == 0) {
						AccountWriter aw = new AccountWriter("file.txt");//create new object to write new account info to database
	            		int accCreated = aw.createAccount(user, pass);//0 if account creation was successful, -1 if something went wrong
	            		//if account creation is successful, display message and send user to main UI
	            		if(accCreated == 0) {
	            			JOptionPane.showMessageDialog(null, "New User Successfully Created");
	            			createUI();
	            		}
	            		//otherwise, display message and allow user to try again
	            		else {
	            			JOptionPane.showMessageDialog(null, "Sorry! Something Went Wrong");
	            			passwordField.setText("");
	    					userField.setText("");
	            		}
					}
					//if user does not want to create a new account, allow them to reenter credentials
					else {
						passwordField.setText("");
    					userField.setText("");
					}

				}
				//otherwise, something went wrong
				else{
					JOptionPane.showMessageDialog(null, "Sorry! Something Went Wrong");
					passwordField.setText("");
					userField.setText("");
				}
				
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
