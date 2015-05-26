package chess;

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
}
