package objectnaming;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jmx.export.naming.IdentityNamingStrategy;

import declarative.DeclarativeDemo;
import declarative.MessageService;

/**
 * Demonstrates access to the {@link MessageService} using JMX (for example in
 * JConsole).
 * <p>
 * Uses a simple XML configuration that exposes all public methods and treats
 * anything with a getter or setter as an attribute, just
 * {@link DeclarativeDemo} but uses the {@link IdentityNamingStrategy} to
 * automatically define bean names. In the declarative-demo the message-service
 * bean name is explicitly set to "service:name=messageService". Thus the bean
 * "messageService" appears in the MBean folder "service".
 * <p>
 * DEMO: Run the application and then connect to it using JConsole. Because of
 * the new naming strategy, the service bean appears (at the bottom of the list
 * on the MBeans panel) in the folder "objectnaming" and is now called
 * <code>MessageService<code> (its class name).
 */
@Configuration
@ImportResource("objectnaming/objectnaming-config.xml")
public class ObjectNamingDemo {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(ObjectNamingDemo.class);
		System.out
				.println(" >> Connect with JConsole and look for the service MBean "
						+ "- see how its name and MBean folder have changed");
		System.out.print(" >> Ready.  Press Enter to Stop...");
		System.in.read();
	}

}
