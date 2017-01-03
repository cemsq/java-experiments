package com.jgen.model.pdex;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Table {

    @XmlAttribute(name = "name", required = true)
    private String name;

    @XmlAttribute(name = "isValueTable")
    private Boolean isValueTable;

    @XmlElement(name = "Comment")
    private String comment;

    @XmlElementWrapper(name="Columns")
    @XmlElement(name = "Column", required = true)
    private List<Column> columns;

    @XmlElement(name = "Index", required = true)
    private List<Index> index;

    @XmlElementWrapper(name="Values")
    @XmlElement(name = "Value")
    private List<EnumValue> values;
}
