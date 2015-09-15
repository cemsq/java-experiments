package chess;

/**
 *
 */
public class Position {
    private int i;
    private int j;

    public Position(Position pos) {
        this(pos.i, pos.j);
    }

    public Position(int i, int j) {
        this.i = i;
        this.j = j;
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

    public int getJ() {
        return j;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Position)) {
            return false;
        }

        Position position = (Position) o;

        if (i != position.i) {
            return false;
        }
        if (j != position.j) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = i;
        result = 31 * result + j;
        return result;
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", i, j);
    }
}
