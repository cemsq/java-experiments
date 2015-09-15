package chess.piece;

import chess.moves.Movements;

/**
 *
 */
public class King extends Piece {
    public King() {
        super(Movements.king());
    }

    @Override
    public String getName() {
        return "King";
    }
}
