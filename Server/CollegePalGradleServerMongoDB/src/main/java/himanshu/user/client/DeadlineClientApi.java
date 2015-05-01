package himanshu.user.client;

import java.util.Collection;

import himanshu.user.repository.Deadline;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**Interface for strong typing on both client and server interface
*
*@author himanshu
*/

public interface DeadlineClientApi {

	
	@GET("/deadline")
	public Collection<Deadline> getDeadlineList();
	
	@POST("/deadline")
	public Boolean addDeadline(@Body Deadline deadline);
	
	@GET("/deadline/search/findByDeadlineIdIgnoreCase")
	public Collection<Deadline> findByDeadlineIdIgnoreCase(@Query("deadlineid") String deadlineId);


}
