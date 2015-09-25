package com.cgm.storm.utils.properties;

import com.google.common.base.Strings;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class PropertyCategory {

    private String name;

    private List<PropertyItem> itemList = new ArrayList<>();

    public PropertyCategory() {

    }

    public PropertyCategory(String name) {
        this.name = name;
    }

    public PropertyItem addItem(PropertyItem item) {
        if (item != null) {
            itemList.add(item);
        }
        return item;
    }

    public PropertyItem getItemByFieldName(String name) {
        if (Strings.isNullOrEmpty(name)) {
            return null;
        }

        for (PropertyItem item : itemList) {
            if (name.equals(item.getFieldName())) {
                return item;
            }
        }

        return null;
    }

    public List<PropertyItem> getItemList() {
        return itemList;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "+ Category: " + name + " {\n" +
                getItemsAsString() +
                "}\n";
    }

    private String getItemsAsString() {
        StringBuilder sb = new StringBuilder();
        for (PropertyItem item : itemList) {
            sb.append(item);
        }

        return sb.toString();
    }
}
