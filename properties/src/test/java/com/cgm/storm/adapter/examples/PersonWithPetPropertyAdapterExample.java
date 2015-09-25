package com.cgm.storm.adapter.examples;

import com.cgm.storm.utils.properties.adapter.PropertyAdapter;

/**
 *
 */
public class PersonWithPetPropertyAdapterExample extends PropertyAdapter<PersonTest> {
    public PersonWithPetPropertyAdapterExample() {
        super(PersonTest.class, "Person Property Adapter");

        String category = "Personal Data";
        this.defineProperty(category, "firstName", "Vorname", "first name description");
        this.defineProperty(category, "lastName", "Nachname", "last name description");
        this.defineProperty(category, "age", "Age", "age description");
        this.defineProperty(category, "money", "the Money", "money description");

        category = "Pet Info";
        this.defineProperty(category, "pet", "name", "pet name", "pet name description");
    }
}
