/*******************************************************************************
 * MIT License
 *
 * Copyright (c) 2018 Florian Schmidt / https://elmland.blog
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *******************************************************************************/
package blog.elmland.cdiexamples.cdi2.bootapi;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import javax.enterprise.inject.spi.CDI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		bootstrapContainer1();

		bootstrapContainer2();
	}

	public static void bootstrapContainer1() {
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
	}

	public static void bootstrapContainer2() {
		SeContainerInitializer initializer = SeContainerInitializer.newInstance();

		try (SeContainer cdiContainer = initializer.disableDiscovery().addBeanClasses(Application.class)
				.addBeanClasses(EventObserverBean.class).addBeanClasses(EventSourceBean.class).initialize()) {

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
	}
}
