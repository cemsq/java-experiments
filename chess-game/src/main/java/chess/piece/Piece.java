package chess.piece;

import chess.Board;
import chess.Position;
import chess.moves.IMovement;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public abstract class Piece implements IMovement{
    List<IMovement> movements = new ArrayList<>();

    public Piece(IMovement movement, IMovement ...movements) {
        addMovement(movement);

        for (IMovement move : movements) {
            addMovement(move);
        }
    }

    public boolean move(Board board, Position initial, Position destiny) {
        List<Position> positions = searchMovements(board, initial);

        return positions.contains(destiny);
    }

    @Override
    public List<Position> searchMovements(Board board, Position initial) {
        List<Position> positions = new ArrayList<>();
        for (IMovement movement : movements) {
            positions.addAll(movement.searchMovements(board, initial));
        }
        return positions;
    }

    public void addMovement(IMovement move) {
        if (move != null) {
            this.movements.add(move);
        }
    }
}
