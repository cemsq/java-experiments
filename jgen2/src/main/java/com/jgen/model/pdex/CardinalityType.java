package com.jgen.model.pdex;

import javax.xml.bind.annotation.XmlEnumValue;

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
