package rewards.jms.client;

import java.util.List;

import org.springframework.jms.core.JmsTemplate;

import rewards.Dining;

/**
 * A batch processor that sends dining event notifications via JMS.
 */
public class JmsDiningBatchProcessor implements DiningBatchProcessor {

	// TODO-03: Provide a JmsTemplate field.  
	//	Add a setter or constructor to allow it to be set via dependency injection.
	
	private JmsTemplate jmsTemplate;
	
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	//	TODO-04: Loop through each Dining instance, 
	//	sending each one using the JmsTemplate.

	public void processBatch(List<Dining> batch) {
		for (Dining dining : batch) {
			jmsTemplate.convertAndSend(dining);	
		}
	}
}
