package com.jtlog.serialization;

import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public enum Separator {
    START_HEAD,
    END_HEAD,
    START_RECORD,
    END_RECORD,
    VALUE,
    SUB_VALUE
    ;

    public static Map<Separator, String> defaultDefinition() {
        HashMap<Separator, String> map = Maps.newHashMap();
        map.put(Separator.START_RECORD, "");
        map.put(Separator.END_RECORD, "\n");
        map.put(Separator.VALUE, "|");
        map.put(Separator.SUB_VALUE, ",");
        return map;
    }
}
