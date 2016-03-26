package cem.rules.test;

import java.util.List;

public class OrgUnit {

    private Integer id;
    private Type type;



    public OrgUnit(Integer id, Type type) {
        this.id = id;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    public List<Characteristic> getCharacteristics() {
        return type.getCharacteristics();
    }
}
