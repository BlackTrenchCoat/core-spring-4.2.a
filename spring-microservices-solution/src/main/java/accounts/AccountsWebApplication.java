package accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

/**
 * Web front-end for the microservices application.
 * <p>
 * Start this process last.
 */
@SpringBootApplication
@EnableDiscoveryClient
// Ignore httpMapperProperties deprecated warning
public class AccountsWebApplication {

	// In accounts-microservice.properties, spring.application.name is set to
	// accounts-microservice. The service registers under this name, so we ask
	// Eureka for the service with that name. Upper case is used by convention
	// (to imply it is not a REAL hostname but a microservice name), but upper
	// or lower case both work.
	public static final String ACCOUNTS_SERVICE_URL = "http://ACCOUNTS-MICROSERVICE";

	public static void main(String[] args) {
		SpringApplication.run(AccountsWebApplication.class, args);
	}

	@Bean
	public AccountManager accountManager() {
		return new RemoteAccountManager(ACCOUNTS_SERVICE_URL);
	}
}
