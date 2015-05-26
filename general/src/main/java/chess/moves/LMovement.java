package chess.moves;

import chess.Board;
import chess.Position;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class LMovement implements IMovement {

    @Override
    public List<Position> searchMovements(Board board, Position pos) {
        List<Position> list = new ArrayList<>();

        for (int i=-2; i<=2; i++) {
            for (int j=-2; j<=2; j++) {
                Position inc = pos.increment(i, j);
                if (board.isValid(inc) && (Math.abs(i) != Math.abs(j)) && (i != 0 && j != 0)) {
                    list.add(inc);
                }
            }
        }

        return list;
    }
}
