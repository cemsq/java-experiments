package com.cg.jgen.utils;

import com.cg.jgen.core.Entity;
import com.cgm.storm.utils.common.StringUtils;

/**
 *
 */
public class TsTypeDecoder {

    public static String toTypeScript(String typeName) {
        StringUtils.validateString(typeName, "Null or empty dataType");

        // easy types to find
        switch (typeName) {
            case "String":
                return "string";

            // Numeric types
            case "int":
            case "double":
            case "float":

            case "Long":
            case "Double":
            case "Float":
            case "Integer":
            case "BigInteger":
            case "BigDecimal":
            case "AtomicInteger":
            case "AtomicLong":
                return "number";

            case "Filter":
                return "IFilter";
            case "Range":
                return "IRange";
            case "Sort":
                return "ISort";

            case "void":
                return "void";
        }

        // may be an Array
        String extracted = extractTypeFromArray(typeName);
        if (!extracted.equals("")) {
            return "Array< " + toTypeScript(extracted) + " >";
        }

        // may be an Entity
        Entity ett = Entity.getEntity(typeName);
        if (ett != null) {
            return "I" + ett.getName();
        }

        return "any";
    }

    private static String extractTypeFromArray(String typeName) {
        int ini = typeName.indexOf("List<");
        int end = typeName.indexOf(">");

        if (0 <= ini && ini < end && end < typeName.length()) {
            return typeName.substring(ini + 5, end);
        }

        ini = typeName.indexOf("ArrayList<");
        if (0 <= ini && ini < end && end < typeName.length()) {
            return typeName.substring(ini + 10, end);
        }

        return "";
    }
}
