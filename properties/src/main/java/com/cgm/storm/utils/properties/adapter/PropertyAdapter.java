package com.cgm.storm.utils.properties.adapter;

import com.cgm.storm.utils.properties.Globals;
import com.cgm.storm.utils.properties.PropertyCategory;
import com.cgm.storm.utils.properties.PropertyItem;
import com.cgm.storm.utils.properties.PropertySet;
import com.cgm.storm.utils.properties.adapter.exception.PropertyAdapterException;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


/**
 * Adapter class between an Entity and a PropertySet
 *
 * @param <T> an Entity type, for Example: Person
 */
public class PropertyAdapter<T> {

    private final String info;
    private final Class clazz;
    private final List<AdapterCategory> catList = new ArrayList<>();
    // ------------------------

    public PropertyAdapter(Class<T> clazz, String info) {
        this.clazz = clazz;
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void defineProperty(String category, String field, String guiName, String desc) {
        defineProperty(category, "", field, guiName, desc);
    }

    public void defineProperty(String categoryName, String fieldOwner, String fieldName, String guiName, String desc) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(categoryName), "category should be not empty or not null");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(fieldName), "fieldOwner should be not empty or not null");

        AdapterCategory category = createAdapterCategory(categoryName);
        AdapterItem item = new AdapterItem();
        item.name = guiName;
        item.desc = desc;

        Class clazz = this.clazz;
        if (Globals.isValidString(fieldOwner)) {
            item.fieldOwner = getField(clazz, fieldOwner);
            clazz = item.fieldOwner.getType();
        }
        item.field = getField(clazz, fieldName);

        category.addItem(item);
        addAdapterCategory(category);
    }

    private AdapterCategory createAdapterCategory(String name) {
        AdapterCategory category = searchAdapterCategory(name);

        return category == null ? new AdapterCategory(name) : category;
    }

    private AdapterCategory searchAdapterCategory(String name) {
        for (AdapterCategory cat : catList) {
            if (cat.name.equals(name)) {
                return cat;
            }
        }
        return null;
    }

    private void addAdapterCategory(AdapterCategory category) {
        AdapterCategory cat = searchAdapterCategory(category.name);
        if (cat == null) {
            catList.add(category);
        }
    }

    private static Field getField(Class clazz, String fieldName) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(fieldName), "fieldName should be not empty or not null");
        Preconditions.checkNotNull(clazz, "clazz should be not null");

        String className = Globals.getName(clazz);
        Field field = null;
        while (clazz != null && field == null) {
            try {
                field = clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            }
        }
        if (field == null) {
            throw new PropertyAdapterException("Illegal field in '" + className + "': " + fieldName);
        }

        return field;
    }


    public PropertySet createPropertySet(T obj) {
        Preconditions.checkNotNull(obj, "null '" + Globals.getName(clazz) + "' Object");
        Preconditions.checkState(catList.size() > 0, "PropertyAdapter without definitions");

        PropertySet properties = new PropertySet();
        properties.setInfo(info);

        for (AdapterCategory cat : catList) {
            PropertyCategory category = cat.createPropertyCategory(obj, clazz);
            properties.addCategory(category);
        }

        return properties;
    }

    public void updatePropertySet(PropertySet ps, T obj) {
        Preconditions.checkNotNull(ps, "PropertySet should be not null");
        Preconditions.checkNotNull(obj, "obj should be not null");

        for (AdapterCategory cat : catList) {
            cat.updatePropertyCategory(ps, obj, clazz);
        }
    }

    public void storePropertiesToObject(PropertySet ps, T obj) {
        Preconditions.checkNotNull(ps, "PropertySet should be not null");
        Preconditions.checkNotNull(obj, "obj should be not null");

        String msg = "PropertySet not match with the definition.";

        for (AdapterCategory adapterCategory : catList) {
            PropertyCategory category = ps.getCategory(adapterCategory.name);
            if (category == null) {
                throw new PropertyAdapterException(msg + " Category: '" + adapterCategory.name + "' in PropertySet not found");
            }

            for (AdapterItem adapterItem : adapterCategory.list) {
                String itemName = adapterItem.field.getName();

                PropertyItem propertyItem = category.getItemByFieldName(itemName);
                if (propertyItem == null) {
                    throw new PropertyAdapterException(msg + " Item: '" + itemName + "' is null or not found");
                }

                setValue(obj, adapterItem, propertyItem);
            }

        }
    }

    private void setValue(T obj, AdapterItem adapterItem, PropertyItem propertyItem) {
        Class clazzOwner = adapterItem.getClassOwner(this.clazz);
        Object objectOwner = adapterItem.getObjectOwner(obj);

        Class type = adapterItem.field.getType();
        String typeName = Globals.getStringAfterLastPoint(type.getName());
        try {
            if (typeName.equals(propertyItem.getType())) {
                Object value = TypeConverter.getRealValueAsObject(type, propertyItem.getValue());
                adapterItem.field.setAccessible(true);
                adapterItem.field.set(objectOwner, value);
            }
        } catch (IllegalAccessException e) {
            throw new PropertyAdapterException("this Exception should never happen!!!", e);
        } catch (NullPointerException e) {
            System.out.println(e.toString() + ": in '" + clazzOwner.getName() + "'.'" + adapterItem.field.getName() + "' member");
        }
    }

    @Override
    public String toString() {
        return "PropertyAdapter{" +
                "info='" + info + '\'' + "\n" +
                ", catList=\n" + catList + "\n" +
                '}';
    }

    public String getDefinitionAsString() {
        StringBuilder sb = new StringBuilder("PropertyAdapter definition:\n" +
                "  info = " + info + "\n" +
                "  categoryList:\n");
        for (AdapterCategory cat : catList) {
            sb.append("     ")
                    .append(cat.name)
                    .append("\n");
            for (AdapterItem item : cat.list) {
                sb.append("         ")
                        .append(item.field.getType().getName())
                        .append("  ")
                        .append(item.field.getName())
                        .append("\n");
            }
        }

        return sb.toString();
    }
}


