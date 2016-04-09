package samples.jmx.runtime;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;

/**
 * @author Rick Evans
 */
@ManagedResource(description = "A Simple Message Service", objectName = "springsource:service=bingo")
public class MessageService {

	private String message = "Hello World!";

	@ManagedAttribute(description = "Gets the message")
	public String getMessage() {
		return message;
	}

	@ManagedAttribute(description = "Sets the message")
	public void setMessage(String message) {
		this.message = message;
	}

	@ManagedOperation(description = "Reverse the operation")
	public String reverseMessage() {
		char[] chars = message.toCharArray();

		StringBuilder reversed = new StringBuilder();
		for (int i = chars.length; i > 0; i--) {
			reversed.append(chars[i - 1]);
		}

		return reversed.toString();
	}

	public String upperCaseMessage() {
		return this.message.toUpperCase();
	}
}
