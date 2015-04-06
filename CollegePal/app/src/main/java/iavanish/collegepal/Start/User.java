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

    private String emailId;
    private String name;
    private String course;
    private String branch;
    private String institution;
    private Vector<String> skills;
    private float rating;
    private String professorTa;
    private String subjectTa;


    public User(String id,String emailId, String name, String course, String branch, String institution, Vector<String> skills, float rating, String professorTa, String subjectTa) {
        super();
        this.id=id;
        this.emailId=emailId;

        this.name = name;
        this.course = course;
        this.branch = branch;
        this.institution = institution;
        this.skills = skills;
        this.rating = rating;
        this.professorTa = professorTa;
        this.subjectTa = subjectTa;

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

    public void setSkills(Vector<String> temp){
        this.skills = temp;
    }

    public Vector<String> getSkills(){
        return this.skills;
    }

    public void setInstitution(String temp){
        this.institution = temp;
    }

    public float getRating(){
        return this.rating;
    }

    public void setRating(float temp){
        this.rating = temp;
    }

    public String getProfessorTa(){
        return this.professorTa;
    }

    public void setProfessorTa(String temp){
        this.professorTa = temp;
    }

    public String getSubjectTa(){
        return this.subjectTa;
    }

    public void setSubjectTa(String temp){
        this.subjectTa = temp;
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
