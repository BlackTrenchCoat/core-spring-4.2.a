package infoassembler;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import declarative.DeclarativeDemo;

/**
 * Demonstrates access to the {@link MessageService} using JMX (for example in
 * JConsole).
 * <p>
 * Works exactly the same as the {@link DeclarativeDemo} but uses a different
 * configuration. The methods and attributes are exposed to JMX are only those
 * on the {@link MessageCapable} interface.
 * <p>
 * DEMO: Run the application and then connect to it using JConsole. The service
 * bean should be appear at the bottom of the list on the MBeans panel. Try
 * playing with its operations.
 * <p>
 * Note that {@link MessageService#invisibleMethod()} is not available as an
 * operation in JConsole.
 */
@Configuration
@ImportResource("infoassembler/infoassembler-config.xml")
public class InfoAssemblerDemo {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(InfoAssemblerDemo.class);
		System.out.println(" >> Connect with JConsole and look for "
				+ "the service MBean - play with its operations");
		System.out.print(" >> Ready.  Press Enter to Stop...");
		System.in.read();
	}

}
