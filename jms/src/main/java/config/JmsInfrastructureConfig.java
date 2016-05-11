package config;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

@Configuration
@EnableJms
public class JmsInfrastructureConfig {

	//	TODO-01: Define an ActiveMQConnectionFactory.
	//	Use brokerURL "vm://embedded?broker.persistent=false" 
	
	@Bean
	public ConnectionFactory connectionFactory() {
		ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
		cf.setBrokerURL("vm://embedded?broker.persistent=false");
		return cf;
	}	

	//	TODO-02: Create two ActiveMQQueue beans, one for dining and one for confirmations.
	//	Use constructor injection to provide a unique name for each queue, use any names you like. 
	//	Remember the queue names you select, you will need them later. -->
	
	@Bean
	public Destination diningQueue() {
		return new ActiveMQQueue( "dining.queue" );
	}

	@Bean
	public Destination confirmationQueue() {
		return new ActiveMQQueue( "confirmation.queue" );
	}	
	
	//	TODO-08: Create a JMS Listener Container Factory bean to support the @JmsListener annotations.
	//	The DefaultJmsListenerContainerFactory is a good class to instantiate for this.
	//	Inject its connection factory to the ActiveMQConnectionFactory bean you defined in an earlier step.
 
	@Bean
	public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
		DefaultJmsListenerContainerFactory result = new DefaultJmsListenerContainerFactory();
		result.setConnectionFactory(connectionFactory());
		return result;
	}
	
	//	TODO-09: Mark this class with the annotation needed to enable asynchronous JMS processing.
}
