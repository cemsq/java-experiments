package com.cg.jgen.generator;

import com.cg.jgen.core.Entity;
import com.cg.jgen.core.FileTemplate;
import com.cg.jgen.core.generator.Generator;
import com.cgm.storm.utils.common.StringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 */
public abstract class BaseGeneratorTest {

    private Generator gen;
    private FileTemplate ft;
    private Entity entity;
    private String expected;

    @BeforeClass
    public void init() {
        injectParameters();
    }

    @Test(dependsOnMethods = "checkParameters")
    public void shouldInjectLabels() {
        // given
        //checkParameters();

        // when
        String data = gen.render(ft, entity);

        // then
        expected = StringUtils.replaceLineSeparator(expected);
        Assert.assertEquals(data, expected, "String rendered not match.");
    }

    @Test
    public void checkParameters() {
        String message = "not defined. Please call setParameters() in the overridden injectParameters() method. ";

        Assert.assertNotNull(gen, "Generator " + message);
        Assert.assertNotNull(ft, "FileTemplate " + message);
        Assert.assertNotNull(entity, "Entity " + message);
        Assert.assertNotNull(expected, "expected String " + message);
    }

    /**
     * Inject the parameters needed for {@link #shouldInjectLabels()} test.
     * Use the {@link #setParameters(com.cg.jgen.core.generator.Generator, com.cg.jgen.core.FileTemplate, com.cg.jgen.core.Entity, String)} method to inject properly the parameters.
     */
    public abstract void injectParameters();

    public void setParameters(Generator gen, FileTemplate ft, Entity entity, String expected) {
        this.gen = gen;
        this.ft = ft;
        this.entity = entity;
        this.expected = expected;
    }

}
