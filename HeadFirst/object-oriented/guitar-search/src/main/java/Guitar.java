/**
 *
 */
public class Guitar {
    private String model;
    private double price;
    private GuitarSpec spec;

    public Guitar(String model, double price, GuitarSpec spec) {
        this.model = model;
        this.price = price;
        this.spec = spec;
    }

    public String getModel() {
        return model;
    }

    public double getPrice() {
        return price;
    }

    public GuitarSpec getSpec() {
        return spec;
    }
}
