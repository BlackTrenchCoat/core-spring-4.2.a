package notification;

import infoassembler.InfoAssemblerDemo;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Demonstrates {@link MessagePublisher} using JMX notifications to send
 * messages to {@link MessageReceiver}. You need to run a JMX (for example in
 * JConsole).
 * <p>
 * Uses a simple XML configuration that exposes methods as operations using
 * interfaces ({@link MessagePublisherManagement} and respectively
 * {@link MessageReceiverManagement} - the same technique as used by
 * {@link InfoAssemblerDemo}.
 * <p>
 * DEMO: Run the application and then connect to it using JConsole. The two
 * "springsource" beans should be appear at the bottom of the list on the MBeans
 * panel.
 * <ul>
 * <li>For each bean, select the Notifications panel and click "Subscribe".
 * <li>Select the "messagePublisher" bean's Operation panel and send a message.
 * <li>The message will appear (1) in its Notifications panel and (2) on this
 * application's console.
 * <li>In the "messageReceiver" bean's Attributes panel, you should see that the
 * MessageCount is now 1.
 * </ul
 */
@Configuration
@ImportResource("notification/notification-config.xml")
public class NotificationDemo {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(NotificationDemo.class);
		System.out.println(" >> Connect with JConsole and look for "
				+ "the service MBean - play with its operations");
		System.out
				.println(" >> Subscribe to notifications in both MBeans then send a message.");
		System.out.println(" >> Ready.  Press Enter to Stop...");
		System.in.read();
	}

}
