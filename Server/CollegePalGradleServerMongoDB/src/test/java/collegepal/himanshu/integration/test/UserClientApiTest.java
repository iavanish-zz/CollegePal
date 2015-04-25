package collegepal.himanshu.integration.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import himanshu.user.client.UserClientApi;
import himanshu.user.repository.User;

import org.junit.Test;
import collegepal.himanshu.data.TestDataUser;

import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;

/**
*
*@author himanshu
*/



public class UserClientApiTest {

	private final String TEST_URL = "http://localhost:8080";

	private UserClientApi UserService = new RestAdapter.Builder()
			.setEndpoint(TEST_URL).setLogLevel(LogLevel.FULL).build()
			.create(UserClientApi.class);

	private User user = TestDataUser.randomUser();
	
	
	@Test
	public void testDataAddAndList() throws Exception {
		
		// Add the user
		UserService.addUser(user);
		
		// We should get back the user that we added above
		Collection<User> users = UserService.getUserList();
		assertTrue(users.contains(user));
	}

}
