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

	/**
	 * Create a ConnectionFactory using ActiveMQ:
	 */
	@Bean
	public ConnectionFactory connectionFactory(){
		return new ActiveMQConnectionFactory("vm://embedded?broker.persistent=false");
	}
	
	
	/**
	 *	Create a Queue for Dining objects using ActiveMQ: 
	 */
	@Bean
	public Destination diningQueue() {
		return new ActiveMQQueue("rewards.queue.dining");
	}
	
	
	/**
	 *	Create a Queue for Confirmation objects using ActiveMQ: 
	 */
	@Bean
	public Destination confirmationQueue() {
		return new ActiveMQQueue("rewards.queue.confirmation");
	}
	
	/**
	 *	Create a Factory for creating JMS Listener Containers.  Spring will use
	 *	this whenever it needs to create an asynchronous JMS Listener Container 
	 *	to support one of your @JmsListener methods: 
	 */
    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory =
                new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        return factory;
    }		
		
}
