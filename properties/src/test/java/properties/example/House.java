package properties.example;

/**
 *
 */
public class House {
    private Number number;

    public House() {
        number = new Number();
    }

    public House(Number number) {
        this.number = number;
    }

    public Number getNumber() {
        return number;
    }

    public void setNumber(Number number) {
        this.number = number;
    }
}
