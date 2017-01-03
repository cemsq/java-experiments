package com.jgen.model.pdex;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Index {

    @XmlAttribute(name = "name", required = true)
    protected String name;

    @XmlAttribute(name = "primary")
    protected Boolean primary;

    @XmlAttribute(name = "unique")
    protected Boolean unique;

    @XmlElementWrapper(name = "Columns")
    @XmlElement(name = "Column", required = true)
    protected List<IndexColumn> columns;


    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class IndexColumn {
        @XmlAttribute(name = "name", required = true)
        protected String name;
    }
}
