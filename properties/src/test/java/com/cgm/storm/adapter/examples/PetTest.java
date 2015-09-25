package com.cgm.storm.adapter.examples;

/**
 *
 */
public class PetTest {

    private String id;

    private String name;

    private String idPerson;

    public PetTest(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(String idPerson) {
        this.idPerson = idPerson;
    }

}
