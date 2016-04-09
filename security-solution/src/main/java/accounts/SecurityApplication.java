/**
 * 
 */
package accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import config.MvcConfig;
import config.RootConfig;
import config.SecurityConfig;


/**
 *
 */
@SpringBootApplication
@Import({MvcConfig.class,RootConfig.class,SecurityConfig.class})
public class SecurityApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.setProperty("spring.profiles.active", "jpa");
		SpringApplication.run(SecurityApplication.class, args);
	}

}
