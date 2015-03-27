
package iavanish.collegepal.Resource;

import iavanish.collegepal.CommonClasses.DataBaseRead;
import iavanish.collegepal.CommonClasses.Course;

/**
 *
 * @author deependra
 */

/**
 * 
 * View resources for a course
 */

public class ViewResource {
    
    private Course course;
    
    private DataBaseRead dataBaseRead;
    
    public ViewResource(Course course) {
        this.course = course;
    }
    
    public Resource[] viewResource() {
        return new Resource[10];
    }
    
}
