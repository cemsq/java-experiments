package org.cem.jkal;

import org.cem.jkal.model.Item;
import org.cem.jkal.model.PanDeSandwich;

/**
 *
 */
public class Items {

    public static final Item ricePudding = new Item("ricePudding", 80, 0, 1);
    public static final Item arroz = new Item("Arroz", 80, 0, 0.2);
    public static final Item pasta = new Item("Pasta", 70, 0, 1.5);
    public static final Item avena = new Item("Avena", 60, 0, 1.6);
    public static final Item panDeSandwich = new PanDeSandwich(); // 4 panes son 100 g
    public static final Item papa = new Item("Papa ", 15, 0, 0);
    public static final Item kuskus = new Item("Kuskus ", 77, 8, 0.7);

    public static final Item res = new Item("Res", 0, 27, 17);
    public static final Item pavo = new Item("Pavo", 0, 25, 8);
    public static final Item pollo = new Item("Pollo", 0, 24, 8);
    public static final Item fish = new Item("Fisch", 0, 22, 12);
    public static final Item jamon = new Item("Jamon", 0.5, 18, 2);
    public static final Item cottage = new Item("Cottage cheese", 3, 10, 2.5);
    public static final Item magertopfen = new Item("Magertopfen", 4.1, 12, 0.5);
    public static final Item skyr = new Item("Skyr", 4, 9.5, 0.5);
    public static final Item protein = new Item("wheyProtein", 4, 21, 2, 30);

    public static final Item verduras = new Item("Verduras", 8, 1, 1);


}
