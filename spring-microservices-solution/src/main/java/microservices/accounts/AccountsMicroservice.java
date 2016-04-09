package microservices.accounts;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import accounts.AccountManager;
import accounts.HomeController;
import accounts.internal.JpaAccountManager;

/**
 * Run as a micro-service, registering with the Discovery Server (Eureka).
 * <p>
 * Run this process second.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EntityScan("rewards")
//Ignore httpMapperProperties deprecated warning
public class AccountsMicroservice {

	protected Logger logger = Logger.getLogger(AccountsMicroservice.class.getName());

	/**
	 * Run the application using Spring Boot and an embedded servlet engine.
	 * 
	 * @param args
	 *            Program arguments - ignored.
	 */
	public static void main(String[] args) {
		// Tell server to look for accounts-service.properties (or
		// accounts-service.yml).
		System.setProperty("spring.config.name", "accounts-microservice");

		SpringApplication.run(AccountsMicroservice.class, args);
	}

	/**
	 * This service needs access to the accounts database.
	 * 
	 * @return An Account manager that uses JPA to access Account data.
	 */
	@Bean
	public AccountManager accountManager() {
		return new JpaAccountManager();
	}
	
	
	// The account REST controller is component-scanned.
	
	
	@Bean
	public HomeController homeController() {
		return new HomeController();
	}

}
