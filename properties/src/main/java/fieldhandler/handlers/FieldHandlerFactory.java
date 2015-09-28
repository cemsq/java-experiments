package fieldhandler.handlers;

import com.google.common.base.Strings;
import fieldhandler.FieldHandler;
import fieldhandler.AccessorType;
import fieldhandler.accessor.FieldAccessor;
import reflection.exception.FieldFormatException;

/**
 *
 */
public class FieldHandlerFactory {

    public static FieldHandler create(Class clazz, String field) {
        return create(clazz, field, FieldAccessor.DEFAULT_TYPE);
    }

    public static FieldHandler create(Class clazz, String field, AccessorType accessorType) {
        String[] fields = parseFieldName(field);
        String owner = fields[0];
        String composite = fields[1];

        FieldAccessor accessor = FieldAccessor.factory(clazz, owner, accessorType);

        SimpleFieldHandler flat = new SimpleFieldHandler(accessor);

        // flat
        if (fields[1].equals("")) {
            return flat;

        } else {
            // array - composite
            if (flat.isArray()) {
                return new ArrayFieldHandler(flat, create(flat.getFieldType(), composite, accessorType));
            }

            // composite
            return new CompositeFieldHandler(flat, create(flat.getFieldType(), composite, accessorType));
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
            throw new FieldFormatException("fieldName should not end with dot");
        }
    }
}
