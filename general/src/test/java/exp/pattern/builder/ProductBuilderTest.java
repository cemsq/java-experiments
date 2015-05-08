package exp.pattern.builder;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 */
public class ProductBuilderTest {

    @Test (expectedExceptions = IllegalArgumentException.class)
    public void shouldFail_using_Builder() {
        try {
            Product p = new Product.Builder()
                    .id("1")
                    .build();

            Assert.assertEquals(p.getId(), "1");
            Assert.assertEquals(p.getName(), "abc");
            Assert.assertEquals(p.getDesc(), "");
            Assert.assertEquals(p.getNum(), new Integer(0));

            System.out.println(p);
        }catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @Test
    public void shouldPass_using_Builder() {
        Product p = new Product.Builder()
                .id("1")
                .name("abc")
                .build();

        Assert.assertEquals(p.getId(), "1");
        Assert.assertEquals(p.getName(), "abc");
        Assert.assertEquals(p.getDesc(), "");
        Assert.assertEquals(p.getNum(), new Integer(0));

        System.out.println(p);
    }

    @Test (expectedExceptions = IllegalArgumentException.class)
    public void shouldFail_using_PBuilderDescMan() {
        Product p = new PBuilderDescMan()
                .id("1")
                .name("abc")
                .build();
    }

    @Test
    public void shouldPass_using_PBuilderDescMan() {
        Product p = new PBuilderDescMan()
                .id("1")
                .name("abc")
                .desc("a description")
                .build();

        Assert.assertEquals(p.getId(), "1");
        Assert.assertEquals(p.getName(), "abc");
        Assert.assertEquals(p.getDesc(), "a description");
        Assert.assertEquals(p.getNum(), new Integer(0));
    }
}
