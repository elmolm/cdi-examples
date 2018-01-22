package blog.elmland.cdiexamples.cdi2.dynamicfactory.bird;

import javax.inject.Inject;

import org.slf4j.Logger;

@BirdType("S")
public class Sparrow implements Bird {
	@Inject
	private Logger logger;

    @Override
    public void tweet() {
        logger.info("Tweet, Tweet, I'm a sparrow!");
    }

}
