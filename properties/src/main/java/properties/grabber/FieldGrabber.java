package properties.grabber;

import java.lang.reflect.Field;
import static properties.grabber.FieldGrabberHelper.*;
/**
 *
 */
public class FieldGrabber {

    private Field field;
    private FieldGrabber grabber;

    public FieldGrabber(Class clazzOwner, String fieldName) {
        buildFieldSequence(this, clazzOwner, fieldName);
    }

    private static void buildFieldSequence(FieldGrabber grabber, Class clazzOwner, String fieldName) {
        String[] fields = parseFieldName(fieldName);
        grabber.field = getField(clazzOwner, fields[0]);

        if (!fields[1].equals("")) {
            grabber.grabber = new FieldGrabber(grabber.field.getType(), fields[1]);
        }
    }

    public Object getValue(Object obj) {
        Object value = FieldGrabberHelper.getFieldValue(field, obj);
        return grabber != null? grabber.getValue(value): value;
    }

    public void setValue(Object obj, Object value) {
        Object fieldValue = FieldGrabberHelper.getFieldValue(field, obj);

        if (grabber != null) {
            grabber.setValue(fieldValue, value);
        } else {
            FieldGrabberHelper.setFieldValue(field, obj, value);
        }
    }
}
