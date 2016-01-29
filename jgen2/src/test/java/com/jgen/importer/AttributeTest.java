package com.jgen.importer;

import com.jgen.model.Attribute;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 */
public class AttributeTest {

    @Test
    public void shouldBeCapitalized() {
        Attribute att = new Attribute("name", "Id");

        Assert.assertEquals(att.getValue(), "id");
    }
}
