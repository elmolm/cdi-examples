package blog.elmland.cdiexamples.cdi2.dynamicfactory.bird;

import javax.enterprise.util.AnnotationLiteral;

public class BirdTypeNameLiteral extends AnnotationLiteral<BirdType> implements BirdType {
    private static final long serialVersionUID = -4678199166362200239L;

    private final String name;

    public BirdTypeNameLiteral(String _name) {
        this.name = _name;
    }

    @Override
    public String value() {
        return this.name;
    }

}