package com.jgen.model.pdex;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class ForeignKey {

    @XmlAttribute(name = "name", required = true)
    protected String name;

    @XmlElement(name = "Column", required = true)
    protected ForeignKeyColumn column;

    @XmlAttribute(name = "child", required = true)
    protected String child;

    @XmlAttribute(name = "parent", required = true)
    protected String parent;

    @XmlAttribute(name = "cardinality", required = true)
    protected CardinalityType cardinality;

    @XmlAttribute(name = "masterDetail")
    protected Boolean masterDetail;

    public static class ForeignKeyColumn {

        @XmlAttribute(name = "src", required = true)
        protected String src;
        @XmlAttribute(name = "target", required = true)
        protected String target;
    }
}
