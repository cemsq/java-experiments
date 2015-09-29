package com.cg.jgen.core;

import com.cg.jgen.core.exception.JGenException;
import com.cg.jgen.utils.Globals;
import com.cg.jgen.utils.KeyWordChecker;
import com.cg.jgen.utils.Utility;
import com.cgm.storm.utils.common.StringUtils;
import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a Table column in a Java/Helix context.
 */
public class Member {

    private static final Dictionary dic = Dictionary.getInstance();
    private static final List<String> annotations = Arrays.asList(
            "decimals"
            , "defaultValue"
            , "length"
            , "mandatory"
            , "maxOcurrs"
            , "minOcurrs"
            //, "name"
            , "override"
            , "polymorphic"
            , "referenceType"
            //, "type"
    );
    private boolean helixAnnotated;
    private List<Attribute> attributeList = new ArrayList<>();

    public Member() {
        helixAnnotated = false;
    }

    public String getAttributeValue(String name) {
        for (Attribute att : attributeList) {
            if (att.name.equals(name)) {
                return att.value;
            }
        }

        return null;
    }

    public Attribute getAttributeByName(String name) {
        for (Attribute att : attributeList) {
            if (att.name.equals(name)) {
                return att;
            }
        }

        return null;
    }

    public void addAttribute(Attribute att) {
        addAttribute(att, "");
    }

    public void addAttribute(String name, String value) {
        addAttribute(new Attribute(name, value));
    }

    public void addAttribute(Attribute att, String tableName) {
        if (att == null || Globals.isListed(attributeList, att) || (att.name.equals("mandatory") && att.value.equals("false"))) {
            return;
        }

        if (att.name.equals("name")) {
            KeyWordChecker.checkKeyWord(att.value, "Column", tableName);
        }

        if (Globals.isListed(annotations, att.name)) {
            helixAnnotated = true;
        }

        attributeList.add(att);
    }

    public List<Attribute> getAttributeList() {
        return attributeList;
    }

    public void interpretAnnotation() {
        validate();

        int i = 0;
        StringBuilder sb = new StringBuilder("");
        for (Attribute att : getAttributeList()) {
            if (Globals.isListed(annotations, att.name)) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(att.name);
                sb.append(" = ");
                String value = att.value;
                if (att.name.equals("defaultValue")) {
                    value = "\"" + value + "\"";
                }
                sb.append(value);
                i++;
            }
        }

        String str = sb.toString();
        if (helixAnnotated) {
            str = "(" + str + ")";
        }
        str = "@Element" + str;

        this.addAttribute(new Attribute("helixAnnotation", str));
    }

    public void validate() {
        Attribute name = this.getAttributeByName("name");
        Attribute type = this.getAttributeByName("type");
        Preconditions.checkNotNull(name, "Member without name attribute defined");
        Preconditions.checkNotNull(type, "Member without type attribute defined");

        if (type.value.equals("String")) {
            Attribute length = this.getAttributeByName("length");
            if (length == null) {
                throw new JGenException("Attribute '" + name.value + "' has no 'length' attribute defined. This is required for String fields");
            }
        }
    }

    @Override
    public String toString() {
        String str;

        String name = getAttributeValue("name");
        String type = getAttributeValue("type");
        str = type + " " + name;

        return str;
    }

    /**
     *
     */
    public static class Attribute {
        private String name;
        private String value;

        public Attribute(String name, String value) {
            StringUtils.validateString(name, "'name' must be not null or not empty");
            StringUtils.validateString(value, "'value' must be not null or not empty");

            this.name = dic.getJGenValueByPDexValue(name);
            if (name.equals("name")) {
                this.value = Utility.camelCase(value);
            } else {
                this.value = dic.getJGenValueByPDexValue(value);
            }
        }

        public String getName() {
            return name;
        }

        public String getValue() {
            return value;
        }

        public boolean nameIs(String name) {
            return this.name.equals(name);
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Attribute)) {
                return false;
            }

            Attribute attribute = (Attribute) o;

            return name != null && name.equals(attribute.name);
        }

        @Override
        public int hashCode() {
            return name != null ? name.hashCode() : 0;
        }

        @Override
        public String toString() {
            return name + " = " + value;
        }
    }
}
