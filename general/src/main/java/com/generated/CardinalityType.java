//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// �nderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2015.11.06 um 03:53:58 PM CET 
//


package com.cg.jgen.generated;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse f�r cardinalityType.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="cardinalityType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="many_to_one"/>
 *     &lt;enumeration value="one_to_one"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "cardinalityType")
@XmlEnum
public enum CardinalityType {

    @XmlEnumValue("many_to_one")
    MANY_TO_ONE("many_to_one"),
    @XmlEnumValue("one_to_one")
    ONE_TO_ONE("one_to_one");
    private final String value;

    CardinalityType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CardinalityType fromValue(String v) {
        for (CardinalityType c: CardinalityType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
