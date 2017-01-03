package com.jgen.model.pdex;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import java.math.BigInteger;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class EnumValue {

    @XmlAttribute(name = "id", required = true)
    protected String id;
    @XmlAttribute(name = "code", required = true)
    protected String code;
    @XmlAttribute(name = "displayText")
    protected String displayText;
    @XmlAttribute(name = "sortSequence", required = true)
    protected BigInteger sortSequence;
}
