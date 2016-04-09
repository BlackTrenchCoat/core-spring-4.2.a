package javaconfig;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;

/**
 * Annotated as an MBean, only annotated methods exposed to JMX
 */
@ManagedResource
public class MessageService {

	private String message = "Hello World!";

	@ManagedAttribute
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@ManagedOperation
	public String reverseMessage() {
		char[] chars = message.toCharArray();

		StringBuilder reversed = new StringBuilder();
		for (int i = chars.length; i > 0; i--) {
			reversed.append(chars[i - 1]);
		}

		return reversed.toString();
	}

	/**
	 * This method is not on {@link MessageCapable} and does not appear in
	 * JConsole as an operation.
	 * 
	 * @return
	 */
	public String invisibleMethod() {
		return "You can access this in JConsole";
	}
}
