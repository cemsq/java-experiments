package core;

import com.google.common.collect.Lists;

import java.util.List;

public class Board {

    private int width;
    private int height;
    private List<List<Tile>> tiles;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;

        init();
    }

    private void init() {
        tiles = Lists.newArrayList();
        for (int i=0; i < width; i++) {
            List<Tile> list = Lists.newArrayList();
            for (int k=0; k<height; k++) {
                list.add(new Tile());
            }
            tiles.add(list);
        }
    }

    public Tile getTile(int x, int y) {
        return tiles.get(x).get(y);
    }

    public void addUnit(Unit unit, int x, int y) {
        getTile(x, y).addUnit(unit);
    }

    public void removeUnit(Unit unit, int x, int y) {
        getTile(x, y).removeUnit(unit);
    }

    public void removeUnits(int x, int y) {
        getTile(x, y).removeUnits();
    }

    public List<Unit> getUnits(int x, int y) {
        return getTile(x, y).getUnits();
    }
}
