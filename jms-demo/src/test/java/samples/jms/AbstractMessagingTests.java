/*
 * Copyright 2002-2006 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package samples.jms;

import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * An abstract base-class used by all our tests.
 * <p>
 * It defines a JUnit <code>@BeforeClass</code> method that turns off most
 * logging which otherwise obscures the output from the tests.
 * <p>
 * Also defines an <code>@AfterClass</code> method that explicitly shuts down
 * ActiveMQ.
 * <p>
 * Spring has registered a shutdown hook to do shut down ActiveMQ automatically,
 * but it generates an ugly exception. Doing it explicitly avoids that.
 * <p>
 * JUnit mandates that <code>@AfterClass</code> methods (like
 * {@link #shutdown()}) must be static but Spring does not support
 * <code>@Autowire</code> on static data-members. We use a simple workaround.
 * <p>
 * This class implements {@link ApplicationContextAware}. This means that a
 * <code>setApplicationContext</code> must be defined. Spring invokes this
 * method during application startup passing in the newly created
 * ApplicationContext. We then save that to a static data-member for use by the
 * {@link #shutdown()} method later.
 */
// Run tests in name order
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public abstract class AbstractMessagingTests implements ApplicationContextAware {

	/**
	 * Save the application context. This must be a static so shutdown() can use
	 * it.
	 */
	protected static ApplicationContext applicationContext;

	/**
	 * Disable excessive logging so the logging due to the application is more
	 * easily read. Method runs before all tests. Unfortunately there remains
	 * some logging at startup before this method is invoked.
	 */
	@BeforeClass
	public static void supressExcessLogging() {
		LoggerFactory.getLogger(AbstractMessagingTests.class) //
				.info("Tests starting ...");
	}

	/**
	 * Shutdown gracefully at the end of the test to avoid an ugly exception.
	 * Doing it explicitly avoids that. This method automatically runs after all
	 * tests and must be static.
	 */
	@AfterClass
	public static void shutdown() {

		Map<String, DefaultMessageListenerContainer> ctrs = applicationContext
				.getBeansOfType(DefaultMessageListenerContainer.class);

		LoggerFactory.getLogger(AbstractMessagingTests.class).info(
				"Shutting down " + ctrs.size()
						+ " message listener container(s).");

		for (DefaultMessageListenerContainer ctr : ctrs.values())
			ctr.shutdown();
	}

	/**
	 * Because this class implements {@link ApplicationContextAware}, Spring
	 * automatically calls this method passing in the ApplicationContent. We
	 * then store the context to a <i>static</i> variable. This is a workaround
	 * because you cannot @Autowire a static data-member.
	 */
	@Override
	public void setApplicationContext(ApplicationContext appContext)
			throws BeansException {
		// Save to the static data-member for use later by the @AfterClass
		// method
		applicationContext = appContext;
	}

}
