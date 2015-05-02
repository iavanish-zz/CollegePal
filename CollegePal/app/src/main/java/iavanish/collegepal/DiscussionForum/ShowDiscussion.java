
package iavanish.collegepal.DiscussionForum;

import iavanish.collegepal.CommonClasses.Course;
import iavanish.collegepal.CommonClasses.DataBaseRead;

/**
 *
 * @author deependra
 */

/**
 * 
 * Show discussions in a course
 */

public class ShowDiscussion {
    
    private Course course;
    
    private DataBaseRead dataBaseRead;
    
    public ShowDiscussion(Course course) {
        this.course = course;
    }
    
    public Discussion[] showDiscussion() {
        return new Discussion[10];
    }
    
}
