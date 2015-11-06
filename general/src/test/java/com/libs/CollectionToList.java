package com.libs;

import exp.amodel.Person;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class CollectionToList {
    @Test
    public void convertingCollectionToArrayList() {
        Map<String, Person> persons = new HashMap<>();

        persons.put("1", new Person("Carl"));
        persons.put("2", new Person("John"));
        persons.put("3", new Person("Doe"));
        persons.put("3", new Person("nothing"));

        Assert.assertEquals(persons.size(), 3);

        List<Person> list = new ArrayList<>(persons.values());
        Assert.assertEquals(list.size(), 3);

        Person p = list.get(0);
        Assert.assertEquals(persons.containsValue(p), true);
    }
}
