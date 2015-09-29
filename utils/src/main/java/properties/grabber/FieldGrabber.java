package properties.grabber;

import reflection.Reflections;

import java.lang.reflect.Field;

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
        String[] fields = FieldGrabberHelper.parseFieldName(fieldName);
        grabber.field = Reflections.getField(clazzOwner, fields[0]);

        if (!fields[1].equals("")) {
            grabber.grabber = new FieldGrabber(grabber.field.getType(), fields[1]);
        }
    }

    public Object getValue(Object obj) {
        Object value = Reflections.getFieldValue(field, obj);
        return grabber != null? grabber.getValue(value): value;
    }

    public void setValue(Object obj, Object value) {
        Object fieldValue = Reflections.getFieldValue(field, obj);

        if (fieldValue == null) {
            fieldValue = Reflections.createInstance(field.getType());
        }

        if (grabber != null) {
            Reflections.setFieldValue(field, obj, fieldValue);
            grabber.setValue(fieldValue, value);
        } else {
            Reflections.setFieldValue(field, obj, value);
        }
    }

    public Class getType() {
        return grabber == null? field.getType() : grabber.getType();
    }
}
