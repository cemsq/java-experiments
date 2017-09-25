/**
 *
 */
public enum Wood {
    ARCE("Arce"),
    MAPLE("Maple"),
    CAOBA("Caoba");

    private String wood;

    Wood(String str) {
        wood = str;
    }

    @Override
    public String toString() {
        return wood;
    }
}
