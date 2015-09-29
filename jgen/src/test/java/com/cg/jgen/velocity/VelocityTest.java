package com.cg.jgen.velocity;

import com.cg.jgen.core.generator.VelocityAdapter;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


/**
 *
 */
public class VelocityTest {
    private static final String TEMPLATE_NAME = "testing/velocityExample.vm";

    @Test
    public void shouldInjectValues() {
        String expected =
                "Peter\n" +
                        "peter\n" +
                        "a description\n" +
                        "\n" +
                        "n1-d1\n" +
                        "\n" +
                        "    n2-d2\n" +
                        "    n3-d3\n" +
                        "    n4-d4\n";
        expected = expected.replace("\n", System.lineSeparator());

        VelocityAdapter velocity = new VelocityAdapter();
        // injecting simple labels
        velocity.put("Name", "Peter");
        velocity.put("name", "peter");
        velocity.put("Description", "a description");

        // injecting an object
        InjectableObject iObj = new InjectableObject("n1", "d1");
        velocity.put("obj", iObj);

        // injecting a list objects
        List<InjectableObject> list = new ArrayList<>();
        list.add(new InjectableObject("n2", "d2"));
        list.add(new InjectableObject("n3", "d3"));
        list.add(new InjectableObject("n4", "d4"));
        velocity.put("objects", list);

        String data = velocity.render(TEMPLATE_NAME);
        Assert.assertEquals(data, expected);
    }
}
