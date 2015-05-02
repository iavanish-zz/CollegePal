package iavanish.collegepal.Courses;

import java.util.Collection;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by Himanshu on 4/18/2015.
 */
public interface CourseClientApi {

    @GET("/course")
    public Collection<Course> getCourseList();

    @POST("/course")
    public Boolean addCourse(@Body Course course);

    @GET("/course/search/findByCourseIdIgnoreCase")
    public Collection<Course> findByCourseIdIgnoreCase(@Query("courseid") String courseId);

    @GET("/course/search/findByCourseNameIgnoreCase")
    public Collection<Course> findByCourseNameIgnoreCase(@Query("coursename") String courseName);

    @GET("/course/search/findByCourseNameContainingIgnoreCase")
    public Collection<Course> findByCourseNameContainingIgnoreCase(@Query("coursename")String courseName);

    @GET("/course/search/findByPreRequisitesContainingIgnoreCase")
    public Collection<Course> findByPreRequisitesContainingIgnoreCase(@Query("prerequisites") String preRequisites);

    @GET("/course/search/findByInstructorsContainingIgnoreCase")
    public Collection<Course> findByInstructorsContainingIgnoreCase(@Query("instructors") String instructors);

    @GET("/course/search/findByAdminContainingIgnoreCase")
    public Collection<Course> findByAdminContainingIgnoreCase(@Query("admin") String admin);

	/*@GET("/course/search/deleteByCourseIdContainingIgnoreCase")
	public long deleteByCourseIdContainingIgnoreCase(
			@Query ("courseid") String courseId);*/
}
