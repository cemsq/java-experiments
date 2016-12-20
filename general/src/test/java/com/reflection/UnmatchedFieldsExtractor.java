package com.reflection;

import com.google.common.base.*;
import com.google.common.base.Objects;
import com.reflection.model.Item;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Field;
import java.util.*;

/**
 *
 */
public class UnmatchedFieldsExtractor {

    @Test
    public void comparingObjects() throws IllegalAccessException {
        Item old = new Item();
        old.setText("hello storm");
        old.setCode("123");
        old.setName("test");
        old.setNumber(1);

        Item newObj = new Item();
        newObj.setText("hello storm");
        newObj.setCode("123");
        newObj.setName("new");
        newObj.setNumber(2);

        List<String> unmatchedFields = getUnmatchedFields(old, newObj);

        Assert.assertNotNull(unmatchedFields, "null object");
        Assert.assertEquals(unmatchedFields.size(), 2, "wrong list");
    }

    // return a List<ChangeEntry>
    public static <T>List<String> getUnmatchedFields(T oldObject, T newObject) throws IllegalAccessException {
        List<String> list = new ArrayList<>();

        List<Field> fields = extractFields(oldObject.getClass());
        for (Field field : fields) {
            boolean accessible = field.isAccessible();
            field.setAccessible(true);

            Object oldValue = field.get(oldObject);
            Object newValue = field.get(newObject);

            if (!Objects.equal(oldValue, newValue)) {
                // create a ChangeEntry
                list.add(field.getName());
            }

            field.setAccessible(accessible);
        }

        return list;
    }

    public static List<Field> extractFields(Class clazz) {
        List<Field> fields = new ArrayList<>();

        Field[] f1 = clazz.getDeclaredFields();
        Collections.addAll(fields, f1);

        Class superclass = clazz.getSuperclass();
        if (!superclass.getName().equals(Object.class.getName())) {
            f1 = superclass.getDeclaredFields();

            Collections.addAll(fields, f1);
        }

        return fields;
    }
}
