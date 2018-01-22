package blog.elmland.cdiexamples.cdi2.bootapi;

import java.util.Random;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventSourceBean {
    private static final Logger logger = LoggerFactory.getLogger(EventSourceBean.class);

    private long id = 0;

    @Inject
    private Event<String> stringEvent;

    public void doSomeStuff() {
        String eventPayload = "I'm a test event - " + new Random().nextInt();

        logger.info("[{}] Fire event '{}'", id, eventPayload);
        this.stringEvent.fire(eventPayload);
    }

    @PostConstruct
    public void init() {
        this.id = System.currentTimeMillis();
    }

    @PreDestroy
    public void dinit() {
        logger.info("EventSourceBean preDestroy executed");
    }
}
