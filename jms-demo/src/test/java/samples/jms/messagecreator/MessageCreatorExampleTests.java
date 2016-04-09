/*
 * Copyright 2002-2006 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package samples.jms.messagecreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;

import samples.jms.AbstractMessagingTests;

/**
 * A simple JUnit Integration example using the {@link MessageCreator} callback.
 */
@ContextConfiguration(locations="application-context.xml")
public class MessageCreatorExampleTests extends AbstractMessagingTests {

	@Autowired
	private JmsTemplate jmsTemplate;

	@Test
	public void testCreateMessage() {
		jmsTemplate.send(new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				TextMessage msg = session.createTextMessage("Hello JMS!");
				msg.setJMSCorrelationID("Test-1");
				return msg;
			}
		});
	}
}
