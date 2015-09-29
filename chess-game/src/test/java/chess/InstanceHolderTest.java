package chess;

import chess.moves.Movements;
import chess.piece.Bishop;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 */
public class InstanceHolderTest {

    @Test(enabled = false)
    public void test() {
        Movements.king();
        Movements.king();
        Movements.king();

        Movements.horse();
        Movements.horse();

        Assert.assertEquals(InstanceHolder.instancesNumber(), 2);
    }
}

