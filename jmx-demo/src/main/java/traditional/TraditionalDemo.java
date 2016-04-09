package traditional;

import java.io.IOException;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

import declarative.DeclarativeDemo;
import declarative.MessageService;

/**
 * Demonstrates access to the {@link MessageService} using JMX (for example in
 * JConsole). This uses JMX API to export the message-service instead of using
 * Spring (compare to the equivalent {@link DeclarativeDemo}.
 * <p>
 * Expose all public methods and treats anything with a getter or setter as an
 * attribute. In practice you would not normally want to allow this much access
 * to JMX.
 * <p>
 * DEMO: Run the application and then connect to it using JConsole. The service
 * bean should be appear at the bottom of the list on the MBeans panel. Try
 * playing with its operations.
 */
public class TraditionalDemo {

	public static void main(String[] args)
			throws InstanceAlreadyExistsException, MBeanRegistrationException,
			NotCompliantMBeanException, MalformedObjectNameException,
			IOException {
		new TraditionalDemo().registerMessageService();
		System.out.println(" >> Connect with JConsole and look for "
				+ "the service MBean - play with its operations");
		System.out.print(" >> Ready.  Press Enter to Stop...");
		System.in.read();
	}

	private void registerMessageService() throws MalformedObjectNameException,
			InstanceAlreadyExistsException, MBeanRegistrationException,
			NotCompliantMBeanException {
		MessageService messageService = new MessageService();
		ObjectName objectName = new ObjectName("service:name=messageService");
		MBeanServer mBeanServer = MBeanServerFactory.findMBeanServer(null).get(
				0);
		mBeanServer.registerMBean(messageService, objectName);
	}

}
