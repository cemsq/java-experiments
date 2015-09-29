package com.cg.jgen.utils;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.StringReader;

public class XmlUtilityTest {

    private static Namespace namespace = Namespace.getNamespace("http://www.cgm.com/2011/helix/metadata");


    private static String xmlResource = "<BusinessObjects xmlns=\"http://www.cgm.com/2011/helix/metadata\">\n" +
            "    <BusinessObject name=\"/cgm/helix/Resource\">\n" +
            "        <Columns>resourceId,localeId,resourceTypeId,catalogId,name,property,value</Columns>\n" +
            "        <Data>\n" +
            "            <Row>\"STORM_PSDEMO000090\", \"en_US\", \"label\", \"FCD703F1BE628B9242\", \"PsDemo\", \"simpleDescription\", \"This is a simple description.\"</Row>\n" +
            "            <Row>\"STORM_PSDEMO000091\", \"en_US\", \"label\", \"FCD703F1BE628B9242\", \"Enumeration\", \"RequestStateUndefined\", \"not def\"</Row>\n" +
            "            <Row>\"STORM_PSDEMO000092\", \"en_US\", \"label\", \"FCD703F1BE628B9242\", \"PsDemo\", \"programmingLanguagesCategory\", \"programming languages\"</Row>\n" +
            "        </Data>\n" +
            "    </BusinessObject>\n" +
            "</BusinessObjects>";

    private static Element initXml(String xml) {
        SAXBuilder builder = new SAXBuilder();
        Document doc = null;
        try {
            doc = builder.build(new StringReader(xml));
        } catch (JDOMException | IOException e) {
            e.printStackTrace();
        }

        return doc.getRootElement();
    }

    @Test(enabled = false)
    public void testAdjustXmlResourceForValueTable() {
        Element root = initXml(xmlResource);

        String enumXml = "<BusinessObject name=\"/cgm/helix/Resource\" xmlns=\"http://www.cgm.com/2011/helix/metadata\">\n" +
                "    <Columns>resourceId, localeId, resourceTypeId, catalogId, name, property, value</Columns>\n" +
                "    <Data>\n" +
                "        <Row>\"STORM_PSDEMO000095\", \"en_US\", \"label\", \"FCD703F1BE628B9242\", \"Enumeration\", \"RequestStateUndefined\", \"not defined\"</Row>\n" +
                "        <Row>\"STORM_PSDEMO000093\", \"en_US\", \"label\", \"FCD703F1BE628B9242\", \"Enumeration\", \"RequestStateShipped\", \"items shipped\"</Row>\n" +
                "    </Data>\n" +
                "</BusinessObject>";


        XmlUtility.setIdGenerator(new IdGeneratorMock());
        XmlUtility.adjustXmlResourceForValueTable(namespace, root, enumXml);

        String expectedXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<BusinessObjects xmlns=\"http://www.cgm.com/2011/helix/metadata\">\n" +
                "    <BusinessObject name=\"/cgm/helix/Resource\">\n" +
                "        <Columns>resourceId,localeId,resourceTypeId,catalogId,name,property,value</Columns>\n" +
                "        <Data>\n" +
                "            <Row>\"STORM_PSDEMO000090\", \"en_US\", \"label\", \"FCD703F1BE628B9242\", \"PsDemo\", \"simpleDescription\", \"This is a simple description.\"</Row>\n" +
                "            <Row>\"STORM_PSDEMO000091\", \"en_US\", \"label\", \"FCD703F1BE628B9242\", \"Enumeration\", \"RequestStateUndefined\", \"not defined\"</Row>\n" +
                "            <Row>\"STORM_PSDEMO000092\", \"en_US\", \"label\", \"FCD703F1BE628B9242\", \"PsDemo\", \"programmingLanguagesCategory\", \"programming languages\"</Row>\n" +
                "            <Row>\"STORM_PSDEMO000095\", \"en_US\", \"label\", \"FCD703F1BE628B9242\", \"Enumeration\", \"RequestStateShipped\", \"items shipped\"</Row>\n" +
                "        </Data>\n" +
                "    </BusinessObject>\n" +
                "</BusinessObjects>\n";

        expectedXml = expectedXml.replaceAll("\n", System.lineSeparator());

        Document doc = new Document(root.detach());
        Format format = Format.getPrettyFormat();
        format.setIndent("    ");
        format.setLineSeparator("\n");

        XMLOutputter xmlOutputter = new XMLOutputter(format);
        String xmlString = xmlOutputter.outputString(doc);

        xmlString = xmlString.replace("\n", System.lineSeparator());

        Assert.assertEquals(xmlString, expectedXml);

    }
}

