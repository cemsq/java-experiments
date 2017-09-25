package workflow;

public class Item {
    private String assortment;
    private String provider;
    private Integer price;

    public Item(String assortment, String provider, Integer price) {
        this.assortment = assortment;
        this.provider = provider;
        this.price = price;
    }

    public String getAssortment() {
        return assortment;
    }

    public String getProvider() {
        return provider;
    }

    public Integer getPrice() {
        return price;
    }
}
