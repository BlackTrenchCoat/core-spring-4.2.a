package declarative;

import infoassembler.InfoAssemblerDemo;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Demonstrates access to the {@link MessageService} using JMX (for example in
 * JConsole).
 * <p>
 * Uses a simple XML configuration that exposes all public methods and treats
 * anything with a getter or setter as an attribute. In practice you would not
 * normally want to allow this much access to JMX. Compare this configuration
 * with {@link InfoAssemblerDemo}.
 * <p>
 * DEMO: Run the application and then connect to it using JConsole. The service
 * bean should be appear at the bottom of the list on the MBeans panel. Try
 * playing with its operations.
 */
@Configuration
@ImportResource("declarative/declarative-config.xml")
public class DeclarativeDemo {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(DeclarativeDemo.class);
		System.out.println(" >> Connect with JConsole and look for "
				+ "the service MBean - play with its operations");
		System.out.print(" >> Ready.  Press Enter to Stop...");
		System.in.read();
	}

}
