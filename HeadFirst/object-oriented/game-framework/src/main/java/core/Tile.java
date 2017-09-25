package core;

import com.google.common.collect.Lists;

import java.util.List;

public class Tile {

    List<Unit> units = Lists.newArrayList();

    public Tile() {

    }

    public void addUnit(Unit unit) {
        units.add(unit);
    }

    public void removeUnit(Unit unit) {

    }

    public List<Unit> getUnits() {
        return units;
    }

    public void removeUnits() {

    }
}
