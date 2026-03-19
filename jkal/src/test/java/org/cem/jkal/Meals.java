package org.cem.jkal;

import org.cem.jkal.model.Item;
import org.cem.jkal.model.Meal;

/**
 *
 */
public class Meals {

    public static Meal create(String name) {
        return new Meal(name);
    }

    public static Meal sandwich_1(String name) {
        return new Meal(name)
                .add(Items.panDeSandwich, 50)
                .add(Items.jamon, 40)
                .add(Items.cottage, 125);
    }

    public static Meal sandwich_2(String name) {
        return new Meal(name)
                .add(Items.panDeSandwich, 100)
                .add(Items.jamon, 40)
                .add(Items.cottage, 60);
    }

    public static Meal sandwich(String name, int panes, double cottage) {
        return new Meal(name)
                .add(Items.panDeSandwich, (double) panes * 100 / 4)
                .add(Items.jamon, 40)
                .add(Items.cottage, cottage);
    }

    public static Meal preWorkout() {
        return new Meal("preWorkout")
                .add(Items.ricePudding, 30)
                .add(Items.protein, 30);
    }

    public static Meal preWorkout(double consumed) {
        return new Meal("preWorkout")
                .add(Items.ricePudding, consumed)
                .add(Items.protein, 30);
    }

    public static Meal postWorkout() {
        return new Meal("postWorkout")
                .add(Items.protein, 30);
    }

    public static Meal standard(String name, Item carb, double q) {
        return new Meal(name).add(carb, q).add(Items.pollo, 150).add(Items.verduras, 150);
    }

    public static Meal ricePudding(String name, double p, double c) {
        return new Meal(name).add(Items.ricePudding, c).add(Items.protein, p);
    }

    public static Meal ricePudding(String name, double q) {
        return new Meal(name).add(Items.ricePudding, q).add(Items.protein, 30);
    }

    public static Meal avena(String name, double q) {
        return new Meal(name).add(Items.avena, q).add(Items.protein, 30);
    }

    public static Meal panConTopfen(String name, int panes) {
        return panConTopfen(name, panes, 125, 100);
    }

    public static Meal panConTopfen(String name, int panes, int magertopfen, int skyr) {
        return Meals.create(name)
                .add(Items.panDeSandwich, (double) panes * 100 / 4)
                .add(Items.magertopfen, magertopfen)
                .add(Items.skyr, skyr);
    }

    public static Meal avenaNat(String name, int consumed) {
        return avenaNat(name, consumed, 10);
    }

    public static Meal avenaNat(String name, int consumed, int protein) {
        return Meals.create(name)
                .add(Items.avena, consumed)
                .add(Items.magertopfen, 125)
                .add(Items.skyr, 100)
                .add(Items.protein, protein);
    }

    public static Meal grasa(int fat) {
        return new Meal("grasa").add(new Item("grasa", 0, 0, fat, 100), 100);
    }
}
