package iavanish.collegepal.DiscussionForum;

import java.util.Collection;

import iavanish.collegepal.Resource.Resource;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by Himanshu on 4/18/2015.
 */
public interface DiscussionClientApi {
    @GET("/discussion")
    public Collection<Discussion> getDiscussionList();

    @POST("/discussion")
    public Boolean addDiscussion(@Body Discussion discussion);

    @GET("/user/search/findByDiscussionId")
    public Collection<Discussion> findByDiscussionId(@Query("discussionid") String discussionId);

}
