package org.cem.jkal;

import org.cem.jkal.model.Item;
import org.cem.jkal.model.Meal;

/**
 *
 */
public class Meals {

    public static Meal sandwich_1(String name) {
        return new Meal(name)
                .add(Items.panDeSandwich, 50)
                .add(Items.jamon, 40)
                .add(Items.cottage, 30);
    }

    public static Meal sandwich_2(String name) {
        return new Meal(name)
                .add(Items.panDeSandwich, 100)
                .add(Items.jamon, 40)
                .add(Items.cottage, 60);
    }

    public static Meal preWorkout() {
        return new Meal("preWorkout")
                .add(Items.ricePudding, 60)
                .add(Items.protein, 30);
    }

    public static Meal postWorkout() {
        return new Meal("postWorkout")
                .add(Items.protein, 30);
    }

    public static Meal standard(String name, Item carb, double q) {
        return new Meal(name).add(carb, q).add(Items.pollo, 150).add(Items.verduras, 150);
    }

    public static Meal ricePudding(String name, double q) {
        return new Meal(name).add(Items.ricePudding, q).add(Items.protein, 30);
    }

    public static Meal avena(String name, double q) {
        return new Meal(name).add(Items.avena, q).add(Items.protein, 30);
    }
}
