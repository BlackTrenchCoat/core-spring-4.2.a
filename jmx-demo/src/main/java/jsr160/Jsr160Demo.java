package jsr160;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(Jsr160AppConfig.class)
public class Jsr160Demo {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(Jsr160Demo.class);
		System.out.println(" >> Connect with JConsole and look for "
				+ "the service MBean - play with its operations");
		System.out.print(" >> Ready.  Press Enter to Stop...");
		System.in.read();
	}

}
