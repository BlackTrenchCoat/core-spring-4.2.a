package samples.jmx.connector;

import java.io.IOException;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import samples.jmx.basic.BasicDemo;

/**
 * Exports same beans as {@link BasicDemo} but exports them via a JSR-160
 * connector.
 * <p>
 * DEMO: Run application and connect as a 'Local Process' in JConsole.
 * Connection succeeds, but no message-service MBean appears. Close the
 * connection. Try connecting as a 'Remote Process' using the URL in
 * 'connector-demo.xml'. This time the message-service MBean can be seen.
 */
@Configuration
@ImportResource({ "samples/jmx/connector/connector-demo.xml",
		"samples/jmx/basic/basic-demo.xml" })
public class ConnectorDemo {

	public static void main(String[] args) throws IOException {
		ConfigurableApplicationContext ctx = SpringApplication
				.run(ConnectorDemo.class);
		System.out.println(" >> Connect as 'Local Process' with JConsole "
				+ "- notice no message-service MBean");
		System.out.println(" >> Connect as 'Remote Process' "
				+ "using URL in 'connector-demo.xml'"
				+ " - now the message-service MBean appears");
		System.out.print(" >> Ready.  Press Enter to Stop...");
		System.in.read();

		// Clean up
		try {
			if (ctx instanceof DisposableBean) // it should be
				((DisposableBean) ctx).destroy();
		} catch (Exception e) {
			System.out.println("Failed to destroy: " + e);
		}
	}
}
