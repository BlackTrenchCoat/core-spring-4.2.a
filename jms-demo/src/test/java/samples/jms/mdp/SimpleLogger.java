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

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.jms.annotation.JmsListener;

/**
 * A simple logger to be used as a Message Driven POJO.
 */
public class SimpleLogger {

	private final Logger logger = Logger.getLogger(getClass());

	@JmsListener(destination = "logging.queue")
	public void log(Object obj) {
		if (obj instanceof Money)
			log((Money) obj);
		else if (obj instanceof byte[])
			log((byte[]) obj);
		else if (obj instanceof Map<?, ?>)
			log((Map<?, ?>) obj);
		else
			log(obj.toString());
	}

	public void log(String s) {
		show("", s);
	}

	public void log(byte[] bytes) {
		StringBuffer sb = new StringBuffer();
		sb.append(">> Received a byte array: [");
		for (int i = 0; i < bytes.length; i++) {
			sb.append(bytes[i]);
			if (i != (bytes.length - 1)) {
				sb.append(',');
			}
		}
		sb.append("]");
		show("byte[]", sb);
	}

	public void log(Map<?, ?> map) {
		show("Map", map);
	}

	public void log(Money money) {
		show("Money", money);
	}

	public void show(String type, Object data) {
		// Make the message stand out amongst all the logging
		logger.info(">> Received " + type + ": " + data);
	}
}
