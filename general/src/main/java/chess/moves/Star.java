package chess.moves;

import chess.Board;
import chess.Position;

import java.util.List;

/**
 *
 */
public class Star implements IMovement {

    private static final Cross CROSS = new Cross();
    private static final Diagonal DIAGONAL = new Diagonal();

    @Override
    public List<Position> searchMovements(Board board, Position pos) {
        List<Position> list = CROSS.searchMovements(board, pos);

        list.addAll(DIAGONAL.searchMovements(board, pos));

        return list;
    }

    @Override
    public String getName() {
        return "Star";
    }
}
