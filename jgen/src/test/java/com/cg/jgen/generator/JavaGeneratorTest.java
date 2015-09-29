package com.cg.jgen.generator;

import com.cg.jgen.DataTestProvider;
import com.cg.jgen.core.Config;
import com.cg.jgen.core.Entity;
import com.cg.jgen.core.FileTemplate;
import com.cg.jgen.core.generator.Generator;
import com.cg.jgen.core.generator.JavaGenerator;

/**
 *
 */
public class JavaGeneratorTest extends BaseGeneratorTest {

    @Override
    public void injectParameters() {
        // given
        Config config = new Config();
        config.setRootPackage("a");
        Generator gen = new JavaGenerator(config);

        FileTemplate ft = DataTestProvider.getFileTemplate("JavaGeneratorTemplate");

        Entity ett = DataTestProvider.getEntityPersonTest();

        String expected =
                "// Common Labels" + "\n" +
                        "// header" + "\n" +
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
                        "utils:" + "\n" +
                        "Big" + "\n" +
                        "small" + "\n" +
                        "lower" + "\n" +
                        "UPPER" + "\n" +
                        "" + "\n" +
                        "// Java Labels" + "\n" +
                        "package a.b;" + "\n" +
                        "" + "\n" +
                        "import a.c.ADependencyClass;" + "\n" +
                        "" + "\n" +
                        "/** A Person object Test */" + "\n" +
                        "@DatabaseTable(" + "\n" +
                        "    tableName = \"PersonTest\"," + "\n" +
                        "    primaryKey = @PrimaryKey(name = \"PK_PERSON_TEST\", elementNames = {\"id\"}, strategy = PrimaryKeyGenerator.GENERATED_BY_DB)," + "\n" +
                        "    indexes = {" + "\n" +
                        "        @TableIndex(name = \"IX_PERSON_TEST\", unique = true, elementNames = {\"firstName\"})" + "\n" +
                        "    }" + "\n" +
                        ")";

        setParameters(gen, ft, ett, expected);
    }
}
