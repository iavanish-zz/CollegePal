package collegepal.himanshu.integration.test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import himanshu.user.client.CourseClientApi;
import himanshu.user.repository.Course;

import org.junit.Test;

import collegepal.himanshu.data.TestDataCourse;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;

/**
*
*@author himanshu
*/


public class  CourseClientApiTest {

	private final String TEST_URL = "http://localhost:8080";

	private CourseClientApi CourseService = new RestAdapter.Builder()
			.setEndpoint(TEST_URL).setLogLevel(LogLevel.FULL).build()
			.create(CourseClientApi.class);

	private Course course = TestDataCourse.randomCourse();
	
	
	@Test
	public void testDataAddAndList() throws Exception {
		
		// Add the user
		CourseService.addCourse(course);
		
		// We should get back the user that we added above
		Collection<Course> course = CourseService.getCourseList();
		assertTrue(course.contains(course));
	}

}
