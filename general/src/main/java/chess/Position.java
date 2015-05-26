package chess;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Position {
    private int i;
    private int j;

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

    public Position increment(Position pos) {
        return this.increment(pos.i, pos.j);
    }

    public Position increment(int i, int j) {
        //this.setPosition(this.i + i, this.j + j);
        //return this;
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

    public static List<Position> scan(Board board, Position pos, int incI, int incJ) {
        List<Position> list = new ArrayList<>();
        Position incr = new Position(incI, incJ);
        Position iter = pos.increment(incr);

        while (board.isValid(iter)) {
            list.add(new Position(iter));
            iter = iter.increment(incr);
        }

        return list;
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", i, j);
    }
}
