package chess;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 */
public class BoardTest {

    @Test
    public void test() {
        Board board = Board.emptyChessBoard();

        Assert.assertTrue(board.isEmpty(0,0));
    }
}
