package com.cgm.storm.utils.properties.adapter;

import com.cgm.storm.utils.properties.PropertyCategory;
import com.cgm.storm.utils.properties.PropertyItem;
import com.cgm.storm.utils.properties.PropertySet;
import com.cgm.storm.utils.properties.adapter.exception.PropertyAdapterException;
import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by César Mora on 14.10.2014.
 */
class AdapterCategory {
    public final String name;
    public final List<AdapterItem> list = new ArrayList<>();

    AdapterCategory(String name) {
        this.name = name;
    }

    void addItem(AdapterItem item) {
        list.add(item);
    }

    PropertyCategory createPropertyCategory(Object obj, Class clazz) {
        Preconditions.checkNotNull(obj, "Object should be not null");

        PropertyCategory category = new PropertyCategory(this.name);
        for (AdapterItem ai : this.list) {
            PropertyItem item = ai.createPropertyItem(obj, clazz);
            category.addItem(item);
        }

        return category;
    }

    void updatePropertyCategory(PropertySet properties, Object obj, Class clazz) {
        PropertyCategory category = properties.getCategory(this.name);
        if (category == null) {
            throw new PropertyAdapterException("Category: '" + this.name + "' in PropertySet not found");
        }

        for (AdapterItem ai : this.list) {
            ai.updatePropertyItem(category, obj, clazz);
        }
    }

    @Override
    public String toString() {
        return "AdapterCategory{" +
                "name='" + name + '\'' + "\n" +
                ", list=\n" + list +
                '}';
    }
}
