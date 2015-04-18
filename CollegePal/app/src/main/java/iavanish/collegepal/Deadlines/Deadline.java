package iavanish.collegepal.Deadlines;

import com.google.common.base.Objects;

/**
 * Created by Himanshu on 4/18/2015.
 */
public class Deadline {

    private String id;

    private String deadlineId;
    private String courseId;
    private String emailId;
    private String date;
    private String deadlineDetails;
    private String deadlineType;
    private Boolean isDeadOver;
    public Deadline(String id,String deadlineId, String courseId,String emailId, String date, String deadlineDetails, String deadlineType, Boolean isDeadOver) {
        super();
        this.id=id;
        this.deadlineId=deadlineId;
        this.courseId=courseId;

        this.emailId = emailId;
        this.date = date;
        this.deadlineDetails = deadlineDetails;
        this.deadlineType = deadlineType;
        this.isDeadOver = isDeadOver;

    }
    public Deadline()
    {
        //dummy constructor for jackson
    }

    public String getId(){
        return this.id;

    }
    public void setId(String temp){
        this.id = temp;
    }

    public String getDeadlineId(){
        return this.deadlineId;
    }

    public void setDeadlineId(String temp){
        this.deadlineId = temp;
    }


    public String getCourseId(){
        return this.courseId;
    }

    public void setCourseId(String temp){
        this.courseId = temp;
    }

    public void setEmailId(String temp){
        this.emailId = temp;
    }
    public String getEmailId(){
        return this.emailId;
    }

    public void setDate(String temp){
        this.date = temp;
    }
    public String getDate(){
        return this.date;
    }
    public String getDeadlineDetails(){
        return this.deadlineDetails;
    }

    public void setDeadlineDetails(String temp){
        this.deadlineDetails = temp;
    }

    public String getDeadlineType(){
        return this.deadlineType;
    }

    public void setDeadlineType(String temp){
        this.deadlineType = temp;
    }

    public Boolean getIsDeadOver(){
        return this.isDeadOver;
    }

    public void setIsDeadOver(Boolean temp){
        this.isDeadOver = temp;
    }


    @Override
    public int hashCode() {
        // Google Guava provides great utilities for hashing
        return Objects.hashCode(deadlineId, courseId);
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Deadline) {
            Deadline other = (Deadline) obj;
            // Google Guava provides great utilities for equals too!
            return Objects.equal(deadlineId, other.deadlineId);

        } else {
            return false;
        }
    }

}
