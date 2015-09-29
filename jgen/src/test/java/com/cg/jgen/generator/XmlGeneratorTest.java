package com.cg.jgen.generator;

import com.cg.jgen.DataTestProvider;
import com.cg.jgen.core.Config;
import com.cg.jgen.core.Entity;
import com.cg.jgen.core.FileTemplate;
import com.cg.jgen.core.generator.XmlGenerator;

/**
 *
 */
public class XmlGeneratorTest extends BaseGeneratorTest {

    @Override
    public void injectParameters() {
        Config config = new Config();
        config.setHelixNamespace("http://www.cgm.com/2011/helix/metadata");
        XmlGenerator gen = new XmlGenerator(config);

        FileTemplate ft = DataTestProvider.getFileTemplate("XmlGeneratorTemplate");

        Entity ett = DataTestProvider.getEntityPersonTest();

        String expected =
                "// Common ---" + "\n" +
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
                        "// Xml - Labels" + "\n" +
                        "PersonTest" + "\n" +
                        "http://www.cgm.com/2011/helix/metadata" + "\n" +
                        "aResourceCatalogId";

        setParameters(gen, ft, ett, expected);
    }
}
