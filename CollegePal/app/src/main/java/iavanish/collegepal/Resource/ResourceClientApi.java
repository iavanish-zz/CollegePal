package iavanish.collegepal.Resource;

import java.util.Collection;

import iavanish.collegepal.Start.User;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by Himanshu on 4/18/2015.
 */
public interface ResourceClientApi {
        @GET("/resource")
    public Collection<Resource> getResourceList();

    @POST("/resource")
    public Boolean addResource(@Body Resource resource);

    @GET("/user/search/findByResourceId")
    public Collection<Resource> findByResourceId(@Query("resourceid") String resourceId);

}
