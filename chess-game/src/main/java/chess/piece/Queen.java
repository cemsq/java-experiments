package chess.piece;

import chess.moves.Movements;

/**
 *
 */
public class Queen extends Piece{

    public Queen() {
        super(Movements.diagonal(), Movements.cross());
    }

    @Override
    public String getName() {
        return "Queen";
    }
}
