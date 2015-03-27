
package iavanish.collegepal.DiscussionForum;

import iavanish.collegepal.CommonClasses.DataBaseWrite;
import iavanish.collegepal.CommonClasses.Student;

/**
 *
 * @author deependra
 */

/**
 * 
 * DeleteComment
 */

public class DeleteComment {
    
    public Comment comment;
    public Student student;
    
    private DataBaseWrite dataBaseWrite;
    
    public DeleteComment(Comment comment, Student student) {
        this.comment = comment;
        this.student = student;
    }
    
    public void deleteComment() {
        
    }
    
}
