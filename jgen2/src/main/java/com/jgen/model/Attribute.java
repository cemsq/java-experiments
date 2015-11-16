package com.jgen.model;

import com.cgm.storm.utils.common.StringUtils;
import com.google.common.base.Strings;
import com.jgen.exception.JgenException;
import com.jgen.model.dictionary.Dictionary;

/**
 *
 */
public class Attribute {
    private static final Dictionary dic = Dictionary.getInstance();

    private String name;
    private String value;

    public Attribute(String name, String value) {
        assertStr(name, "'name' must be not null or not empty");
        assertStr(value, "'value' must be not null or not empty");

        this.name = dic.getJGenValueByPDexValue(name);
        if (name.equals("name")) {
            this.value = StringUtils.camelCase(value);
        } else {
            this.value = dic.getJGenValueByPDexValue(value);
        }
    }

    private static void assertStr(String str, String message) {
        if (Strings.isNullOrEmpty(str)) {
            throw new JgenException(message);
        }
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
