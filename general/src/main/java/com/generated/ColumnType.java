//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// �nderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2015.11.06 um 03:53:58 PM CET 
//


package com.generated;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse f�r columnType.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="columnType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CH"/>
 *     &lt;enumeration value="IN"/>
 *     &lt;enumeration value="FX"/>
 *     &lt;enumeration value="TS"/>
 *     &lt;enumeration value="DA"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "columnType")
@XmlEnum
public enum ColumnType {

    CH,
    IN,
    FX,
    TS,
    DA;

    public String value() {
        return name();
    }

    public static ColumnType fromValue(String v) {
        return valueOf(v);
    }

}
