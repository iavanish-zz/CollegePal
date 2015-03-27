
package iavanish.collegepal.Start;

import iavanish.collegepal.CommonClasses.DataBaseRead;

/**
 *
 * @author himanshu
 */

/**
 * 
 * Log-in / Log-out
 */

public class LogInLogOut {
    
    private static final int STATE_DEFAULT = 0;
    private static final int STATE_LOGIN_IN = 1;
    private static final int STATE_IN_PROGRESS = 2;
    private static final int RESPONSE_CODE = 0;
    private static final String SAVED_PROGRESS = "login_progress";
    private int logInError;
    private int logInProgress;
    
    private DataBaseRead dataBaseRead;
    
    public LogInLogOut() {
        
    }
    
    public void resolveLogInError() {
	//code to resolve login errors
    } 
    
    public void onLoggedIn() {
	//code for login actions
    }
    
    public void onLoggedOut() {
	//code for logout actions
    }
    
}
