package fieldhandler.example.item;

import java.util.List;

/**
 *
 */
public class Item {

    private String name;

    private List<ItemIngredient> itemIngredients;

    public Item() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ItemIngredient> getItemIngredients() {
        return itemIngredients;
    }

    public void setItemIngredients(List<ItemIngredient> itemIngredients) {
        this.itemIngredients = itemIngredients;
    }
}
