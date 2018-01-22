package blog.elmland.cdiexamples.cdi2.dynamicfactory.bird;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

@ApplicationScoped
public class BirdFactory {

    @Inject
    @Any
    private Instance<Bird> birdInstance;

    public Bird createBird(String _birdType) {
        Instance<Bird> instance = this.birdInstance.select(new BirdTypeNameLiteral(_birdType));

        if (!instance.isResolvable()) {
            throw new IllegalArgumentException("bird type " + _birdType + " not supported");
        }

        return instance.get();
    }
}
