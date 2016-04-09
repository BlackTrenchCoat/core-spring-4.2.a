package javaconfig;

import infoassembler.InfoAssemblerDemo;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Demonstrates access to the {@link MessageService} using JMX (for example in
 * JConsole).
 * <p>
 * Uses a simple Java Configuration and exposes those methods annotated with @ManagedOperation
 * and @ManagedAttribute. Compare this version with the XML alternatives
 * {@link declarative.DeclarativeDemo}. with {@link InfoAssemblerDemo}.
 * <p>
 * DEMO: Run the application and then connect to it using JConsole. The service
 * bean should be appear at the bottom of the list on the MBeans panel. Try
 * playing with its operations.
 * <p>
 * Note that {@link MessageService#setMessage(String))} is missing because it is
 * not annotated with @ManagedAttribute.
 */
@Configuration
@Import(AppConfig.class)
public class JavaConfigDemo {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(JavaConfigDemo.class);
		System.out.println(" >> Connect with JConsole and look for "
				+ "the service MBean - play with its operations");
		System.out.print(" >> Ready.  Press Enter to Stop...");
		System.in.read();
	}

}
