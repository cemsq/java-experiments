package properties;

import properties.grabber.FieldGrabber;
import properties.properties.PropertyCategory;
import properties.properties.PropertyItem;
import properties.properties.PropertySet;

import java.util.*;

/**
 *
 */
public class PropertyAdapter<T> {

    private String info;
    private Class<T> clazz;
    private Map<String, List<Definition>> definitions = new HashMap<>();
    private List<String> categories = new ArrayList<>();

    private class Definition {
        String fieldName;
        String display;
        boolean mandatory;
        boolean editable;

        FieldGrabber grabber;

        public Definition(Class clazz, String fieldName, String display, boolean mandatory, boolean editable) {
            this.fieldName = fieldName;
            this.display = display;
            this.mandatory = mandatory;
            this.editable = editable;
            grabber = new FieldGrabber(clazz, fieldName);
        }
    }

    public PropertyAdapter(Class<T> clazz, String info) {
        this.clazz = clazz;
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public PropertyAdapter defineProperty(String category, String field, String display, boolean mandatory, boolean editable) {
        List<Definition> defs = definitions.get(category);
        if (defs == null) {
            defs = new ArrayList<>();
            definitions.put(category, defs);
            categories.add(category);
        }
        defs.add(new Definition(clazz, field, display, mandatory, editable));

        return this;
    }

    public void storePropertiesToObject(PropertySet properties, T obj) {
        update(properties, obj, false);
    }

    public void updateProperties(PropertySet properties, T obj) {
        update(properties, obj, true);
    }

    private void update(PropertySet properties, T obj, boolean fromObject) {
        for (String categoryName : categories) {
            PropertyCategory category = properties.getCategory(categoryName);
            for (Definition def : definitions.get(categoryName)) {
                PropertyItem item = category.getItem(def.fieldName);

                // write value from obj to Item
                if (fromObject) {
                    item.setValue(def.grabber.getValue(obj));
                } else {
                    // write value from Item to obj
                    def.grabber.setValue(obj, item.getActualValue());
                }
            }
        }
    }

    public PropertySet createPropertySet(T obj) {
        PropertySet properties = new PropertySet(info);
        for (String categoryName : categories) {
            PropertyCategory category = new PropertyCategory(categoryName);
            properties.addCategory(category);

            for (Definition def : definitions.get(categoryName)) {
                PropertyItem item = new PropertyItem(
                        def.fieldName,
                        def.display,
                        def.grabber.getValue(obj),
                        def.mandatory,
                        def.editable);

                category.addItem(item);
            }
        }

        return properties;
    }

}
