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
        list.addAll(Position.scan(board, pos, -1, 0));
        // down
        list.addAll(Position.scan(board, pos, 1, 0));
        // right
        list.addAll(Position.scan(board, pos, 0, 1));
        // left
        list.addAll(Position.scan(board, pos, 0, -1));

        return list;
    }
}
