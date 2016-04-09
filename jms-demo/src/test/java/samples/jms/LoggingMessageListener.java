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

package samples.jms;

import java.util.Date;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;

/**
 * A simple implementation of {@link MessageListener} that logs the text of any
 * received {@link TextMessage}.
 */
public class LoggingMessageListener implements MessageListener {

	private final Logger logger = Logger.getLogger(getClass());

	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			try {
				String id = message.getJMSCorrelationID();
				Date ts = new Date(message.getJMSTimestamp());
				String text = ((TextMessage) message).getText();
				// Make the message stand out amongst all the logging
				logger.info(">> [" + ts + "]: Received: " + text + " (" + id
						+ ")");
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}
}
