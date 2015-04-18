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
    public Collection<Course> getDiscussionList();

    @POST("/course")
    public Boolean addCourse(@Body Course course);

    @GET("/user/search/findByCourseId")
    public Collection<Course> findByCourseId(@Query("courseid") String courseId);

}
