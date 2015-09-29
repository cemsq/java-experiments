package com.cgm.storm.utils.fieldhandler.handlers;

import com.cgm.storm.utils.fieldhandler.FieldHandler;
import com.cgm.storm.utils.fieldhandler.accessor.FieldAccessor;
import com.cgm.storm.utils.reflection.exception.FieldFormatException;
import com.google.common.base.Strings;

/**
 *
 */
public abstract class FieldHandlerFactory {

    public static FieldHandler create(Class clazz, String field) {
        String[] fields = parseFieldName(field);
        String owner = fields[0];
        String composite = fields[1];

        FieldAccessor accessor = FieldAccessor.factory(clazz, owner);
        // simple
        if (fields[1].equals("")) {
            return new SimpleFieldHandler(accessor);

        } else {
            // array - composite
            if (accessor.isArray()) {
                return new ArrayFieldHandler(accessor, create(accessor.getFieldType(), composite));
            }

            // composite
            return new CompositeFieldHandler(accessor, create(accessor.getFieldType(), composite));
        }
    }

    /**
     * fieldName = "some.field.Name"
     *  returns:
     *      [0] = "some"
     *      [1] = "field.name"
     */
    public static String[] parseFieldName(String fieldName) {
        checkFieldName(fieldName);

        String field = fieldName;
        int point = fieldName.indexOf(".");

        if (point > 0) {
            field = fieldName.substring(0, point);
            fieldName = fieldName.substring(point + 1);
        } else {
            fieldName = "";
        }

        return new String[]{
                field,
                fieldName
        };
    }

    private static void checkFieldName(String fieldName) {
        if (Strings.isNullOrEmpty(fieldName)) {
            throw new FieldFormatException("Empty fieldName");
        }
        if (fieldName.endsWith(".")) {
            throw new FieldFormatException("fieldName should not end with dot, fieldName: '" + fieldName + "'");
        }
    }
}
