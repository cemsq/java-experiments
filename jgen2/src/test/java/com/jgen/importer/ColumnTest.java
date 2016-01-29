package com.jgen.importer;

import com.jgen.model.Column;
import org.jdom2.Element;
import org.testng.annotations.Test;

/**
 *
 */
public class ColumnTest {

    @Test
    public void shouldCreateColumnFromElement() {
        Element eColumn = new Element(XmlPdexConstants.COLUMN);
        Column column = new Column(eColumn);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void shouldThrowNullPointerException() {
        Column column = new Column(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldThrowArgumentException() {
        Element eColumn = new Element("column");
        Column column = new Column(eColumn);
    }
}
