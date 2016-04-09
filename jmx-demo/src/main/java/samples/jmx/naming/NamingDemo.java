package samples.jmx.naming;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("samples/jmx/naming/naming-demo.xml")
public class NamingDemo {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(NamingDemo.class);
		System.out.println(" >> Connect with JConsole and look for "
				+ "the service MBean - play with its operations");
		System.out.print(" >> Ready.  Press Enter to Stop...");
		System.in.read();
	}
}
