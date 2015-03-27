
package iavanish.collegepal.DiscussionForum;

import iavanish.collegepal.CommonClasses.DataBaseWrite;
import iavanish.collegepal.CommonClasses.Student;

/**
 *
 * @author deependra
 */

/**
 * 
 * Delete a discussion
 */

public class DeleteDiscussion {
    
    public Discussion discussion;
    public Student student;
    
    private DataBaseWrite dataBaseWrite;
    
    public DeleteDiscussion(Discussion discussion, Student student) {
        this.discussion = discussion;
        this.student = student;
    }
    
    public void deleteDiscussion() {
        
    }
    
}
