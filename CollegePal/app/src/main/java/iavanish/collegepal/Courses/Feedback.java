
package iavanish.collegepal.Courses;

import iavanish.collegepal.CommonClasses.Course;
import iavanish.collegepal.CommonClasses.DataBaseWrite;

/**
 *
 * @author iavanish
 */

/**
 * 
 * User feedback about a particular course
 */

public class Feedback {
    
    private Course course;
    private String feedback;
    
    private DataBaseWrite dataBaseWrite;
    
    private Feedback() {
        
    }
    
    public Feedback(Course course) {
        this.course = course;
    }

    public void registerFeedback(String feedback) {
        
    }
    
    public Feedback[] viewFeedback() {
        return new Feedback[10];
    }
    
}
