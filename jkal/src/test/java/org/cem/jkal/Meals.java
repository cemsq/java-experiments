package org.cem.jkal;

import org.cem.jkal.model.Food;
import org.cem.jkal.model.Meal;

/**
 *
 */
public class Meals {

    public static Meal sandwich_1(String name) {
        return new Meal(name)
                .add(Foods.panDeSandwich, 50)
                .add(Foods.jamon, 40)
                .add(Foods.cottage, 30);
    }

    public static Meal sandwich_2(String name) {
        return new Meal(name)
                .add(Foods.panDeSandwich, 100)
                .add(Foods.jamon, 40)
                .add(Foods.cottage, 60);
    }

    public static Meal preWorkout() {
        return new Meal("preWorkout")
                .add(Foods.ricePudding, 60)
                .add(Foods.protein, 30);
    }

    public static Meal postWorkout() {
        return new Meal("postWorkout")
                .add(Foods.protein, 30);
    }

    public static Meal standard(String name, Food carb, double q) {
        return new Meal(name).add(carb, q).add(Foods.pollo, 150).add(Foods.verduras, 150);
    }

    public static Meal ricePudding(String name, double q) {
        return new Meal(name).add(Foods.ricePudding, q).add(Foods.protein, 30);
    }

    public static Meal avena(String name, double q) {
        return new Meal(name).add(Foods.avena, q).add(Foods.protein, 30);
    }
}
