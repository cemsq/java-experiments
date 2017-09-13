package com.cg.jgen;

import com.cg.jgen.core.EnumValue;
import com.cg.jgen.core.generator.VelocityAdapter;
import com.cg.jgen.core.importer.PDexImporter;
import com.cg.jgen.utils.Utility;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

public class EnumValueTest {

    private String xmlInput = "<Tables>\n" +
            "\t<Table name=\"RequestState\" isValueTable=\"true\">\t\n" +
            "\t\t<Values>\n" +
            "\t\t\t<Value id=\"0\" core=\"undefined\" displayText=\"not defined\" sortSequence=\"1\"/>\n" +
            "\t\t\t<Value id=\"1\" core=\"entered\" sortSequence=\"2\"/>\n" +
            "\t\t\t<Value id=\"2\" core=\"signed\" sortSequence=\"3\"/>\n" +
            "\t\t\t<Value id=\"3\" core=\"shipped\" displayText=\"items shipped\" sortSequence=\"4\"/>\n" +
            "\t\t\t<Value id=\"4\" core=\"finished\" sortSequence=\"5\"/>\n" +
            "\t\t</Values>\n" +
            "\t</Table>\n" +
            "</Tables>";

    private List<EnumValue> initTest() throws JDOMException, IOException {
        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(new StringReader(xmlInput));

        Element root = doc.getRootElement();
        Element table = root.getChild("Table");
        return PDexImporter.buildEnumValues(table);
    }


    @Test
    public void testEnumValueCreateList() throws JDOMException, IOException {

        // Tests if the data structure is correctly built from the XML

        List<EnumValue> enumValues = initTest();

        Assert.assertEquals(enumValues.size(), 5, "List of values has wrong size");
        String id = enumValues.get(0).getId();
        Assert.assertEquals(id, "0", "Id in row 0 not okay");
        String code = enumValues.get(4).getCode();
        Assert.assertEquals(code, "finished", "Code in row 4 not okay");
        int sortSequence = enumValues.get(2).getSortSequence();
        Assert.assertEquals(sortSequence, 3, "Code in row 2 not okay");
        String displayText = enumValues.get(3).getDisplayText();
        Assert.assertEquals(displayText, "items shipped", "Code in row 3 not okay");

        String displayText2 = enumValues.get(2).getDisplayText();
        Assert.assertNull(displayText2, "Display text unexpectedly not NULL");
    }

    @Test
    public void testEnumValueCreateJavaEnum() throws JDOMException, IOException {

        List<EnumValue> enumValues = initTest();

        String expectedSource = "package com.cgm.storm.generated.enums;\n" +
                "\n" +
                "/**\n" +
                " * Created by StormJGen\n" +
                " *\n" +
                " * Please do not modify this file.\n" +
                " * @see com.cgm.storm.model.RequestState\n" +
                " */\n" +
                "\npublic enum RequestStateEnum {\n" +
                "    undefined_0 (0),\n" +
                "    entered_1 (1),\n" +
                "    signed_2 (2),\n" +
                "    shipped_3 (3),\n" +
                "    finished_4 (4);\n" +
                "\n" +
                "    private Integer id;\n" +
                "\n" +
                "    RequestStateEnum(Integer id) {\n" +
                "        this.id = id;\n" +
                "    }\n\n" +
                "    public Integer getId() {\n" +
                "        return id;\n" +
                "    }\n"+
                "}";

        expectedSource = expectedSource.replaceAll("\n", System.lineSeparator());

        VelocityAdapter velocity = new VelocityAdapter();
        velocity.put("Entity", "RequestState");
        velocity.put("Values", enumValues);
        //velocity.put("Date", "2015-03-11 11:09:35");
        velocity.put("Package", "com.cgm.storm.generated.enums");
        velocity.put("util", new MockUtility());

        String result = velocity.render("templates/java/EnumValue.vm");

        Assert.assertEquals(result, expectedSource);
    }


    @Test
    public void testEnumValueCreateValueXml() throws JDOMException, IOException {

        List<EnumValue> enumValues = initTest();

        String expectedXml = "<BusinessObject xmlns=\"http://www.cgm.com/2011/helix/metadata\" name=\"/com/cgm/storm/model/RequestState\">\n" +
                "    <Columns>id, core, sortSequence</Columns>\n" +
                "    <Data>\n" +
                "        <Row>0, \"undefined\", 1</Row>\n" +
                "        <Row>1, \"entered\", 2</Row>\n" +
                "        <Row>2, \"signed\", 3</Row>\n" +
                "        <Row>3, \"shipped\", 4</Row>\n" +
                "        <Row>4, \"finished\", 5</Row>\n" +
                "    </Data>\n" +
                "</BusinessObject>";

        expectedXml = expectedXml.replaceAll("\n", System.lineSeparator());

        VelocityAdapter velocity = new VelocityAdapter();
        velocity.put("EnumValueName", "RequestState");
        velocity.put("Values", enumValues);
        velocity.put("HelixNamespace", "http://www.cgm.com/2011/helix/metadata");
        velocity.put("IdIsInteger", "true");

        String result = velocity.render("templates/xml/xmlEnumValue.vm");

        Assert.assertEquals(result, expectedXml);
    }

    @Test
    public void testEnumValueCreateValueXmlResource() throws JDOMException, IOException {
        List<EnumValue> enumValues = initTest();

        String expectedXml = "<BusinessObject xmlns=\"http://www.cgm.com/2011/helix/metadata\" name=\"/cgm/helix/Resource\">\n" +
                "    <Columns>resourceId, localeId, resourceTypeId, catalogId, name, property, value</Columns>\n" +
                "    <Data>\n" +
                "        <Row>\"STORM_XYZ2234ABC00\", \"en_US\", \"label\", \"FCD703F1BE628B9242\", \"Enumeration\", \"RequestStateUndefined\", \"not defined\"</Row>\n" +
                "        <Row>\"STORM_XYZ2234ABC01\", \"en_US\", \"label\", \"FCD703F1BE628B9242\", \"Enumeration\", \"RequestStateEntered\", \"entered\"</Row>\n" +
                "        <Row>\"STORM_XYZ2234ABC02\", \"en_US\", \"label\", \"FCD703F1BE628B9242\", \"Enumeration\", \"RequestStateSigned\", \"signed\"</Row>\n" +
                "        <Row>\"STORM_XYZ2234ABC03\", \"en_US\", \"label\", \"FCD703F1BE628B9242\", \"Enumeration\", \"RequestStateShipped\", \"items shipped\"</Row>\n" +
                "        <Row>\"STORM_XYZ2234ABC04\", \"en_US\", \"label\", \"FCD703F1BE628B9242\", \"Enumeration\", \"RequestStateFinished\", \"finished\"</Row>\n" +
                "    </Data>\n" +
                "</BusinessObject>";

        expectedXml = expectedXml.replaceAll("\n", System.lineSeparator());

        VelocityAdapter velocity = new VelocityAdapter();
        Utility utility = new Utility();
        velocity.put("util", utility);
        velocity.put("EnumValueName", "RequestState");
        velocity.put("EnumValueCatalogId", "FCD703F1BE628B9242");
        velocity.put("HelixNamespace", "http://www.cgm.com/2011/helix/metadata");

        for (EnumValue enumValue : enumValues) {
            enumValue.setResourceId("STORM_XYZ2234ABC0" + enumValue.getId());
        }

        velocity.put("Values", enumValues);

        String result = velocity.render("templates/xml/xmlEnumValueResource.vm");

        Assert.assertEquals(result, expectedXml);
    }


}

