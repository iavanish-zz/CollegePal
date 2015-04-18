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

    @GET("/user/search/findByDeadlineId")
    public Collection<Deadline> findByDeadlineId(@Query("deadlineid") String deadlineId);

}

