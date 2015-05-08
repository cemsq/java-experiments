package exp.xml;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class ModifyingElementsTest {
    @Test
    public void shouldGetAndSetId() {
        Element root = getRootElement("test1.xml");

        List<Element> list = root.getChildren("row");

        Element e = list.get(0);
        String id = getId(e);
        Assert.assertEquals(id, "1");

        setText(e, "newText");
        Assert.assertEquals(e.getText(), "1, newText");
    }

    @Test
    public void modifyElementFromList_originalDocIsModifiedToo() {
        Element root = getRootElement("test1.xml");

        List<Element> list = root.getChildren("row");

        Element eFromList = list.get(0);
        setText(eFromList, "a change");

        Element e = root.getChild("row");
        Assert.assertEquals(e.getText(), "1, a change");
    }

    @Test
    public void modifyMap_originalDocIsModifiedToo() {
        Document docOri = getDocElement("test1.xml");
        Element root = docOri.getRootElement();

        Map<String, Element> map = fillMap(root);
        Element eFromMap = map.get("1");
        setText(eFromMap, "newText");

        Element e = root.getChild("row");
        Assert.assertEquals(e.getText(), "1, newText");
    }

    @Test
    public void modifyElementsByText() {
        Document docOri = getDocElement("test1.xml");
        Element root = docOri.getRootElement();
        Map<String, Element> map = fillMap(root);

        Element rootNew = getDocElement("test2.xml").getRootElement();
        for (Element e : rootNew.getChildren("row")) {
            String id = getId(e);
            Element newElement = e.clone();

            if (map.containsKey(id)) {
                Element inMap = map.get(id);
                boolean modify = !inMap.getText().equals(e.getText());

                if (modify) {
                    inMap.setText(e.getText());
                }
            } else {
                root.addContent(newElement);
            }
        }

        saveDocument(docOri, "modifiedByText.xml");
    }

    @Test
    public void modifyElementsByRemoveAndAddContent() {
        Document docOri = getDocElement("test1.xml");
        Element root = docOri.getRootElement();
        Map<String, Element> map = fillMap(root);

        Element rootNew = getDocElement("test2.xml").getRootElement();
        for (Element e : rootNew.getChildren("row")) {
            String id = getId(e);
            Element newElement = e.clone();

            if (map.containsKey(id)) {
                Element inMap = map.get(id);
                boolean modify = !inMap.getText().equals(e.getText());

                if (modify) {
                    root.removeContent(inMap);
                    root.addContent(newElement);
                }
            } else {
                root.addContent(newElement);
            }
        }

        saveDocument(docOri, "modifiedByRemoveAndAddContent.xml");
    }

    @Test
    public void modifyElementsBySetContent() {
        Document docOri = getDocElement("test1.xml");
        Element root = docOri.getRootElement();
        Map<String, Element> map = fillMap(root);

        Element rootNew = getDocElement("test2.xml").getRootElement();
        for (Element e : rootNew.getChildren("row")) {
            String id = getId(e);
            Element newElement = e.clone();
            if (map.containsKey(id)) {
                Element inMap = map.get(id);
                boolean modify = !inMap.getText().equals(e.getText());

                if (modify) {
                    int index = root.indexOf(inMap);
                    root.setContent(index, newElement);
                }
            } else {
                root.addContent(newElement);
            }
        }

        saveDocument(docOri, "modifiedBySetContent.xml");
    }

    public Document getDocElement(String xmlFile) {
        try {
            SAXBuilder builder = new SAXBuilder();
            return builder.build(xmlFile);
        }catch (JDOMException | IOException e) {
            throw new RuntimeException("Cannot read file: " + xmlFile);
        }

    }

    public Element getRootElement(String xmlFile) {
        return getDocElement(xmlFile).getRootElement();
    }

    public String getId(Element element) {
        String []text = element.getText().split(",");
        return text[0];
    }

    public void setText(Element e, String text) {
        String id = getId(e);
        e.setText(id + ", " + text);
    }

    public Map<String, Element> fillMap(Element root) {
        Map<String, Element> map = new HashMap<>();

        for (Element e : root.getChildren("row")) {
            map.put(getId(e), e);
        }

        return map;
    }

    public void saveDocument(Document doc, String fileName) {
        XMLOutputter xmlOutput = new XMLOutputter();

        xmlOutput.setFormat(Format.getPrettyFormat());
        try {
            xmlOutput.output(doc, new FileWriter(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
