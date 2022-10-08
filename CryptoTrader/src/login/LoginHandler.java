package login;

import java.io.*;



/**
 * The Class LoginHandler is used to read from the textfile database containing all username-password combos
 * and verifies whether a user has entered valid credential on the login page.
 */
public class LoginHandler {
	
	/** The username and password input. */
	String userInput, passInput;
	// -1 if user exists but password not correct. 0 if user doesn't exist. 1 is user exists and password isn't correct.
	/** The success. */
	// 2 if user account creation failed for some reason.
	int success = 0;
	
	/** The file. */
	File file = new File("file.txt");
	
    /**
     * Instantiates a new login handler.
     *
     * @param userInput the username input
     * @param passInput the password input
     * @throws FileNotFoundException the file not found exception
     */
    public LoginHandler(String userInput, String passInput) throws FileNotFoundException {     
    	this.userInput = userInput;
    	this.passInput = passInput;
    }
    
    /**
     * Verifies if the users credentials are valid.
     *
     * @return true, if successful
     */
    public boolean verify() {
        try {
        	BufferedReader br = new BufferedReader(new FileReader(file));
	            String user, pass, line;
	            while((line = br.readLine()) != null) {//while not at the end of the file, read and parse each line while checking if the username
	            									   //matches the given username
	                user = line.substring(0, line.indexOf(','));
	                pass = line.substring(line.indexOf(',')+1);
	                if(user.equals(userInput)) {
	                    if(pass.equals(passInput)) {//if username matches, check password
	                        success = 1;
	                        br.close();
	                        return true;//return true if username and password match
	                    }
	                    else {
	                    	//TODO: change to pop-up
	                        System.out.println("Password is not correct");
	                        success = -1;
	                        br.close();
	                        return false;//return false if password doesn't match
	                    }
	                }
	
	            }
            br.close();
        }catch(Exception e) {
        	e.printStackTrace();
        	System.out.println("File Not Found");
        }
        
        return false;//return false if something else happens(user doesn't exist)
    }
    
    /**
     * Verified.
     *
     * @return true, if successful
     */
    public boolean verified() {
    	if(success == -1) {
    		return false;
    	}
    	else {
    		return true;
    	}
    }
    
    /**
     * Gets the success.
     *
     * @return the success (1 if verified, 0 if user doesn't exist, -1 if password incorrect)
     */
    public int getSuccess() {
    	return success;
    }
}
