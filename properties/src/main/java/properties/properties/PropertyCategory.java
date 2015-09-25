package properties.properties;

import properties.exception.PropertySetException;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class PropertyCategory {

    private String name;
    private List<PropertyItem> items = new ArrayList<>();

    public PropertyCategory(String name) {
        this.name = name;
    }

    public PropertyCategory addItem(PropertyItem item) {
        if (!items.contains(item)) {
            items.add(item);
        }

        return this;
    }

    public String getName() {
        return name;
    }

    public List<PropertyItem> getItems() {
        return items;
    }

    public PropertyItem getItem(String name) {
        for (PropertyItem item : items) {
            if (item.getFieldName().equals(name)) {
                return item;
            }
        }
        throw new PropertySetException("Item not found: " + name + " in Category: " + this.name);
    }
}
