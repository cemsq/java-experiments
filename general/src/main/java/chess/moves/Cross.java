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
        list.addAll(board.searchByIncrement(pos, -1, 0));
        // down
        list.addAll(board.searchByIncrement(pos, 1, 0));
        // right
        list.addAll(board.searchByIncrement(pos, 0, 1));
        // left
        list.addAll(board.searchByIncrement(pos, 0, -1));

        return list;
    }
}
