package blog.elmland.cdiexamples.cdi2.bootapi;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Inject
    private EventSourceBean eventSource;

    private long id = 0;

    public void execute() {
        logger.info("[{}] execute", id);
        this.eventSource.doSomeStuff();
    }

    @PostConstruct
    public void init() {
        this.id = System.currentTimeMillis();
    }

    @PreDestroy
    public void dinit() {
        logger.info("Application preDestroy executed");
    }
}
