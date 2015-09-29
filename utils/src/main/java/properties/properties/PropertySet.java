package properties.properties;

import properties.exception.PropertySetException;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class PropertySet {

    private String name;
    private List<PropertyCategory> categories = new ArrayList<>();

    public PropertySet(String name) {
        this.name = name;
    }

    public PropertySet addCategory(PropertyCategory category) {
        if (!categories.contains(category)) {
            categories.add(category);
        }

        return this;
    }

    public PropertyCategory getCategory(String name) {
        for (PropertyCategory category : categories) {
            if (category.getName().equals(name)) {
                return category;
            }
        }
        throw new PropertySetException("Category not found: " + name + " in PropertySet: " + this.name);
    }

    public String getName() {
        return name;
    }

    public List<PropertyCategory> getCategories() {
        return categories;
    }
}
