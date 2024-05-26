package org.cem.jkal.model;

import lombok.Getter;

/**
 *
 */
@Getter
public enum Macro {
    CARBS(4),
    PROTEIN(4),
    FAT(9);

    private final double factor;

    Macro(double cal) {
        this.factor = cal;
    }

    public static double toCalories(double carbs, double protein, double fat) {
        return CARBS.toCalories(carbs) + PROTEIN.toCalories(protein) + FAT.toCalories(fat);
    }

    public double toCalories(double g) {
        return factor * g;
    }
}
