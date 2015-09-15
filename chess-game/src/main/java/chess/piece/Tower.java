package chess.piece;

import chess.moves.Movements;

/**
 *
 */
public class Tower extends Piece {

    public Tower() {
        super(Movements.cross());
    }

    @Override
    public String getName() {
        return "Tower";
    }
}
