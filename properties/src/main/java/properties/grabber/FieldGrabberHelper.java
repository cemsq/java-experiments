package properties.grabber;

import com.google.common.base.Strings;
import properties.exception.FieldFormatException;

/**
 *
 */
public abstract class FieldGrabberHelper {
    private FieldGrabberHelper() {}

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
            throw new FieldFormatException("fieldName should not ent with dot");
        }
    }
}
