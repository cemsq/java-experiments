package chess;

import chess.piece.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 */
public class PieceMovementTest extends IMovementTest{
    private static final Logger log = LoggerFactory.getLogger(IMovementTest.class);

    private Position position_0_0 = new Position(0, 0);
    private Position position_3_3 = new Position(3, 3);
    private Position position_5_6 = new Position(5, 6);

    private Board board = Board.emptyChessBoard();

    @Test(dataProvider = "piece-provider", dependsOnMethods = "testingMovements")
    public void movePiece(Board board, Piece piece, Position initial, Position destiny, boolean moved) {
        Assert.assertEquals(piece.move(board, initial, destiny), moved, "invalid movement from: " + initial + " to: " + destiny + ". Piece: " + piece.getName());
    }

    @DataProvider(name = "movement-provider")
    public Object[][] movementProvider() {

        return new Object[][]{
                {new Tower(), position_0_0, 14},
                {new Bishop(), position_0_0, 7},
                {new Horse(), position_0_0, 2},
                {new Queen(), position_0_0, 21}
        };
    }

    @DataProvider(name = "piece-provider")
    public Object[][] pieceProvider() {

        return new Object[][] {
                {board, new Tower(), position_0_0, new Position(0, 7), true},
                {board, new Tower(), position_0_0, new Position(7, 0), true},
                {board, new Tower(), position_0_0, new Position(1, 1), false},

                {board, new Bishop(), position_0_0, new Position(1, 1), true},
                {board, new Bishop(), position_0_0, new Position(7, 7), true},
                {board, new Bishop(), position_0_0, new Position(0, 0), false},

                {board, new Horse(), position_0_0, new Position(2, 1), true},
                {board, new Horse(), position_0_0, new Position(1, 2), true},
                {board, new Horse(), position_0_0, new Position(1, 1), false},

                {board, new Queen(), position_0_0, new Position(0, 7), true},
                {board, new Queen(), position_0_0, new Position(7, 0), true},
                {board, new Queen(), position_0_0, new Position(7, 7), true},
                {board, new Queen(), position_0_0, new Position(2, 1), false}
        };
    }
}
