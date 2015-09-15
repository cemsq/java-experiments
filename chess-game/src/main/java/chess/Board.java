package chess;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Board {

    private Cell[][] cells;
    private int n;
    private int m;

    public Board(int m, int n) {
        this.n = n;
        this.m = m;

        cells = new Cell[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    public static Board emptyChessBoard() {
        return new Board(8, 8);
    }

    public boolean isValid(Position pos) {
        return isValid(pos.getI(), pos.getJ());
    }

    public boolean isValid(int i, int j) {
        return isInRange(i, j);
    }

    public boolean isInRange(Position pos) {
        return isInRange(pos.getI(), pos.getJ());
    }

    public boolean isInRange(int i, int j) {
        return i>=0 && i<m && j>=0 && j<n;
    }

    public boolean isEmpty(int i, int j) {
        return cells[i][j].isEmpty();
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
