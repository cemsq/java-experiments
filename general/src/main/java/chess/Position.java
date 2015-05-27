package chess;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Position {
    private int i;
    private int j;

    public static final Position UP = new Position(-1, 0);
    public static final Position DOWN = new Position(1, 0);
    public static final Position RIGHT = new Position(0, 1);
    public static final Position LEFT = new Position(0, -1);

    public static final Position UP_LEFT = new Position(UP.getI(), LEFT.getJ());
    public static final Position UP_RIGHT = new Position(UP.getI(), RIGHT.getJ());
    public static final Position DOWN_LEFT = new Position(DOWN.getI(), LEFT.getJ());
    public static final Position DOWN_RIGHT = new Position(DOWN.getI(), RIGHT.getJ());


    public Position(int i, int j) {
        setPosition(i, j);
    }

    public Position(Position pos) {
        setPosition(pos);
    }

    public void setPosition(Position pos) {
        setPosition(pos.i, pos.j);
    }

    public void setPosition(int i, int j) {
        setI(i);
        setJ(j);
    }

    public static Position increment(Position a, Position b) {
        return a.increment(b);
    }

    public Position increment(Position pos) {
        return this.increment(pos.i, pos.j);
    }

    public Position increment(int i, int j) {
        return new Position(this.i + i, this.j + j);
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", i, j);
    }
}
