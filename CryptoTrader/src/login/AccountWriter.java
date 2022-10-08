package login;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


/**
 * The Class AccountWriter is used to create a new account in the database when a user enters a username which does not already exist.
 */
public class AccountWriter {
	
	/** The filename. */
	private String filename;
	
	/**
	 * Instantiates a new account writer.
	 *
	 * @param filename the filename
	 */
	public AccountWriter(String filename) {
		this.filename = filename;
	}
	
	/**
	 * Creates the account.
	 *
	 * @param user the username
	 * @param pass the password
	 * @return 0, if successful
	 */
	public int createAccount(String user, String pass) {
        BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter(filename, true));
		    StringBuilder sb = new StringBuilder();
	        sb.append(user);
	        sb.append(",");
	        sb.append(pass);
	        sb.append("\n");
	        bw.write(sb.toString());
	        System.out.println("New User Created");
	        bw.close();
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
    
        return 0;
	}
}
