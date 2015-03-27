
package iavanish.collegepal.DiscussionForum;

import iavanish.collegepal.CommonClasses.Student;
import iavanish.collegepal.CommonClasses.TimeStamp;
import iavanish.collegepal.CommonClasses.Course;

/**
 *
 * @author deependra
 */

/**
 * 
 * Discussions in a particular course
 */

public class Discussion {
    
    public String discussionID;
    public Student student;
    public Course course;
    public String discussion;
    public TimeStamp timeStamp;
    public int vote;
    
    public Discussion() {
        
    }
    
}
