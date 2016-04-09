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

package samples.jms.mdp;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;

import samples.jms.AbstractMessagingTests;

/**
 * This shows how Java Configuration is not always better than XML. Only one @JmsListener
 * annotation per queue is allowed for any given message-driven POJO. So we have
 * annotated a generic log(Object) method in {@link SimpleLogger}. When you run
 * this test you will see that it accepts the raw JMS Message, the payload is
 * not extracted. Compare this to {@link MessageDrivenPojoXmlTests}.
 */
@ContextConfiguration(classes = { ApplicationConfig.class })
public class MessageDrivenPojoTests extends AbstractMessagingTests {

	@Autowired
	private JmsTemplate jmsTemplate;

	@Test
	public void test1SendingString() {
		jmsTemplate.convertAndSend("Hello POJO!");
	}

	@Test
	public void test2SendingBytes() {
		byte[] bytes = new byte[] { 1, 2, 3 };
		jmsTemplate.convertAndSend(bytes);
	}

	@Test
	public void test3SendingMap() throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("firstName", "Keith");
		map.put("lastName", "Donald");
		jmsTemplate.convertAndSend(map);
	}

	@Test
	public void test4SendingMoney() {
		Money money = new Money(100.00, "USD");
		jmsTemplate.convertAndSend(money);
	}

}
