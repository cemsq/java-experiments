import org.testng.annotations.Test;

import java.util.List;

/**
 *
 */
public class GuitarTest {

    @Test
    public void findGuitarsBySpec() {
        Inventory inventory = createInventory();
        GuitarSpec spec = new GuitarSpec(Builder.IBANEZ, Type.ELECTRIC, Wood.MAPLE, Wood.MAPLE);

        List<Guitar> list = spec.search(inventory.getGuitars());

        for (Guitar guitar : list) {
            GuitarSpec gs = guitar.getSpec();

            System.out.println("Model: " + guitar.getModel());
            System.out.println("Price: " + guitar.getPrice());
            System.out.println();
            System.out.println("Builder: " + gs.getBuilder());
            System.out.println("Type: " + gs.getType());
            System.out.println("BodyWood: " + gs.getBodyWood());
            System.out.println("NeckWood: " + gs.getNeckWood());
            System.out.println("-----");
        }
    }

    public static Inventory createInventory() {
        Inventory inventory = new Inventory();

        inventory.addGuitar(new Guitar("Strato", 1450.0, new GuitarSpec(Builder.FENDER, Type.ELECTRIC, Wood.CAOBA, Wood.CAOBA)));
        inventory.addGuitar(new Guitar("AT-10", 1450.0, new GuitarSpec(Builder.IBANEZ, Type.ELECTRIC, Wood.MAPLE, Wood.MAPLE)));
        inventory.addGuitar(new Guitar("JS-100", 1450.0, new GuitarSpec(Builder.IBANEZ, Type.ELECTRIC, Wood.MAPLE, Wood.MAPLE)));
        inventory.addGuitar(new Guitar("Tele", 1450.0, new GuitarSpec(Builder.FENDER, Type.ELECTRIC, Wood.CAOBA, Wood.ARCE)));
        inventory.addGuitar(new Guitar("Jazz", 1450.0, new GuitarSpec(Builder.PRS, Type.ELECTRIC, Wood.CAOBA, Wood.CAOBA)));

        return inventory;
    }
}

