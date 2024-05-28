package org.cem.jkal.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.function.ToDoubleFunction;

import static org.cem.jkal.model.Utils.round;
import static org.cem.jkal.model.Utils.toFactor;

/**
 *
 */
@Getter
@Setter
@ToString
public abstract class AbstractItem {

    private static final double REF_100g = 100;

    private String name;
    private double carbs;
    private double protein;
    private double fat;

    public AbstractItem(String name, double carbs, double protein, double fat) {
        this(name, carbs, protein, fat, 100);
    }

    public AbstractItem(String name, double carbs, double protein, double fat, double g) {
        this.name = name;
        setCarbs(toFactor(carbs, g, REF_100g));
        setProtein(toFactor(protein, g, REF_100g));
        setFat(toFactor(fat, g, REF_100g));
    }

    public void setCarbs(double carbs) {
        this.carbs = round(carbs);
    }

    public void setProtein(double protein) {
        this.protein = round(protein);
    }

    public void setFat(double fat) {
        this.fat = round(fat);
    }

    public Consumption consume(double given) {
        double c = toFactor(getCarbs(), REF_100g, given);
        double p = toFactor(getProtein(), REF_100g, given);
        double f = toFactor(getFat(), REF_100g, given);
        double cals = Macro.toCalories(c, p, f);
        return new Consumption(given + " g " + getName(), c, p, f, cals);
    }

    public double computeFromCarbs(AbstractItem other, double given) {
        return computeFrom(other, given, AbstractItem::getCarbs);
    }

    public double computeFrom(AbstractItem other, double given, ToDoubleFunction<AbstractItem> f) {
        Consumption otherConsumption = other.consume(given);
        double otherCarbs = f.applyAsDouble(otherConsumption);
        return round(toFactor(REF_100g, f.applyAsDouble(this), otherCarbs));
    }
}
