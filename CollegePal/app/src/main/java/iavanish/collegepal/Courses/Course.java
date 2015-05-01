package iavanish.collegepal.Courses;

import com.google.common.base.Objects;

import java.util.Vector;

/**
 * Created by Himanshu on 4/18/2015.
 */
public class Course {

    private String id;

    private String str;
    private String courseId;
    private String courseName;
    private String admin;
    private String overview;
    private String institution;
    private Vector<String> preRequisites;
    private Vector<String> postConditions;
    private Vector<String> instructors;
    private Vector<String> courseTA;
    private Vector<String> studentRegistered;

    public Course(String id,String str,String courseId, String courseName, String admin, String overview, String institution, Vector<String> preRequisites, Vector<String> postConditions, Vector<String> instructors, Vector<String> courseTA, Vector<String> studentRegistered) {
        super();
        this.id=id;

        this.str=str;
        this.courseId=courseId;
        this.courseName = courseName;
        this.admin = admin;
        this.overview = overview;
        this.institution = institution;
        this.preRequisites = preRequisites;
        this.postConditions = postConditions;
        this.instructors = instructors;
        this.courseTA = courseTA;
        this.studentRegistered=studentRegistered;

    }
    public Course()
    {
        //dummy constructor for jackson
    }

    public String getId(){
        return this.id;

    }
    public void setId(String temp){
        this.id = temp;
    }

    public String getStr(){
        return this.str;
    }

    public void setStr(String temp){
        this.str= temp;
    }

    public String getCourseId(){
        return this.courseId;
    }

    public void setCourseId(String temp){
        this.courseId = temp;
    }

    public void setCourseName(String temp){
        this.courseName = temp;
    }
    public String getCourseName(){
        return this.courseName;
    }

    public void setAdmin(String temp){
        this.admin = temp;
    }
    public String getAdmin(){
        return this.admin;
    }
    public String getOverview(){
        return this.overview;
    }

    public void setOverview(String temp){
        this.overview = temp;
    }

    public String getInstitution(){
        return this.institution;
    }

    public void setInstitution(String temp){
        this.institution = temp;
    }

    public Vector<String> getPreRequisites(){
        return this.preRequisites;
    }

    public void setPreRequisites(Vector<String> temp){
        this.preRequisites = temp;
    }

    public Vector<String> getPostConditions(){
        return this.postConditions;
    }

    public void setPostConditions(Vector<String> temp){
        this.postConditions = temp;
    }
    public Vector<String> getInstructors(){
        return this.instructors;
    }

    public void setInstructors(Vector<String> temp){
        this.instructors = temp;
    }
    public Vector<String> getStudentRegistered(){
        return this.studentRegistered;
    }

    public void setStudentRegistered(Vector<String> temp){
        this.studentRegistered = temp;
    }
    public Vector<String> getCourseTA(){
        return this.courseTA;
    }

    public void setCourseTA(Vector<String> temp){
        this.courseTA = temp;
    }

    @Override
    public int hashCode() {
        // Google Guava provides great utilities for hashing
        return Objects.hashCode(courseId, courseName);
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Course) {
            Course other = (Course) obj;
            // Google Guava provides great utilities for equals too!
            return Objects.equal(courseId, other.courseId);

        } else {
            return false;
        }
    }

}











