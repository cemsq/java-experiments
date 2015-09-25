package com.cgm.storm.utils.properties.adapter;

import com.cgm.storm.utils.properties.Globals;
import com.cgm.storm.utils.properties.PropertyCategory;
import com.cgm.storm.utils.properties.PropertyItem;
import com.cgm.storm.utils.properties.adapter.exception.PropertyAdapterException;
import com.google.common.base.Preconditions;

import java.lang.reflect.Field;

/**
 *
 */
class AdapterItem {
    public Field field;
    public String name;
    public String desc;

    // the current field belongs to a composite object
    public Field fieldOwner;

    public AdapterItem() {

    }

    public PropertyItem createPropertyItem(Object obj, Class clazz) {
        Preconditions.checkNotNull(clazz, "Class should be not null");

        Object objectOwner = getObjectOwner(obj);
        if (objectOwner == null) {
            Class cc = getClassOwner(clazz);
            String className = Globals.getName(cc);
            throw new PropertyAdapterException("'" + this.field.getName() + "' member in '" + className + "' is null");
        }

        PropertyItem item = new PropertyItem();
        updatePropertyItem(item, objectOwner, field, name, desc);

        return item;
    }

    public void updatePropertyItem(PropertyCategory category, Object obj, Class clazz) {
        Object objectOwner = getObjectOwner(obj);
        if (objectOwner == null) {
            Class cc = getClassOwner(clazz);
            String className = Globals.getName(cc);
            throw new PropertyAdapterException("'" + this.field.getName() + "' member in '" + className + "' is null");
        }

        PropertyItem item = category.getItemByFieldName(this.field.getName());
        if (item == null) {
            throw new PropertyAdapterException("Item: '" + this.field.getName() + "' in Category '" + category.getName() + "' not found");
        }

        updatePropertyItem(item, objectOwner, field, name, desc);
    }

    public Object getObjectOwner(Object obj) {
        Preconditions.checkNotNull(obj);

        Object objectOwner = obj;
        if (fieldOwner != null) {
            fieldOwner.setAccessible(true);
            try {
                objectOwner = fieldOwner.get(obj);
            } catch (IllegalAccessException e) {
                throw new PropertyAdapterException("this exception should never happen!");
            }
        }
        return objectOwner;
    }

    public Class getClassOwner(Class clazz) {
        Preconditions.checkNotNull(clazz, "Null clazz");

        return fieldOwner == null ? clazz : fieldOwner.getType();
    }

    private static void updatePropertyItem(PropertyItem item, Object obj, Field field, String guiName, String desc) {
        try {
            field.setAccessible(true);
            item.setFieldName(field.getName());
            item.setType(Globals.getStringAfterLastPoint(field.getType().getName()));
            item.setName(guiName);
            item.setDescription(desc);
            item.setValue(field.get(obj).toString());
        } catch (IllegalAccessException e) {
            throw new PropertyAdapterException("this Exception should never happen!", e);
        }
    }


    @Override
    public String toString() {
        return " AdapterItem{\n" +
                "    field = '" + field + "'\n" +
                "    name = '" + name + "\'\n" +
                "    desc = '" + desc + "\'\n" +
                " }\n";
    }
}
