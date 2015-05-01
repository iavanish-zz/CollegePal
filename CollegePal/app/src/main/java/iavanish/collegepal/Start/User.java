package iavanish.collegepal.Start;

import com.google.common.base.Objects;

import java.util.Vector;

/**
 *Class to represent a user in DB
 *
 *@author himanshu
 *
 */

public class User {

    private String id;

    private String str;
    private String emailId;
    private String name;
    private String course;
    private String branch;
    private String institution;
    private Vector<String> skills;
    private Vector<String> courseEnrolled;
    private Vector<String> coursesOffering;

    public User(String id,String str, String emailId, String name, String course, String branch, String institution, Vector<String> skills, Vector<String> courseEnrolled, Vector<String> coursesOffering) {
        super();
        this.id=id;
        this.str=str;
        this.emailId=emailId;

        this.name = name;
        this.course = course;
        this.branch = branch;
        this.institution = institution;
        this.skills = skills;
        this.courseEnrolled = courseEnrolled;
        this.coursesOffering = coursesOffering;

    }
    public User()
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


    public String getEmailId(){
        return this.emailId;
    }

    public void setEmailId(String temp){
        this.emailId = temp;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String temp){
        this.name = temp;
    }

    public String getCourse(){
        return this.course;
    }

    public void setCourse(String temp){
        this.course = temp;
    }

    public String getBranch(){
        return this.branch;
    }

    public void setBranch(String temp){
        this.branch = temp;
    }

    public String getInstitution(){
        return this.institution;
    }

    public void setInstitution(String temp){
        this.institution = temp;
    }

    public void setSkills(Vector<String> temp){
        this.skills = temp;
    }

    public Vector<String> getSkills(){
        return this.skills;
    }
    public void setCourseEnrolled(Vector<String> temp){
        this.courseEnrolled = temp;
    }

    public Vector<String> getCourseEnrolled(){
        return this.courseEnrolled;
    }
    public void setCoursesOffering(Vector<String> temp){
        this.coursesOffering = temp;
    }

    public Vector<String> getCoursesOffering(){
        return this.coursesOffering;
    }

    @Override
    public int hashCode() {
        // Google Guava provides great utilities for hashing
        return Objects.hashCode(emailId,name);
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            User other = (User) obj;
            // Google Guava provides great utilities for equals too!
            return Objects.equal(emailId, other.emailId);

        } else {
            return false;
        }
    }

}
