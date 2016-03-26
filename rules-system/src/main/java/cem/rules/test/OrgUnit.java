package cem.rules.test;

import java.util.List;

public class OrgUnit {

    private Type type;

    public OrgUnit(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public List<Characteristic> getCharacteristics() {
        return type.getCharacteristics();
    }
}
