package himanshu.user.client;

import java.util.Collection;

import himanshu.user.repository.Course;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**Interface for strong typing on both client and server interface
*
*@author himanshu
*/

public interface CourseClientApi {

	
	@GET("/course")
	public Collection<Course> getCourseList();
	
	@POST("/course")
	public Boolean addCourse(@Body Course course);
	
	@GET("/course/search/findByfindByCourseIdIgnoreCase")
	public Collection<Course> findByCourseIdIgnoreCase(@Query("courseid") String courseId);


}
