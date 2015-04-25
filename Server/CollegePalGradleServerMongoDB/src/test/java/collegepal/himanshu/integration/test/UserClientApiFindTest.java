package collegepal.himanshu.integration.test;

import static org.junit.Assert.*;

import java.util.Collection;

import himanshu.user.client.UserClientApi;
import himanshu.user.repository.User;

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


public class UserClientApiFindTest {

	private final String URL = "http://localhost:8080";

	
	private UserClientApi UserService = new RestAdapter.Builder()
	.setEndpoint(URL).setLogLevel(LogLevel.FULL).build()
			.create(UserClientApi.class);

	@Test
	public void testFindUser() throws Exception {
		
		Collection<User> users = UserService.findByEmailIdIgnoreCase("himanshu1449@iiitd.ac.in");
		Collection<User> allUsers = UserService.getUserList();
		assertTrue(allUsers.contains(users));
	}

}
