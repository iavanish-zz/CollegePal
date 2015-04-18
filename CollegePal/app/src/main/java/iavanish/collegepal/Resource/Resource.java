package iavanish.collegepal.Resource;

import com.google.common.base.Objects;

/**
 * Created by Himanshu on 4/18/2015.
 */
public class Resource {
    private String id;

    private String resourceId;
    private String resourceName;
    private String courseId;
    private String emailId;
    private String resourceDetails;
    private String resourceType;
    public Resource(String id,String resourceId, String resourceName, String courseId, String emailId, String resourceDetails, String resourceType) {
        super();
        this.id=id;
        this.resourceId=resourceId;
        this.resourceName=resourceName;
        this.courseId=courseId;

        this.emailId = emailId;
        this.resourceDetails = resourceDetails;
        this.resourceType = resourceType;

    }
    public Resource()
    {
        //dummy constructor for jackson
    }

    public String getId(){
        return this.id;

    }
    public void setId(String temp){
        this.id = temp;
    }

    public String getResourceId(){
        return this.resourceId;
    }

    public void setResourceId(String temp){
        this.resourceId = temp;
    }

    public String getResourceName(){
        return this.resourceName;
    }

    public void setResourceName(String temp){
        this.resourceName = temp;
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

    public void setResourceDetails(String temp){
        this.resourceDetails = temp;
    }
    public String getResourceDetails(){
        return this.resourceDetails;
    }
    public String getResourceType(){
        return this.resourceType;
    }

    public void setResourceType(String temp){
        this.resourceType = temp;
    }


    @Override
    public int hashCode() {
        // Google Guava provides great utilities for hashing
        return Objects.hashCode(resourceId, courseId, emailId);
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Resource) {
            Resource other = (Resource) obj;
            // Google Guava provides great utilities for equals too!
            return Objects.equal(resourceId, other.resourceId);

        } else {
            return false;
        }
    }

}
