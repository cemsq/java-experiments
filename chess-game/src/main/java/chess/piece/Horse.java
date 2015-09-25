package chess.piece;

import chess.moves.Movements;

/**
 *
 */
public class Horse extends Piece {

    public Horse() {
        super(Movements.horse());
    }

    @Override
    public String getName() {
        return "Horse";
    }
}
