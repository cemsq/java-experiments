package org.cem.jkal.model;

import lombok.Getter;

import static org.cem.jkal.model.Utils.round;

/**
 *
 */
@Getter
public class Consumption extends AbstractItem {

    private double cal;

    public Consumption(String name, double carbs, double protein, double fat, double cal) {
        super(name, carbs, protein, fat);
        this.cal = round(cal);
    }

    public Consumption(String name) {
        this(name, 0, 0, 0, 0);
    }

    public void add(Consumption c) {
        setCarbs(getCarbs() + c.getCarbs());
        setProtein(getProtein() + c.getProtein());
        setFat(getFat() + c.getFat());
        cal = round(cal + c.getCal());
    }

    @Override
    public String toString() {
        return "Consumed{" +
                getName() + " = " +
                "cal=" + cal + ", " +
                "carbs=" + getCarbs() + ", " +
                "protein=" + getProtein() + ", " +
                "fat=" + getFat() +
                '}';
    }
}
