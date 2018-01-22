package blog.elmland.cdiexamples.cdi2.dynamicfactory;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

public class Main {
    public static void main(String[] args) {
        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            ConsoleClient client = container.select(ConsoleClient.class).get();
            client.doSomeStuff();
        }
    }
}
