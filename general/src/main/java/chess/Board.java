package chess;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Board {

    public boolean isValid(Position pos) {
        return isValid(pos.getI(), pos.getJ());
    }

    public boolean isValid(int i, int j) {
        return i>=0 && i<8 && j>=0 && j<8;
    }

    public boolean isEmpty(int i, int j) {
        return true;
    }

    public List<Position> searchByIncrement(Position pos, int incI, int incJ) {
        return searchByIncrement(pos, new Position(incI, incJ));
    }

    public List<Position> searchByIncrement(Position pos, Position incr) {
        List<Position> list = new ArrayList<>();
        Position iter = pos.increment(incr);

        while (isValid(iter)) {
            list.add(new Position(iter));
            iter = iter.increment(incr);
        }

        return list;
    }
}
