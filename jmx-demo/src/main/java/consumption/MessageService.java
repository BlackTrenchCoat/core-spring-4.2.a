package consumption;

/**
 * Holds a message that can be fetched normally or fetched reversed. A new message
 * can be set at any time.
 */
public class MessageService {

	private String message = "Hello World!";

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String reverseMessage() {
		char[] chars = message.toCharArray();

		StringBuilder reversed = new StringBuilder();
		for (int i = chars.length; i > 0; i--) {
			reversed.append(chars[i - 1]);
		}

		return reversed.toString();
	}
}
