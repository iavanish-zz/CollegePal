
package iavanish.collegepal.CommonClasses;

import iavanish.collegepal.Courses.Feedback;
import iavanish.collegepal.Deadlines.Deadline;
import iavanish.collegepal.DiscussionForum.Comment;
import iavanish.collegepal.DiscussionForum.Discussion;
import iavanish.collegepal.Resource.Resource;

/**
 *
 * @author himanshu
 */

/**
 * 
 * All the database write operations occur here
 */

public class DataBaseWrite extends DataBaseRead {
    
    public DataBaseWrite() {
        super();
    }
    
    public boolean enterDeadline(Deadline deadline) {
        return false;
    }
    
    public boolean updateDeadline(Deadline deadline) {
        return false;
    }
    
    public boolean updateCourse(Course course) {
        return false;
    }
    
    public boolean deleteCourse(Course course) {
        return false;
    }
    
    public boolean registerFeedback(Feedback feedback) {
        return false;
    }
    
    public boolean selectCourse(Student student, Course course) {
        return false;
    }
    
    public boolean leaveCourse(Student student, Course course) {
        return false;
    }
    
    public boolean newDiscussion(Discussion discussion) {
        return false;
    }
    
    public boolean newComment(Comment comment) {
        return false;
    }
    
    public boolean voteOnDiscussion(Discussion discussion, Student student, int vote) {
        return false;
    }
    
    public boolean voteOnComment(Comment comment, Student student, int vote) {
        return false;
    }
    
    public boolean deleteDiscussion(Discussion discussion, Student student) {
        return false;
    }
    
    public boolean deleteComment(Comment comment, Student student) {
        return false;
    }
    
    public boolean newNotes(Resource resource) {
        return false;
    }
    
    public boolean newSnapshot(Resource resource) {
        return false;
    }
    
    public boolean newUpload(Resource resource) {
        return false;
    }
    
    public boolean updateResource(Resource resource, Student student) {
        return false;
    }
    
}
