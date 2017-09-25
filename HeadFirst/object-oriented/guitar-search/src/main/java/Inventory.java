import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Inventory {
    private List<Guitar> guitars;

    public Inventory() {
        guitars = new ArrayList<>();
    }

    public Inventory addGuitar(Guitar guitar) {
        guitars.add(guitar);

        return this;
    }

    public List<Guitar> getGuitars() {
        return guitars;
    }
}
