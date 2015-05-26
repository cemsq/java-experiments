package chess;

import chess.moves.Cross;
import chess.moves.Diagonal;
import chess.moves.IMovement;
import chess.moves.LMovement;
import org.testng.annotations.Test;

import java.util.List;

/**
 *
 */
public class IMovementTest {

    @Test
    public void testingCross() {
        IMovement cross = new Cross();
        List<Position> list = cross.searchMovements(new Board(), new Position(0, 0));
        for (Position pos : list) {
            System.out.println(pos);
        }
    }

    @Test
    public void testingDiagonal() {
        IMovement cross = new Diagonal();
        List<Position> list = cross.searchMovements(new Board(), new Position(0, 0));
        for (Position pos : list) {
            System.out.println(pos);
        }
    }

    @Test
    public void testingLMovement() {
        IMovement cross = new LMovement();
        List<Position> list = cross.searchMovements(new Board(), new Position(0, 0));
        for (Position pos : list) {
            System.out.println(pos);
        }
    }
}
