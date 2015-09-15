package chess.piece;

import chess.moves.Movements;

/**
 *
 */
public class Bishop extends Piece {

    public Bishop() {
        super(Movements.diagonal());
    }

    @Override
    public String getName() {
        return "Bishop";
    }
}
