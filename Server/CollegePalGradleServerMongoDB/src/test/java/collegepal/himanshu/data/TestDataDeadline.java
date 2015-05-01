package collegepal.himanshu.data;

import java.util.UUID;
import java.util.Vector;

import himanshu.user.repository.Deadline;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.bcel.internal.util.Objects;

/**
*
*@author himanshu
*/


public class TestDataDeadline {

	private static final ObjectMapper objectMapper = new ObjectMapper();
	public static Deadline randomDeadline() {
		
		
		String id = UUID.randomUUID().toString();
		
		return new Deadline("199f45a1-c3bc-49c6-9b0c-8abafedaa3de","199f45a1-c3bc-49c6-9b0c-8abafedaa3de","deadline1","CSE304",
				"avanishsingh@gmail.com","28/06/2015","Mid Sem Deadline","public",false) ;
	}
	
	
	public static String toJson(Object o) throws Exception{
		return objectMapper.writeValueAsString(o);
	}
}
