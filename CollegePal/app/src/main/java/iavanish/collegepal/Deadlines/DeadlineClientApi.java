package iavanish.collegepal.Deadlines;

import java.util.Collection;

import iavanish.collegepal.DiscussionForum.Discussion;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by Himanshu on 4/18/2015.
 */
public interface DeadlineClientApi {

    @GET("/deadline")
    public Collection<Deadline> getDeadlineList();

    @POST("/deadline")
    public Boolean addDeadline(@Body Deadline deadline);

    @GET("/deadline/search/findByDeadlineIdIgnoreCase")
    public Collection<Deadline> findByDeadlineIdIgnoreCase(@Query("deadlineid") String deadlineId);

    @GET("/deadline/search/findByCourseIdIgnoreCase")
    public Collection<Deadline> findByCourseIdIgnoreCase(@Query("courseid") String courseId);

    @GET("/deadline/search/findByEmailIdIgnoreCase")
    public Collection<Deadline> findByEmailIdIgnoreCase(@Query("emailid") String emailId);

    @GET("/deadline/search/findByEmailIdContainingIgnoreCase")
    public Collection<Deadline> findByEmailIdContainingIgnoreCase(@Query("emailid") String emailid);


}
