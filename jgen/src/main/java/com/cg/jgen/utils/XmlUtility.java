package com.cg.jgen.utils;

import com.cg.jgen.core.exception.JGenException;
import com.cg.jgen.utils.idgenerator.IIdGenerator;
import com.cg.jgen.utils.idgenerator.IdGeneratorImpl;
import com.cgm.storm.utils.common.StringUtils;
import com.google.common.base.Preconditions;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaderJDOMFactory;
import org.jdom2.input.sax.XMLReaderSchemaFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Methods for handling XML
 */
public class XmlUtility {
    private static final Logger log = LoggerFactory.getLogger(XmlUtility.class);

    private static IIdGenerator idGenerator = null;

    public static void setIdGenerator(IIdGenerator idGenerator) {
        XmlUtility.idGenerator = idGenerator;
    }

    private static int findXmlElement(Element root, String elementName, Namespace namespace, String attributeName, String value) {
        int index = -1;

        List<Element> list = root.getChildren(elementName, namespace);

        for (Element element : list) {
            String att = element.getAttribute(attributeName).getValue();
            if (att.equals(value)) {
                index = root.indexOf(element);
                break;
            }
        }

        return index;
    }

    private static Map<String, Integer> fillMap(Element elementData, Namespace namespace) {
        List<Element> rows = elementData.getChildren("Row", namespace);
        Map<String, Integer> map = new HashMap<>();

        for (Element element : rows) {
            String rowNew = element.getText();
            String[] items = rowNew.split("\"\\s*,\\s*\"");
            String key = items[5];

            Integer index = elementData.indexOf(element);
            map.put(key, index);
        }

        return map;
    }

    private static Document createDocument(String data) {
        // convert new data to XML
        SAXBuilder builder = new SAXBuilder();
        Document doc = null;
        try {
            doc = builder.build(new StringReader(data));
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }

    // write content to storm.init.enumeration.xml
    //  existing tables are replaced
    public static void adjustXmlForValueTable(Namespace namespace, Element root, String data) {

        Document doc = createDocument(data);

        Element newData;
        newData = doc.getRootElement().detach();

        // if the entry already exists replace it
        String newDataEntityName = newData.getAttribute("name").getValue();
        Integer indexFound = findXmlElement(root, "BusinessObject", namespace, "name", newDataEntityName);

        if (indexFound >= 0) {
            // replace the content with newData
            root.setContent(indexFound, newData);
        } else {
            root.addContent(newData);
        }
    }

    public static void adjustXmlResourceForValueTable(Namespace namespace, Element rootOrig, String xmlData) {

        Document doc = createDocument(xmlData);

        Element newData;
        newData = doc.getRootElement().detach();

        Integer indexFound = findXmlElement(rootOrig, "BusinessObject", namespace, "name", "/cgm/helix/Resource");
        if (indexFound >= 0) {

            Element businessObject = rootOrig.getChild("BusinessObject", namespace);
            Element dataOrig = businessObject.getChild("Data", namespace);
//            dataOrig = dataOrig.detach();

            Map<String, Integer> mapOrig;
            mapOrig = fillMap(dataOrig, namespace);

            Element dataNew = newData.getChild("Data", namespace);
            List<Element> rowListNew = dataNew.getChildren("Row", namespace);

            for (Element element : rowListNew) {

                String rowNew = element.getText();
                String[] items = rowNew.split("\"\\s*,\\s*\"");
                String property = items[5];

                // We clone the element because element.detach throws the element out of the list
                Element el = element.clone();

                if (mapOrig.containsKey(property)) {
                    int index = mapOrig.get(property);

                    Content cont = dataOrig.getContent(index);
                    String rowContent = ((Element) cont).getText();
                    String[] itemsOld = rowContent.split("\"\\s*,\\s*\"");

                    // set the new translated value
                    itemsOld[6] = items[6];

                    String rowContentNew = com.cg.helix.util.StringUtils.join("\", \"", itemsOld);
                    el.setText(rowContentNew);

                    dataOrig.setContent(index, el.detach());
                } else {
                    el.detach();
                    String rowContent = el.getText();
                    String[] itemsNew = rowContent.split("\"\\s*,\\s*\"");
                    itemsNew[0] = "\"" + getNewId();
                    String rowContentNew = com.cg.helix.util.StringUtils.join("\", \"", itemsNew);
                    el.setText(rowContentNew);
                    dataOrig.addContent(el);
                }
            }

        } else {
            rootOrig.addContent(newData);
        }

    }

    private static String getNewId() {
        // deliver a new Helix-ID
//        return "STORM_PSDEMO000095";
        if (idGenerator == null) {
            idGenerator = new IdGeneratorImpl();
        }
        return idGenerator.createUniqueId();
    }

    public static Document readXmlFromFile(String path) {
        return readXml(path, getSAXBuilder(""));
    }

    public static Document readXmlFromFileWithSchema(String path, String schemaFile) {
        return readXml(path, getSAXBuilder(schemaFile));
    }

    public static Document readXml(String path, SAXBuilder builder) {
        try {
            return builder.build(new File(path));

        } catch (JDOMException | IOException e) {
            throw new JGenException("Error reading file: " + path, e);
        }
    }

    public static SAXBuilder getSAXBuilder(String schemaFile) {
        SAXBuilder builder;

        try {
            if (StringUtils.isValid(schemaFile)) {
                SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
                Schema schema = schemaFactory.newSchema(new File(schemaFile));

                // create an XMLReaderJDOMFactory by passing the schema
                XMLReaderJDOMFactory factory = new XMLReaderSchemaFactory(schema);

                // create a SAXBuilder using the XMLReaderJDOMFactory
                builder = new SAXBuilder(factory);
            } else {
                builder = new SAXBuilder();
            }
        } catch (SAXException e) {
            throw new JGenException("Error creating schema " + schemaFile, e);
        }
        return builder;
    }

    public static List<String> buildSimpleList(Element parent, String rootTagName, String childTagName, String attribute) {
        Preconditions.checkNotNull(parent, "parent Element must be not null");
        StringUtils.validateString(rootTagName, "rootTagName must be not null or not empty");
        StringUtils.validateString(childTagName, "childTagName must be not null or not empty");
        StringUtils.validateString(attribute, "attribute must be not null or not empty");

        Element root = parent.getChild(rootTagName);
        if (root == null) {
            throw new JGenException("'" + rootTagName + "' element in the xml file was not found");
        }

        List<Element> children = root.getChildren(childTagName);
        if (children.size() == 0) {
            throw new JGenException("'" + childTagName + "' element in '" + rootTagName + "' not found");
        }

        List<String> list = new ArrayList<>();
        for (Element child : children) {
            Attribute att = child.getAttribute(attribute);
            if (att == null) {
                throw new JGenException("Attribute: '" + attribute + "' not found");
            }
            StringUtils.validateString(att.getValue(), "Empty attribute: " + attribute);
            list.add(att.getValue());
        }

        return list;
    }
}
