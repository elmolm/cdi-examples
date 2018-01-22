package blog.elmland.cdiexamples.cdi2.dynamicfactory;

import java.util.Scanner;

import javax.inject.Inject;

import org.slf4j.Logger;

import blog.elmland.cdiexamples.cdi2.dynamicfactory.bird.Bird;
import blog.elmland.cdiexamples.cdi2.dynamicfactory.bird.BirdFactory;

public class ConsoleClient {
	@Inject
	private Logger logger;

	@Inject
	private BirdFactory birdFactory;

	public void doSomeStuff() {
		try (Scanner scanner = new Scanner(System.in)) {

			logger.info("Which bird should tweet?");

			while (scanner.hasNextLine()) {
				String birdType = scanner.nextLine();

				Bird bird = birdFactory.createBird(birdType);
				bird.tweet();
			}
		}

	}
}
