package cem.rules.api;

import java.util.ArrayList;
import java.util.List;

public class OrgUnitExtension {

    private List<String> characteristics = new ArrayList<>();

    public OrgUnitExtension() {
    }

    public OrgUnitExtension add(String str) {
        characteristics.add(str);

        return this;
    }

    public List<String> getCharacteristics() {
        return characteristics;
    }
}
