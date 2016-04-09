package microservices.registration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Once modified, this is all you need to run a Eureka registration server.
 * <p>
 * Start this process FIRST.
 */
// TODO-01: Add annotations to make this a Eureka server using Spring Boot
//          Ignore any httpMapperProperties deprecated warning
public class RegistrationServer {

	/**
	 * Run the application using Spring Boot and an embedded servlet engine.
	 * 
	 * @param args
	 *            Program arguments - ignored.
	 */
	public static void main(String[] args) {
		// TODO-02: Tell server to look for registration-server.properties - see
		//          detailed instructions if you don't know how to do this.

		// TODO-03: Make this run as a Spring Application
		

		// TODO-04: Run this as a Spring Boot application by doing
		//               right-click -> Run As -> Spring Boot App 
		//          Then open the server's Dashboard at http://localhost:1111/ in
		//          a browser
		// TODO-05: Why is this server's port 1111? Where was this configured?
	}

}
