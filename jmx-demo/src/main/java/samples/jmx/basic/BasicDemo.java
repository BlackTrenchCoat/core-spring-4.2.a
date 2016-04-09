package samples.jmx.basic;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("samples/jmx/basic/basic-demo.xml")
public class BasicDemo {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(BasicDemo.class);

		System.out.println(" >> Connect with JConsole and look for "
				+ "the service MBean - play with its operations");
		System.out.print(" >> Ready.  Press Enter to Stop...");
		System.in.read();
	}
}
