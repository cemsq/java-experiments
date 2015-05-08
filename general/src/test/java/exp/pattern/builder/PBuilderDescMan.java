package exp.pattern.builder;

/**
 *
 */
public class PBuilderDescMan extends Product.Builder{
    @Override
    public Product build() {
        Product p = super.build();

        checkString(p.getDesc(), "Description not defined");

        return p;
    }
}
