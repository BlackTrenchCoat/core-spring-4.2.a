package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * TODO-07: Add the annotation to cause component scanning.
 * Set the base package to pick up all of the classes we have annotated so far.
 * Save all changes, Re-run the RewardNetworkTests.  It should now pass.
 */
@Configuration
@ComponentScan("rewards")
public class RewardsConfig {

	//	TODO-02: Remove all of the @Bean methods above.  Remove the @Autowired field.
    //	Try executing the RewardNetworkTests test class. It should fail. Why?
	
}
