package iavanish.collegepal.DiscussionForum;

import com.google.common.base.Objects;

/**
 * Created by Himanshu on 4/18/2015.
 */
public class Discussion {
    private String id;

    private String discussionId;
    private String courseId;
    private String emailId;
    private String discussionTopic;
    private String discussionDetails;
    private String timeOfDiscussionPosted;
    private String remarks;
    private int vote;

    public Discussion(String id,String discussionId, String courseId, String emailId, String discussionTopic, String discussionDetails, String timeOfDiscussionPosted,int vote) {
        super();
        this.id=id;
        this.discussionId=discussionId;
        this.courseId=courseId;

        this.emailId = emailId;
        this.discussionTopic = discussionTopic;
        this.discussionDetails = discussionDetails;
        this.timeOfDiscussionPosted=timeOfDiscussionPosted;
        this.vote=vote;

    }
    public Discussion()
    {
        //dummy constructor for jackson
    }

    public String getId(){
        return this.id;

    }
    public void setId(String temp){
        this.id = temp;
    }

    public String getDiscussionId(){
        return this.discussionId;
    }

    public void setDiscussionId(String temp){
        this.discussionId = temp;
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

    public void setDiscussionTopic(String temp){
        this.discussionTopic = temp;
    }
    public String getDiscussionTopic(){
        return this.discussionTopic;
    }
    public String getDiscussionDetails(){
        return this.discussionDetails;
    }

    public void setDiscussionDetails(String temp){
        this.discussionDetails = temp;
    }

    public String getTimeOfDiscussionPosted(){
        return this.timeOfDiscussionPosted;
    }

    public void setTimeOfDiscussionPosted(String temp){
        this.timeOfDiscussionPosted = temp;
    }
    public int getVote(){
        return this.vote;
    }

    public void setVote(int temp){
        this.vote = temp;
    }
    @Override
    public int hashCode() {
        // Google Guava provides great utilities for hashing
        return Objects.hashCode(discussionId, courseId, emailId);
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Discussion) {
            Discussion other = (Discussion) obj;
            // Google Guava provides great utilities for equals too!
            return Objects.equal(discussionId, other.discussionId);

        } else {
            return false;
        }
    }

}

