package collegepal.himanshu.integration.test;

import static org.junit.Assert.*;

import java.util.Collection;

import himanshu.user.client.CourseClientApi;
import himanshu.user.repository.Course;

import org.junit.Test;

import collegepal.himanshu.data.TestDataUser;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RequestInterceptor.RequestFacade;
import retrofit.RestAdapter.LogLevel;

/**
*
*@author himanshu
*/


public class CourseClientApiFindTest {

	private final String URL = "http://localhost:8080";

	
	private CourseClientApi CourseService = new RestAdapter.Builder()
	.setEndpoint(URL).setLogLevel(LogLevel.FULL).build()
			.create(CourseClientApi.class);

	@Test
	public void testFindCourse() throws Exception {
		
		/*Collection<Course> courses = CourseService.findByCourseIdIgnoreCase("CSE542");
		Collection<Course> allCourses = CourseService.getCourseList();
		assertTrue(allCourses.contains(courses));
		*/
		Collection<Course> courses1 = CourseService.findByCourseNameContainingIgnoreCase("Practice of Programming");
		Collection<Course> allCourses1 = CourseService.getCourseList();
		assertTrue(allCourses1.contains(courses1));
		
		/*Collection<Course> courses2 = CourseService.findByPreRequisitiesContainingIgnoreCase("Practice of Programming");
		Collection<Course> allCourses2 = CourseService.getCourseList();
		assertTrue(allCourses2.contains(courses2));
		
		Collection<Course> courses3 = CourseService.findByInstructorsContainingIgnoreCase("Avanish Singh");
		Collection<Course> allCourses3 = CourseService.getCourseList();
		assertTrue(allCourses3.contains(courses3));*/
	}

}
