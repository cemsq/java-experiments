package com.cg.jgen.generator;

import com.cg.jgen.DataTestProvider;
import com.cg.jgen.core.Config;
import com.cg.jgen.core.FileTemplate;
import com.cg.jgen.core.generator.Generator;

/**
 *
 */
public class GeneratorTest extends BaseGeneratorTest {

    @Override
    public void injectParameters() {
        // given
        Config config = new Config();

        Generator gen = new Generator(config);

        FileTemplate ft = DataTestProvider.getFileTemplate("GeneratorTestTemplate");

        String expected =
                "PersonTest" + "\n" +
                        "personTest" + "\n" +
                        "    @Element(length = 18)" + "\n" +
                        "    private String id;" + "\n" +
                        "" + "\n" +
                        "    @Element(length = 25)" + "\n" +
                        "    private String firstName;" + "\n" +
                        "" + "\n" +
                        "    @Element(length = 25)" + "\n" +
                        "    private String lastName;" + "\n" +
                        "" + "\n" +
                        "A Person object Test" + "\n" +
                        "// header" + "\n" +
                        "Big" + "\n" +
                        "small" + "\n" +
                        "lower" + "\n" +
                        "UPPER";

        setParameters(gen, ft, DataTestProvider.getEntityPersonTest(), expected);
    }
}
