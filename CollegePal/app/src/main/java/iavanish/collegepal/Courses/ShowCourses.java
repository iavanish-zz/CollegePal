
package iavanish.collegepal.Courses;

import iavanish.collegepal.CommonClasses.Course;
import iavanish.collegepal.CommonClasses.DataBaseRead;

/**
 *
 * @author himanshu
 */

/**
 * 
 * Show courses to the user
 */

public class ShowCourses {
    
    private DataBaseRead dataBaseRead;
    
    public ShowCourses() {
        
    }
    
    public Course[] showAllCourses() {
        return new Course[10];
    }
    
    public Course[] showRegisteredCourses() {
        return new Course[10];
    }
    
}
