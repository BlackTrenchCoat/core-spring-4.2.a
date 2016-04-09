package samples.jmx.annotations;

import java.io.IOException;

import javaconfig.JavaConfigDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Sets up the same example as {@link JavaConfigDemo} but wil all configuration
 * in XML. Clearly a a much harder approach but useful for a legacy application that
 * doesn't use Java Config.
 */
@Configuration
@ImportResource("samples/jmx/annotations/annotations-demo.xml")
public class AnnotationsDemo {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(AnnotationsDemo.class);
		System.out.println(" >> Connect with JConsole and look for "
				+ "the service MBean - play with its operations");
		System.out.print(" >> Ready.  Press Enter to Stop...");
		System.in.read();
	}
}
