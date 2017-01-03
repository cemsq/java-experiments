package com.jgen.model.pdex;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "tables"
//        "foreignKeys",
//        "domains"
})
@XmlRootElement(name = "Database")
@Data
public class Database {

    @XmlElementWrapper(name="Tables")
    @XmlElement(name = "Table", required = true)
    private List<Table> tables;
}
