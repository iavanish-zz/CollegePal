package collegepal.himanshu.data;

import java.util.UUID;
import java.util.Vector;

import himanshu.user.repository.User;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.bcel.internal.util.Objects;

/**
*
*@author himanshu
*/

public class TestDataUser {

	private static final ObjectMapper objectMapper = new ObjectMapper();
	public static User randomUser() {
		
		
		String id = UUID.randomUUID().toString();
		Vector<String> skills = new Vector<String>();
		skills.add("JAVA Programming");
		
		
		Vector<String> courseEnrolled = new Vector<String>();
		courseEnrolled.add("Practice of Programming");
		
		Vector<String> courseOffering = new Vector<String>();
		courseOffering.add("Smart Coding");
		return new User("112e45b1-c3bc-49c6-9b0c-8abafedaa3de","112e45b1-c3bc-49c6-9b0c-8abafedaa3de","avanishsingh@gmail.com",
				"avanish singh","MTech Ist year","CSE","IIIT Delhi",skills,courseEnrolled,courseOffering) ;
	}
	
	
	public static String toJson(Object o) throws Exception{
		return objectMapper.writeValueAsString(o);
	}
}
