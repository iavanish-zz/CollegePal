
package iavanish.collegepal.Courses;

import iavanish.collegepal.CommonClasses.Course;
import iavanish.collegepal.CommonClasses.DataBaseWrite;
import iavanish.collegepal.CommonClasses.Student;

/**
 *
 * @author iavanish
 */

/**
 * 
 * Delete a course (by administrator of the course)
 */

public class DeleteCourse {
    
    private Course course;
    private Student student;
    
    private DataBaseWrite dataBaseWrite;
    
    public DeleteCourse(Course course, Student student) {
        this.course = course;
        this.student = student;
    }
    
    public void deleteCourse() {
        
    }
    
}
