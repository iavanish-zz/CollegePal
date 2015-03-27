
package iavanish.collegepal.DiscussionForum;

import iavanish.collegepal.CommonClasses.DataBaseRead;

/**
 *
 * @author deependra
 */

/**
 * 
 * Show comments in a course
 */

public class ShowComment {

    private Discussion discussion;
    
    private DataBaseRead dataBaseRead;
    
    public ShowComment(Discussion discussion) {
        this.discussion = discussion;
    }
    
    public Comment[] showComment() {
        return new Comment[10];
    }
    
}
