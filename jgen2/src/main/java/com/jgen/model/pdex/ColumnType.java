package com.jgen.model.pdex;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "columnType")
@XmlEnum
public enum ColumnType {
    CH("String"),
    IN("Integer"),
    FX("Float"),
    TS("LocalDateTime"),
    DA("LocalDate");

    private String type;

    ColumnType(String type) {
        this.type = type;
    }

    public String getValue() {
        return type;
    }
}
