package himanshu.user.repository;

import java.util.Collection;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import retrofit.http.Query;


/**
*
*@author himanshu
*/


@RepositoryRestResource(path = "/course")
public interface CourseRepository extends MongoRepository<Course, String>{
	
	public Collection<Course> findByCourseIdIgnoreCase(
			@Param ("courseid") String courseId);
	
	public Collection<Course> findByCourseNameContainingIgnoreCase(
			@Param ("coursename") String courseName);
			
	
	public Collection<Course> findByPreRequisitesContainingIgnoreCase(
			@Param("prerequisites") String preRequisites);
	
	
	public Collection<Course> findByInstructorsContainingIgnoreCase(
			@Param ("instructors") String instructors);

	public Collection<Course> findByAdminContainingIgnoreCase(
			@Param ("admin") String admin);
	
	
	
	
	/*public long deleteByCourseIdIgnoreCase(
			@Param ("courseid") String courseId);*/
}
