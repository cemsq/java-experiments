package chess;

import chess.moves.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

/**
 *
 */
public class IMovementTest {
    private static final Logger log = LoggerFactory.getLogger(IMovementTest.class);

    @Test(dataProvider = "movement-provider")
    public void testingMovements(IMovement movement, Position position, int size) {
        log.info(" Position: {}, size: {}, Testing: {}", position, size, movement.getName());
        List<Position> list = movement.searchMovements(Board.emptyChessBoard(), position);

        Assert.assertEquals(list.size(), size, movement.getName() + " list.size not match");
    }

    @DataProvider(name = "movement-provider")
    public Object[][] movementProvider() {
        Position position_0_0 = new Position(0, 0);
        Position position_3_3 = new Position(3, 3);
        Position position_5_7 = new Position(5, 7);

        return new Object[][]{
                {Movements.cross(), position_0_0, 14},
                {Movements.diagonal(), position_0_0, 7},
                {Movements.horse(), position_0_0, 2},
                {Movements.king(), position_0_0, 3},

                {Movements.cross(), position_3_3, 14},
                {Movements.diagonal(), position_3_3, 13},
                {Movements.horse(), position_3_3, 8},
                {Movements.king(), position_3_3, 8},

                {Movements.cross(), position_5_7, 14},
                {Movements.diagonal(), position_5_7, 7},
                {Movements.horse(), position_5_7, 4},
                {Movements.king(), position_5_7, 5}
        };
    }
}
