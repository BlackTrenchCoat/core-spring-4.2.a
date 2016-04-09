package jsr160;

import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.jmx.support.ConnectorServerFactoryBean;
import org.springframework.jmx.support.MBeanServerFactoryBean;
import org.springframework.remoting.rmi.RmiRegistryFactoryBean;

@Configuration
@EnableMBeanExport
public class Jsr160AppConfig {

	@Autowired
	ApplicationContext context;

	@Bean
	public MessageService messageService() {
		return new MessageService();
	}

	@Bean
	public RmiRegistryFactoryBean rmiRegistryFactoryBean() {
		RmiRegistryFactoryBean registry = new RmiRegistryFactoryBean();
		registry.setPort(1099);
		return registry;
	}

	/**
	 * Need to create the MBeanServer explicitly so the
	 * ConnectorServerFactoryBean can connect to it during initialisation.
	 * 
	 * @return An MbeanServer.
	 */
	@Bean
	public MBeanServer mBeanServerFactoryBean() {
		MBeanServerFactoryBean msfb = new MBeanServerFactoryBean();
		msfb.setLocateExistingServerIfPossible(true);
		msfb.afterPropertiesSet();
		return msfb.getObject();
	}

	@Bean
	public ConnectorServerFactoryBean connectorServerFactoryBean(
			MBeanServer mBeanServer) throws MalformedObjectNameException {
		ConnectorServerFactoryBean csf = new ConnectorServerFactoryBean();
		csf.setObjectName("connector:name=rmi");
		csf.setServiceUrl("service:jmx:rmi://localhost/jndi/rmi://localhost:1099/jmxconnector");
		csf.setServer(mBeanServer);
		return csf;
	}

}
