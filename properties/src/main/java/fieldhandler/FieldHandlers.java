package fieldhandler;

import com.google.common.base.Strings;
import reflection.Reflections;
import reflection.exception.FieldFormatException;
import reflection.exception.ReflectionsException;

import java.lang.reflect.Field;
import java.util.List;

/**
 *
 */
public class FieldHandlers {

    public static FieldHandler create(Class clazz, String field) {
        String[] fields = parseFieldName(field);

        // flat
        if (fields[1].equals("")) {
            return flat(clazz, fields[0]);

        } else {
            // composite
            Field f = Reflections.getField(clazz, fields[0]);
            if (f.getType().equals(List.class)) {
                return array(clazz, fields[0], fields[1]);
            }

            return composite(clazz, fields[0], fields[1]);
        }
    }

    public static FlatFieldHandler flat(Class clazz, String field) {
        return new FlatFieldHandler(clazz, field);
    }

    public static CompositeFieldHandler composite(Class clazz, String field) {
        String[] fields = parseFieldName(field);
        return composite(clazz, fields[0], fields[1]);
    }

    public static CompositeFieldHandler composite(Class clazz, String field, String composite) {
        return new CompositeFieldHandler(clazz, field, composite);
    }

    public static ArrayFieldHandler array(Class clazz, String field) {
        String[] fields = parseFieldName(field);
        return array(clazz, fields[0], fields[1]);
    }

    public static ArrayFieldHandler array(Class clazz, String field, String composite) {
        return new ArrayFieldHandler(clazz, field, composite);
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
            throw new FieldFormatException("fieldName should not end with dot");
        }
    }
}
