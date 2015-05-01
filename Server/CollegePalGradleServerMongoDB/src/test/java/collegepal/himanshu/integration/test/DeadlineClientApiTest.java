package collegepal.himanshu.integration.test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import himanshu.user.client.DeadlineClientApi;
import himanshu.user.repository.Deadline;

import org.junit.Test;

import collegepal.himanshu.data.TestDataDeadline;
import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;

/**
*
*@author himanshu
*/


public class  DeadlineClientApiTest {

	private final String TEST_URL = "http://localhost:8080";

	private DeadlineClientApi DeadlineService = new RestAdapter.Builder()
			.setEndpoint(TEST_URL).setLogLevel(LogLevel.FULL).build()
			.create(DeadlineClientApi.class);

	private Deadline deadline = TestDataDeadline.randomDeadline();
	
	
	@Test
	public void testDataAddAndList() throws Exception {
		
		// Add the user
		DeadlineService.addDeadline(deadline);
		
		// We should get back the user that we added above
		Collection<Deadline> deadline = DeadlineService.getDeadlineList();
		assertTrue(deadline.contains(deadline));
	}

}
