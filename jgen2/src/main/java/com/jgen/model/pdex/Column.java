package com.jgen.model.pdex;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Column {
    @XmlAttribute(name = "type", required = true)
    protected ColumnType type;

    @XmlAttribute(name = "name", required = true)
    protected String name;

    @XmlAttribute(name = "mandatory")
    protected Boolean mandatory;

    @XmlAttribute(name = "length")
    protected String length;

    @XmlAttribute(name = "defaultValue")
    protected String defaultValue;

    @XmlAttribute(name = "domain")
    protected String domain;
}
