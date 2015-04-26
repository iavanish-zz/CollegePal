package iavanish.collegepal.Courses;

import java.util.Collection;

import iavanish.collegepal.DiscussionForum.Discussion;
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

    @GET("/course/search/findByfindByCourseIdIgnoreCase")
    public Collection<Course> findByCourseIdIgnoreCase(@Query("courseid") String courseId);
}
