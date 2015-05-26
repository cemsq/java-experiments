package chess;

import chess.moves.Cross;
import chess.moves.Diagonal;
import chess.moves.IMovement;
import chess.moves.LMovement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

/**
 *
 */
public class IMovementTest {

    @Test(dataProvider = "movement-provider")
    public void testingMovements(IMovement movement, String desc) {
        System.out.println("Testing: " + desc);
        List<Position> list = movement.searchMovements(new Board(), new Position(0, 0));
        for (Position pos : list) {
            System.out.println(pos);
        }
    }

    @DataProvider(name = "movement-provider")
    public Object[][] movementProvider() {
        return new Object[][]{
                {new Cross(), "Cross"},
                {new Diagonal(), "Diagonal"},
                {new Cross(), "LMovement"}
        };
    }
}
