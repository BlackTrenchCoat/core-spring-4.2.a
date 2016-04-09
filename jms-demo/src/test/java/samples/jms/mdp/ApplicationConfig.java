package samples.jms.mdp;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

@EnableJms
@Configuration
public class ApplicationConfig {

	public static final String IN_MEMORY_BROKER_URL = "vm://embedded?broker.persistent=false";

	@Bean
	public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory,
			Queue loggingQueue) {
		JmsTemplate template = new JmsTemplate(connectionFactory);
		template.setDefaultDestination(loggingQueue);
		return template;
	}

	@Bean
	public ConnectionFactory connectionFactory() {
		return new ActiveMQConnectionFactory(IN_MEMORY_BROKER_URL);
	}

	@Bean
	public Queue loggingQueue() {
		return new ActiveMQQueue("logging.queue");
	}

	@Bean
	public SimpleLogger logger() {
		return new SimpleLogger();
	}

	/**
	 * Create a Factory for creating JMS Listener Containers. Spring will use
	 * this whenever it needs to create an asynchronous JMS Listener Container
	 * to support one of your @JmsListener methods:
	 */
	@Bean
	public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(
			ConnectionFactory connectionFactory) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory);
		factory.setConcurrency("5");
		return factory;
	}

}
