package consumption;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import declarative.MessageService;

/**
 * Starts up JMX and make a request of the {@link MessageService} MBean
 * programmatically.
 * 
 * @author paulchapman
 *
 */
@Configuration
@ImportResource("consumption/consumption-config.xml")
public class ConsumptionDemo {

	public static void main(String[] args) throws IOException {
		ApplicationContext ctx = SpringApplication.run(ConsumptionDemo.class);
		MessageCapable mc = ctx.getBean("messageServiceClient",
				MessageCapable.class);
		System.out.println(">> " + mc.reverseMessage());
	}

}
