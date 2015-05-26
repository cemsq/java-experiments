package chess.moves;

import chess.Board;
import chess.Position;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Diagonal implements IMovement {

    @Override
    public List<Position> searchMovements(Board board, Position pos) {
        List<Position> list = new ArrayList<>();

        // up - left
        list.addAll(Position.scan(board, pos, -1, -1));
        // up - right
        list.addAll(Position.scan(board, pos, -1, 1));
        // down - right
        list.addAll(Position.scan(board, pos, 1, 1));
        // down - left
        list.addAll(Position.scan(board, pos, 1, -1));

        return list;
    }
}
