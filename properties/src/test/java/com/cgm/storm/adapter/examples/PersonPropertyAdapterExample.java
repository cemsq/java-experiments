package com.cgm.storm.adapter.examples;

import com.cgm.storm.utils.properties.adapter.PropertyAdapter;

/**
 *
 */
public class PersonPropertyAdapterExample extends PropertyAdapter<PersonTest> {
    public PersonPropertyAdapterExample() {
        super(PersonTest.class, "Person Property Adapter");

        String category = "Personal Data";
        this.defineProperty(category, "firstName", "Vorname", "first name description");
        this.defineProperty(category, "lastName", "Nachname", "last name description");
        this.defineProperty(category, "age", "Age", "age description");
        this.defineProperty(category, "money", "the Money", "money description");
    }
}
