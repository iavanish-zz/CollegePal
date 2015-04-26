package himanshu.user.repository;

import java.util.Collection;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


/**
*
*@author himanshu
*/





@RepositoryRestResource(path = "/course")
public interface CourseRepository extends MongoRepository<Course, String>{
	
	public Collection<Course> findByCourseIdIgnoreCase(
			@Param ("courseid") String courseId);
}
