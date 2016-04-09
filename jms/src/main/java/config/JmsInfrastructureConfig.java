package config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class JmsInfrastructureConfig {

	//	TODO-01: Define an ActiveMQConnectionFactory.
	//	Use brokerURL "vm://embedded?broker.persistent=false" 

	//	TODO-02: Create two ActiveMQQueue beans, one for dining and one for confirmations.
	//	Use constructor injection to provide a unique name for each queue, use any names you like. 
	//	Remember the queue names you select, you will need them later. -->
	
	
	
	//	TODO-08: Create a JMS Listener Container Factory bean to support the @JmsListener annotations.
	//	The DefaultJmsListenerContainerFactory is a good class to instantiate for this.
	//	Inject its connection factory to the ActiveMQConnectionFactory bean you defined in an earlier step.

	//	TODO-09: Mark this class with the annotation needed to enable asynchronous JMS processing.
}
