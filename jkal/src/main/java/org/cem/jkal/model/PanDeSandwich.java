package org.cem.jkal.model;

import java.util.function.ToDoubleFunction;

/**
 *
 */
public class PanDeSandwich extends Item {

    public PanDeSandwich() {
        super("Pan de sandwich", 40, 0, 2);
    }

    @Override
    public double computeFrom(double givenMacros, ToDoubleFunction<AbstractItem> f) {
        double grams = super.computeFrom(givenMacros, f);
        return grams * 4 / 100;
    }
}
