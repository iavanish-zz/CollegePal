package collegepal.himanshu.data;

import java.util.UUID;
import java.util.Vector;

import himanshu.user.repository.Course;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.bcel.internal.util.Objects;

/**
*
*@author himanshu
*/


public class TestDataCourse {

	private static final ObjectMapper objectMapper = new ObjectMapper();
	public static Course randomCourse() {
		
		
		String id = UUID.randomUUID().toString();
		Vector<String> preRequisites = new Vector<String>();
		preRequisites.add("Advanced JAVA Programming");
		
		Vector<String> postConditions = new Vector<String>();
		postConditions.add("Good Concepts of Programming");
		
		Vector<String> instructors = new Vector<String>();
		instructors.add("Rahul Purandare");
		
		Vector<String> courseTA = new Vector<String>();
		courseTA.add("Anindya Srivastava");
		
		Vector<String> studentsRegistered = new Vector<String>();
		studentsRegistered.add("Ashish Bandil");
		return new Course("199f45a1-c3bc-49c6-9b0c-8abafedaa3de","CSE304","Practice of Programming",
				"himanshu1449@iiitd.ac.in","Course for basic understanding of design principles and how to code","IIIT Delhi",preRequisites,postConditions,instructors,courseTA,studentsRegistered) ;
	}
	
	
	public static String toJson(Object o) throws Exception{
		return objectMapper.writeValueAsString(o);
	}
}
