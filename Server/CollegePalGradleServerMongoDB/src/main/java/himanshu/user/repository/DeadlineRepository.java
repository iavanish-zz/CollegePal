package himanshu.user.repository;

import java.util.Collection;
import java.util.List;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestMapping;

/**
*
*@author himanshu
*/

@RepositoryRestResource(path = "/deadline")
public interface DeadlineRepository extends MongoRepository<Deadline, String>{

	
	public Collection<Deadline> findByDeadlineIdIgnoreCase(
			@Param ("deadlineid") String deadlineId);
	
}
