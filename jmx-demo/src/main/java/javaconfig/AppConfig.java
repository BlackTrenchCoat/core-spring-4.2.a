package javaconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;

@Configuration
@EnableMBeanExport
public class AppConfig {

	@Bean
	public MessageService messageService() {
		return new MessageService();
	}

}
