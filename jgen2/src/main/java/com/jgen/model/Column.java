package com.jgen.model;

import com.google.common.base.Strings;
import com.jgen.exception.JgenException;
import com.jgen.importer.XmlPdexConstants;
import org.jdom2.Element;

/**
 *
 */
public class Column {

    private String name;
    private String type;
    private int length;
    private String domain;
    private boolean mandatory;
    private String defaultValue;

    public Column() {

    }

    public Column(Element element) {
        assertElement(element);

    }

    private void assertElement(Element element) {
        if (element == null) {
            throw new JgenException("Null Element object");
        }
        if (!element.getName().equals(XmlPdexConstants.COLUMN)) {
            throw new JgenException("Wrong XmlElement name: '" + element.getName() + "'");
        }
        if (Strings.isNullOrEmpty(element.getAttributeValue("name"))) {

        }
        if (Strings.isNullOrEmpty(element.getAttributeValue("type"))) {

        }
        if (Strings.isNullOrEmpty(element.getAttributeValue("length"))) {

        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
