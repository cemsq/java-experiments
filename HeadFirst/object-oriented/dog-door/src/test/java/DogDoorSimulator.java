import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 *
 */
public class DogDoorSimulator {

    @Test
    public static void normal() {
        DogDoor door = new DogDoor();
        Remote remote = new Remote(door);

        System.out.println("Fido barks to go outside... ");
        remote.pressButton();

        System.out.println("\nFido has gone outside... ");

        System.out.println("\nFido's all done...");

        System.out.println("\nFido's back inside... ");
    }

    @AfterMethod
    public void afterMethod() {
        try {
            Thread.sleep(4000);
            System.out.printf("finished");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
