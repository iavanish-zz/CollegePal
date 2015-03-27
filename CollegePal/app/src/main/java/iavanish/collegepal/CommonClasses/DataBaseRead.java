
package iavanish.collegepal.CommonClasses;

import iavanish.collegepal.Courses.Feedback;
import iavanish.collegepal.Deadlines.PrivateDeadline;
import iavanish.collegepal.Deadlines.PublicDeadline;
import iavanish.collegepal.Deadlines.SuggestedActivities;
import iavanish.collegepal.DiscussionForum.Comment;
import iavanish.collegepal.DiscussionForum.Discussion;
import iavanish.collegepal.Resource.Resource;

/**
 *
 * @author himanshu
 */

/**
 * 
 * All the database read operations occur here
 */

public class DataBaseRead {
    
    public DataBaseRead() {
        
    }
    
    public boolean checkIfStudentIsInDB(Student student) {
        return false;
    }
    
    public PublicDeadline[] publicDeadline(Student student) {
        return new PublicDeadline[10];
    }
    
    public PrivateDeadline[] privateDeadline(Student student) {
        return new PrivateDeadline[10];
    }
    
    public SuggestedActivities[] suggestedActivities(Student student) {
        return new SuggestedActivities[10];
    }
    
    public Feedback[] viewFeedback(Course course) {
        return new Feedback[10];
    }
    
    public Course[] showAllCourses() {
        return new Course[10];
    }
    
    public Course[] showRegisteredCourses(Student student) {
        return new Course[10];
    }
    
    public Discussion[] showDiscussion(Course course) {
        return new Discussion[10];
    }
    
    public Comment[] showComments(Discussion discussion) {
        return new Comment[10];
    }
    
    public Resource[] showResource(Course course) {
        return new Resource[10];
    }
    
}
