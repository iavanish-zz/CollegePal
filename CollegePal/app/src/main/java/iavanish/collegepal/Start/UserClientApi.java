package iavanish.collegepal.Start;
import java.util.Collection;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**Interface for strong typing on both client and server interface
 *
 *@author himanshu
 */


public interface UserClientApi {

    @GET("/user")
    public Collection<User> getUserList();

    @POST("/user")
    public Boolean addUser(@Body User user);

    @GET("/user/search/findByEmailId")
    public Collection<User> findByEmailId(@Query("emailid") String emailId);



}
