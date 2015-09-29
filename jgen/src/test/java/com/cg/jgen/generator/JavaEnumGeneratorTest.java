package com.cg.jgen.generator;

import com.cg.jgen.DataTestProvider;
import com.cg.jgen.core.Config;
import com.cg.jgen.core.Entity;
import com.cg.jgen.core.EnumValue;
import com.cg.jgen.core.FileTemplate;
import com.cg.jgen.core.generator.Generator;
import com.cg.jgen.core.generator.JavaEnumGenerator;

import java.util.Arrays;

/**
 *
 */
public class JavaEnumGeneratorTest extends BaseGeneratorTest {

    @Override
    public void injectParameters() {
        // given
        Config config = new Config();
        config.setRootPackage("a");
        Generator gen = new JavaEnumGenerator(config);

        FileTemplate ft = DataTestProvider.getFileTemplate("JavaEnumGeneratorTemplate");

        EnumValue ev1 = new EnumValue("0", "undefined", "not defined", 1);
        EnumValue ev2 = new EnumValue("1", "signed", "", 2);
        EnumValue ev3 = new EnumValue("2", "shipped", "items shipped", 3);

        Entity ett = DataTestProvider.getEntityPersonTest();
        ett.setValueTable(true);
        ett.setEnumValues(Arrays.asList(ev1, ev2, ev3));

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
                        "    primaryKey = @PrimaryKey(name = \"PK_PERSON_TEST\", elementNames = {\"id\"})," + "\n" +
                        "    indexes = {" + "\n" +
                        "        @TableIndex(name = \"IX_PERSON_TEST\", unique = true, elementNames = {\"firstName\"})" + "\n" +
                        "    }" + "\n" +
                        ")" + "\n" +
                        "" + "\n" +
                        "// JavaEnum Labels" + "\n" +
                        "    undefined_0 (\"0\")" + "\n" +
                        "    signed_1 (\"1\")" + "\n" +
                        "    shipped_2 (\"2\")" + "\n";

        setParameters(gen, ft, DataTestProvider.getEntityPersonTest(), expected);
    }
}
