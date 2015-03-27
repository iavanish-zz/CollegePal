
package iavanish.collegepal.CommonClasses;

/**
 *
 * @author himanshu
 */

/**
 * 
 * Handles connection to the database (which resides on the server)
 */

/**
 * 
 * Implements singleton pattern
 */

public class DataBaseHandler {
    
    private static DataBaseHandler dataBaseHandler;
    
    private DataBaseHandler() {
        
    }
    
    public static DataBaseHandler getInstance() {
        if(dataBaseHandler == null) {
            dataBaseHandler = new DataBaseHandler();
        }
        else {
            
        }
        return dataBaseHandler;
    }
    
}
