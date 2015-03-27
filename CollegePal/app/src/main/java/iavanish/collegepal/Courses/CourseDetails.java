
package iavanish.collegepal.Courses;

import iavanish.collegepal.CommonClasses.DataBaseWrite;

/**
 *
 * @author himanshu
 */

/**
 * 
 * Course Details
 */

public class CourseDetails {
    
    public String courseOverview;
    public String[] preRequisites;
    public String[] postConditions;
    public String instructor;
    public String[] courseTAs;
     
    private DataBaseWrite dataBaseWrite;    
       
    public CourseDetails() {
    }
    
}
