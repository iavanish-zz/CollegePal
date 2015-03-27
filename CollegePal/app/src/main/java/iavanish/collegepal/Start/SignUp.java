
package iavanish.collegepal.Start;

import iavanish.collegepal.CommonClasses.DataBaseWrite;

/**
 *
 * @author himanshu
 */

/**
 * 
 * SignUp using Google Account
 */

public class SignUp {
    
    private static final int STATE_DEFAULT = 0;
    private static final int STATE_SIGN_UP = 1;
    private static final int STATE_IN_PROGRESS = 2;
    private static final int RESPONSE_CODE = 0;
    private static final String SAVED_PROGRESS = "signup_progress";
    private String userName;
    private String email;
    private String password;
    private int SignUpError;
    private int SignUpProgress;
    
    private DataBaseWrite dataBaseWrite;
    
    public SignUp() {
        
    }
    
    public void resolveSignUpError() {
	//code to resolve login errors
    } 
    
    public void showSignUpForm() {
        //code to display form
    }
  
    public void signUpUser() {
	//code to handle sign up
    }

    public void onSignedUp() {
	//code for Sign Up actions
    }
    
}
