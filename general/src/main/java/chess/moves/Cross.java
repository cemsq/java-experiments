package chess.moves;

import chess.Board;
import chess.Position;


import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Cross implements IMovement {

    @Override
    public List<Position> searchMovements(Board board, Position pos) {
        List<Position> list = new ArrayList<>();

        // up
        list.addAll(board.searchByIncrement(pos, Position.UP));
        // down
        list.addAll(board.searchByIncrement(pos, Position.DOWN));
        // right
        list.addAll(board.searchByIncrement(pos, Position.RIGHT));
        // left
        list.addAll(board.searchByIncrement(pos, Position.LEFT));

        return list;
    }

    @Override
    public String getName() {
        return "Cross";
    }
}
