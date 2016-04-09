package samples.jmx.runtime;

import javax.management.ObjectName;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jmx.export.MBeanExportOperations;

/**
 * Uses P{2link MBeanExportOperations} to register MBeans programatically.
 */
@Configuration
@ImportResource("samples/jmx/runtime/runtime-demo.xml")
public class RuntimeDemo {

	public static void main(String[] args) throws Exception {
		ApplicationContext ctx = SpringApplication.run(RuntimeDemo.class);
		System.out.println("Started....");

		// grab the MBeanExportOperations - allows runtime registration
		MBeanExportOperations operations = ctx.getBean("exporter",
				MBeanExportOperations.class);

		// Register beans manually - specifying the name
		for (int x = 1; x <= 5; x++) {
			System.out.println("Register bean: " + x);
			MessageService messageService = new MessageService();
			messageService.setMessage("Message: " + x);
			ObjectName objectName = new ObjectName(
					"springsource:service=messageService" + x);
			operations.registerManagedResource(messageService, objectName);
			System.out.println("Generated name: " + objectName);
		}

		// Register beans manually - using auto-generated names, derived from
		// the @ManagedResource name property (see MessageService).
		for (int x = 1; x <= 5; x++) {
			System.out.println("Register bean: " + x);
			MessageService messageService = new MessageService();
			messageService.setMessage("Message: " + x);
			ObjectName generatedName = operations
					.registerManagedResource(messageService);
			System.out.println("Generated name: " + generatedName);
		}

		System.out .println(" >> Connect with JConsole and look for "
			+ "the springsource MBeans - open up bingo to see nested MBeans");
		System.out.print(" >> Ready.  Press Enter to Stop...");
		System.in.read();
	}
}
