package himanshu.user;



import himanshu.user.json.ResourcesMapper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

/**Main Class
*
*@author himanshu
*/


@EnableAutoConfiguration
@EnableMongoRepositories
@EnableWebMvc
@Configuration
@ComponentScan
public class Application extends RepositoryRestMvcConfiguration {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		System.out.println("");

		System.out.println("                            _                \\       /\n" + 
			"|_| o __  _ __  _ |_       |_) _  _  |  _     \\ __  / \n" + 
			"| | | |||(_|| |_> | ||_|   | \\(_)(_  |<_>      \\|||/  \n" + 
			"");
	}
	@Override
	public ObjectMapper halObjectMapper(){
		return new ResourcesMapper();
	}

}
