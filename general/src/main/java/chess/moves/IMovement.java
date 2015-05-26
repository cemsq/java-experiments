package chess.moves;

import chess.Board;
import chess.Position;

import java.util.List;

/**
 *
 */
public interface IMovement {

    public List<Position> searchMovements(Board board, Position pos);

    public String getName();
}
