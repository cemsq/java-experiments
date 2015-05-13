import org.testng.annotations.Test;

/**
 *
 */
public class DirectoryScannerTest {

    @Test
    public void test() {
        new DirectoryScanner().analyze("dir-walker/src/main/resources/templates", new DirectoryWalker());
    }
}
