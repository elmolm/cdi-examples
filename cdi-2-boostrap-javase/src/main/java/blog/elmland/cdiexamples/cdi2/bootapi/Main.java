package blog.elmland.cdiexamples.cdi2.bootapi;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import javax.enterprise.inject.spi.CDI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
				
		try (SeContainer cdiContainer = SeContainerInitializer.newInstance().initialize()) {

			logger.info("Get Application 2 times, to show, that it's a real singleton");
			Application appl = cdiContainer.select(Application.class).get();
			appl.execute();

			Application appl2 = CDI.current().select(Application.class).get();
			appl2.execute();

			/**
			 * Get non singleton bean MyBean 2 times, to show, that they are different
			 * objects.
			 */
			EventSourceBean myBean1 = cdiContainer.select(EventSourceBean.class).get();
			myBean1.doSomeStuff();

			EventSourceBean myBean2 = cdiContainer.select(EventSourceBean.class).get();
			myBean2.doSomeStuff();

		}
		
		SeContainerInitializer initializer = SeContainerInitializer.newInstance();
		SeContainer container = initializer.initialize();
		
		container.close();
		

	}
}
