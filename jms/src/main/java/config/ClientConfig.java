package config;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

import rewards.jms.client.DiningBatchProcessor;
import rewards.jms.client.JmsDiningBatchProcessor;
import rewards.jms.client.RewardConfirmationLogger;

@Configuration
public class ClientConfig {

	@Autowired ConnectionFactory connectionFactory;
	
	@Bean
	@Autowired
	public DiningBatchProcessor diningBatchProcessor(JmsTemplate jmsTemplate) {
		JmsDiningBatchProcessor processor = new JmsDiningBatchProcessor();
		return processor;
	}

	//	TODO-05: Define a JmsTemplate.
	//	Provide it with a reference to the ConnectionFactory and the dining destination. 
	//	Inject it into the batch processor bean (above). 
	
	@Autowired
	Destination diningQueue;
	
	@Bean 
	public JmsTemplate jmsTemplate() {
		JmsTemplate result = new JmsTemplate(connectionFactory);
		result.setDefaultDestination(diningQueue);
		return result;
	}
	
	/**
	 *	Create an object that knows how to log dining confirmations: 
	 */
	@Bean
	public RewardConfirmationLogger logger() {
		return new RewardConfirmationLogger();
	}
	
}
