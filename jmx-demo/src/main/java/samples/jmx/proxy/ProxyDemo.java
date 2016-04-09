package samples.jmx.proxy;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("samples/jmx/proxy/proxy-demo.xml")
public class ProxyDemo {

	public static void main(String[] args) throws IOException {
		ApplicationContext ctx = SpringApplication.run(ProxyDemo.class);
		RuntimeInfo ri = ctx.getBean("runtime", RuntimeInfo.class);

		System.out.println("Boot Class Path: " + ri.getBootClassPath());
		System.out.println("Class Path: " + ri.getClassPath());
		System.out.println("VM Name: " + ri.getVmName());
		System.out.println("VM Vendor: " + ri.getVmVendor());
	}
}
