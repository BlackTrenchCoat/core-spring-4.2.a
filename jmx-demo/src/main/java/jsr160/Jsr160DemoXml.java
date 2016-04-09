package jsr160;

import infoassembler.MessageService;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import declarative.DeclarativeDemo;

/**
 * Demonstrates access to the {@link MessageService} using JMX (for example in
 * JConsole).
 * <p>
 * Similar to {@link DeclarativeDemo} but provides a JSR-160 compliant connector
 * (allowing JMX clients to connect over RMI) - similar to using Spring's
 * RmiExporter.
 * <p>
 * DEMO: Run the application and then connect to it using JConsole. You cannot
 * connect as a "Local Process", instead select "Remote Process" and use the URL
 * from the configuration:
 * <code>service:jmx:rmi://localhost/jndi/rmi://localhost:1099/jmxconnector</code>
 * The service bean should be appear at the bottom of the list on the MBeans
 * panel. Try playing with its operations.
 * <p>
 * Note that {@link MessageService#invisibleMethod()} is not available as an
 * operation in JConsole.
 */
@Configuration
@ImportResource("jsr160/jsr160-config.xml")
public class Jsr160DemoXml {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(Jsr160DemoXml.class);
		System.out.println(" >> Connect with JConsole and look for "
				+ "the service MBean - play with its operations");
		System.out.print(" >> Ready.  Press Enter to Stop...");
		System.in.read();
	}

}
