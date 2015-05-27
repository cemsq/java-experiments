package chess;

import chess.moves.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

/**
 *
 */
public class IMovementTest {

    @Test(dataProvider = "movement-provider")
    public void testingMovements(IMovement movement, Board board, Position pos, int positionQuantity) {
        System.out.println("Testing " + movement.getName() + ". Should have " + positionQuantity + " positions");
        List<Position> list = movement.searchMovements(board, pos);

        Assert.assertEquals(list.size(), positionQuantity, "Movements created not match in: " + movement.getName());
    }

    @DataProvider(name = "movement-provider")
    public Object[][] movementProvider() {
        Board board = new Board();
        Position pos = new Position(0, 0);

        return new Object[][]{
                {new Cross(), board, pos, 14},
                {new Diagonal(), board, pos, 7},
                {new LMovement(), board, pos, 2},
                {new Star(), board, pos, 21}
        };
    }
}
