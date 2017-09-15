package blog.elmland.cdiexamples.cdi2.bootapi;

import javax.enterprise.event.Observes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventObserverBean {
	private static final Logger logger = LoggerFactory.getLogger(EventObserverBean.class);

	public void processEvent(@Observes String _event) {
		logger.info("Process event '{}'", _event);
	}
}
